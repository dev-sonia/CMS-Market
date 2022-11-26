package com.dalgorithm.ecms.application;

import com.dalgorithm.ecms.common.UserType;
import com.dalgorithm.ecms.config.JwtAuthenticationProvider;
import com.dalgorithm.ecms.domain.SignInForm;
import com.dalgorithm.ecms.domain.model.Customer;
import com.dalgorithm.ecms.domain.model.Seller;
import com.dalgorithm.ecms.exception.CustomException;
import com.dalgorithm.ecms.exception.ErrorCode;
import com.dalgorithm.ecms.service.customer.CustomerService;
import com.dalgorithm.ecms.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignInApplication {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;
    private final SellerService sellerService;

    public String customerLogInToken(SignInForm form) {
        Customer customer = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }

    public String sellerLoginToken(SignInForm form) {
        Seller seller = sellerService.findValidSeller(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(seller.getEmail(), seller.getId(), UserType.SELLER);
    }
}

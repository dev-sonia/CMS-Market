package com.dalgorithm.ecms.controller;

import com.dalgorithm.ecms.common.UserVo;
import com.dalgorithm.ecms.config.JwtAuthenticationProvider;
import com.dalgorithm.ecms.domain.CustomerDto;
import com.dalgorithm.ecms.domain.customer.ChangeBalanceForm;
import com.dalgorithm.ecms.domain.model.Customer;
import com.dalgorithm.ecms.exception.CustomException;
import com.dalgorithm.ecms.service.customer.CustomerBalanceService;
import com.dalgorithm.ecms.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dalgorithm.ecms.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;



    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo userVo = provider.getUserVo(token);
        Customer customer = customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        return ResponseEntity.ok(CustomerDto.from(customer));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                 @RequestBody ChangeBalanceForm form) {
        UserVo vo = provider.getUserVo(token);

        return ResponseEntity.ok(customerBalanceService.changeBalance(vo.getId(), form).getCurrentMoney());
    }
}

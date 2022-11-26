package com.dalgorithm.ecms.service.seller;

import com.dalgorithm.ecms.domain.SignUpForm;
import com.dalgorithm.ecms.domain.model.Customer;
import com.dalgorithm.ecms.domain.model.Seller;
import com.dalgorithm.ecms.domain.repository.CustomerRepository;
import com.dalgorithm.ecms.domain.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    public Optional<Seller> findByIdAndEmail(Long id, String email) {
        return sellerRepository.findByIdAndEmail(id, email);
    }

    public Optional<Seller> findValidSeller(String email, String password){
        return sellerRepository.findByEmailAndPasswordAndVerifyIsTrue(email, password);
    }
}

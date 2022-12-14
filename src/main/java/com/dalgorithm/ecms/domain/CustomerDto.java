package com.dalgorithm.ecms.domain;

import com.dalgorithm.ecms.domain.model.Customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String email;
    private Integer balance;

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getEmail(), customer.getBalance() == null ? 0 : customer.getBalance());
    }
}
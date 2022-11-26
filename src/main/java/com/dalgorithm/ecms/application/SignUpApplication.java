package com.dalgorithm.ecms.application;

import com.dalgorithm.ecms.client.MailgunClient;
import com.dalgorithm.ecms.client.mailgun.SendMailForm;
import com.dalgorithm.ecms.domain.SignUpForm;
import com.dalgorithm.ecms.domain.model.Customer;
import com.dalgorithm.ecms.domain.model.Seller;
import com.dalgorithm.ecms.exception.CustomException;
import com.dalgorithm.ecms.service.customer.SignUpCustomerService;
import com.dalgorithm.ecms.service.seller.SignUpSellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import static com.dalgorithm.ecms.exception.ErrorCode.ALREADY_REGISTER_USER;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;

    private final SignUpSellerService signUpSellerService;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form) {
        if(signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ALREADY_REGISTER_USER);
        } else {
            Customer c = signUpCustomerService.signUp(form);
            String code = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("tester@my.com")
                    .to(form.getEmail())
                    .subject("Verification Email")
                    .text(getVerificationEmailBody(c.getEmail(), c.getName(),"customer", code))
                    .build();

            log.info("Send email result : " + mailgunClient.sendEmail(sendMailForm).getBody());

            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.changeCustomerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    public void sellerVerify(String email, String code) {
        signUpSellerService.verifyEmail(email, code);
    }

    public String sellerSignUp(SignUpForm form) {
        if (signUpSellerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ALREADY_REGISTER_USER);
        } else {
            Seller seller = signUpSellerService.signUp(form);
            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("tester@gmail.com")
                    .to(form.getEmail())
                    .subject("Verification Email")
                    .text(getVerificationEmailBody(seller.getEmail(), seller.getName(), "seller", code))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            signUpSellerService.changeSellerValidateEmail(seller.getId(), code);
            return "회원 가입에 성공하였습니다.";

        }
    }

    private String getRandomCode() {return RandomStringUtils.random(10, true, true);}

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello ").append(name).append("! Please Click Link for verification.\n\n")
                .append("https://localhost:8080/signup/"+type+"/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }

}

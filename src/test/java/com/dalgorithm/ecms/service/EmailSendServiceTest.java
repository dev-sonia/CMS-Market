package com.dalgorithm.ecms.service;

import com.dalgorithm.ecms.client.MailgunClient;
import com.dalgorithm.ecms.client.mailgun.SendMailForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private MailgunClient mailgunClient;

    @Test
    void EmailTest() {
        SendMailForm form = SendMailForm.builder()
                .from("commerce-market-test@my.com")
                .to("moonlab27@gmail.com")
                .subject("Test Email - MOON CMS")
                .text("Welcome to MOON CMS")
                .build();

        mailgunClient.sendEmail(form);
    }
}
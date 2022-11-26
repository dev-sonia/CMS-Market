package com.dalgorithm.ecms.client;

import com.dalgorithm.ecms.client.mailgun.SendMailForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "${mailgun.url}")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("${mailgun.api}")
    ResponseEntity<String> sendEmail(@SpringQueryMap SendMailForm form);
}

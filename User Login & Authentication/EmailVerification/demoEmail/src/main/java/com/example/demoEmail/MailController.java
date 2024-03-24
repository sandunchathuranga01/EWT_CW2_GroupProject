package com.example.demoEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mail")
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send")
    public String sendEmail(@RequestBody mailDto mailDto){
        try {
            SimpleMailMessage message= new SimpleMailMessage();
            message.setSubject(mailDto.getSubject());
            message.setTo(mailDto.getToMail());
            message.setFrom("lakindu.20221402@iit.ac.lk");
            message.setText(mailDto.getMessage());

            javaMailSender.send(message);

            return "Success";

        }catch (Exception e){
            return e.getMessage();
        }


    }


}

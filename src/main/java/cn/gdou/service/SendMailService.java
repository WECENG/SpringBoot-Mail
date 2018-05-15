package cn.gdou.service;

import cn.gdou.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    @Autowired
    private MailSender mailSender;

    @Autowired
    private Environment env;

    public void sendSimpleEmail(String destination,Message message){
        SimpleMailMessage mailMessage =new SimpleMailMessage();
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        mailMessage.setTo(destination);
        mailMessage.setSubject("New Friend");
        mailMessage.setText(message.getDate()+": "+message.getContent());

        mailSender.send(mailMessage);
    }
}

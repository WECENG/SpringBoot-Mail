package cn.gdou.service;

import cn.gdou.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendThymeleafMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    private SpringTemplateEngine thymeleaf;

    public void sendHtmlMail(String destination, Message message,String tile)
                        throws MessagingException {
        Context ctx=new Context();
        ctx.setVariable("mailName",tile);
        ctx.setVariable("mailText",message.getDate()+": "+message.getContent());
        ClassPathResource image=new ClassPathResource("image/AMQP.png");
        ctx.setVariable("AMQP",image);
        String emailText=thymeleaf.process("mailTemplate.html",ctx);
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setTo(destination);
        helper.setText(emailText,true);

        mailSender.send(mimeMessage);
    }
}

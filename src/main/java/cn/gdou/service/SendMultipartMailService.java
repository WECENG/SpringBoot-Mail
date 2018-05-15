package cn.gdou.service;

import cn.gdou.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMultipartMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String mailUser;

    public void sendSimpleMultipartEmail(String destination,Message message)
                            throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);   //表明消息是multipart类型的
        helper.setFrom(mailUser);
        helper.setTo(destination);
        helper.setSubject("新朋友");
        helper.setText(message.getDate()+": "+message.getContent());
        //文件系统路径
        FileSystemResource image=new FileSystemResource(
                "D:\\IDEA_WorkSpace\\SpringBoot-Mail\\src\\main\\resources\\image\\AMQP.png");
        //类路径
        ClassPathResource  image1=new ClassPathResource("image/AMQP.png");
        helper.addAttachment("AMQP.png",image);
        helper.addAttachment("AMQP.png",image1);

        mailSender.send(mimeMessage);
    }
}

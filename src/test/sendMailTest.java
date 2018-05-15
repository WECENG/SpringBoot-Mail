import cn.gdou.model.Message;
import cn.gdou.service.SendMailService;
import cn.gdou.service.SendMultipartMailService;
import cn.gdou.service.SendThymeleafMailService;
import cn.gdou.springboot.SpringBootApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class sendMailTest {
    private static final Logger log=LoggerFactory.getLogger(sendMailTest.class);

    @Autowired
    private SendMailService mailService;

    @Autowired
    private SendMultipartMailService multipartMailService;

    @Autowired
    private SendThymeleafMailService thymeleafMailService;

//    @Test
//    public void sendEmailTest(){
//        String destination="18316380586@sina.cn";
//        Message message=new Message();
//        message.setDate(new Date().toString());
//        message.setContent("hello,hello");
//        mailService.sendSimpleEmail(destination,message);
//            log.info("the email was sent");
//    }

//    @Test
//    public void sendMultipartEmailTest(){
//        String destination="18316380586@sina.cn";
//        Message message=new Message();
//        message.setDate(new Date().toString());
//        message.setContent("你好，我传了张图片");
//        try{
//        multipartMailService.sendSimpleMultipartEmail(destination,message);
//        }catch (MessagingException e){
//            log.error("Message convert error");
//        }
//        log.info("the email was sent");
//    }

    @Test
    public void sendHtmlEmailTest(){
        String destination="****";
        Message message=new Message();
        message.setDate(new Date().toString());
        message.setContent("你好，我发送了一个html格式的文档");
        String title="html邮件";
        try{
            thymeleafMailService.sendHtmlMail(destination,message,title);
        }catch (MessagingException e){
            log.error("Message convert error");
        }
        log.info("the email was sent");
    }


}

SpringBoot MAil
        工作方式是以SMTP（Simple Mail Transfer Protocol）即简单邮件传输协议的方式连接
        已有的邮箱账号，例如新浪邮箱，实现邮件的发送
            1.首先需要在相应的邮箱启动smtp协议
            2.创建MailSender Bean,设置连接邮箱的账号密码以及服务器名称
                 @Bean
                    public MailSender mailSender(){
                        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
                        mailSender.setHost(env.getProperty("spring.mail.host"));
                        mailSender.setUsername(env.getProperty("spring.mail.username"));
                        mailSender.setPassword(env.getProperty("spring.mail.password"));
                        mailSender.setDefaultEncoding("utf-8");
                        return mailSender;
                    }
            3.使用MailSender对象发送邮件
                    a.发送简单的文本邮件，直接使用SimpleMailMessage对象足够
                             public void sendSimpleEmail(String destination,Message message){
                                    SimpleMailMessage mailMessage =new SimpleMailMessage();
                                    mailMessage.setFrom(env.getProperty("spring.mail.username"));
                                    mailMessage.setTo(destination);
                                    mailMessage.setSubject("New Friend");
                                    mailMessage.setText(message.getDate()+": "+message.getContent());
                                    mailSender.send(mailMessage);
                                }
                    b.发送带有附件的邮件
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
                    c.发送html格式的邮件
                              使用thymeleaf视图技术将html模板转换为String,在将其发送出去
                              需要注入SpringTemplateEngine模板引擎，还需要添加一个thymeleaf
                              解析器，让它能够找到html模板
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
                                 
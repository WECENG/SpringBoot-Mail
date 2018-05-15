package cn.gdou.web.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Set;

@Configuration
@ComponentScan("cn.gdou.web.controller")
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    public SpringResourceTemplateResolver resourceTemplateResolver(){
        SpringResourceTemplateResolver templateResolver=
                new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setOrder(2);
        return templateResolver;
    }

    @Bean
    public ClassLoaderTemplateResolver loaderTemplateResolver(){
        ClassLoaderTemplateResolver templateResolver=
                new ClassLoaderTemplateResolver();
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setPrefix("templates/");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setOrder(1);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(
            Set<ITemplateResolver> resolvers){
        SpringTemplateEngine templateEngine=
                new SpringTemplateEngine();
        templateEngine.setTemplateResolvers(resolvers);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(
            SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver=
                new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
}


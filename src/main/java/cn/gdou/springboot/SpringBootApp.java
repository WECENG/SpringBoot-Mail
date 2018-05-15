package cn.gdou.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("cn.gdou")
@Configuration
@EnableAutoConfiguration
public class SpringBootApp {
    public static void main(String[] args){
        SpringApplication.run(SpringBootApp.class);
    }
}

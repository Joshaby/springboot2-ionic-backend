package com.nelioalves.cursomc.config;

import com.nelioalves.cursomc.services.DataBaseService;
import com.nelioalves.cursomc.services.EmailService;
import com.nelioalves.cursomc.services.MockEmailService;
import com.nelioalves.cursomc.services.SMTPEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DataBaseService dataBaseService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean initializeMariaDB() throws ParseException {
        if (strategy.equals("create")) {
            dataBaseService.initializeDataBase();
            return true;
        }
        return false;
    }

    @Bean
    public EmailService emailService() {
        return new SMTPEmailService();
    }
}

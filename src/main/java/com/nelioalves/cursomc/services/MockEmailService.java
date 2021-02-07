package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
    private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendMail(SimpleMailMessage simpleMailMessage) {
        logger.info("Enviando email...");
        logger.info(simpleMailMessage.toString());
        logger.info("Email enviado!");
    }
}

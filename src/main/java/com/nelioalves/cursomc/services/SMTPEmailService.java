package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPEmailService extends AbstractEmailService {
    private static final Logger logger = LoggerFactory.getLogger(SMTPEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendMail(SimpleMailMessage simpleMailMessage) {
        logger.info("Enviando email...");
        mailSender.send(simpleMailMessage);
        logger.info("Email enviado!");
    }
}

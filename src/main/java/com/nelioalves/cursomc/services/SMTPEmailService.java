package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;

public class SMTPEmailService extends AbstractEmailService {
    private static final Logger logger = LoggerFactory.getLogger(SMTPEmailService.class);

    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(SimpleMailMessage simpleMailMessage) {
        logger.info("Enviando email...");
        mailSender.send(simpleMailMessage);
        logger.info("Email enviado!");
    }
    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) {
        logger.info("Enviando emal Html...");
        javaMailSender.send(mimeMessage);
        logger.info("Email enviado!");
    }
}

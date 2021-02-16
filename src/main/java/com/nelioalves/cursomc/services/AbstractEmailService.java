package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPedido(pedido);
        sendMail(simpleMailMessage);
    }
    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage mimeMessage = prepapeHtmlMailMessageFromPedido(pedido);
            sendHtmlEmail(mimeMessage);
        }
        catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sender);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido confirmado! Id do pedido " + pedido.getId());
        simpleMailMessage.setText(pedido.toString());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        return simpleMailMessage;
    }
    protected MimeMessage prepapeHtmlMailMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(pedido.getCliente().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Pedido confirmado! Id do pedido " + pedido.getId());
        mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        return mimeMessage;
    }

    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        String html = templateEngine.process("email/confirmacaoPedido", context);
        System.out.println(html);
        return html;
    }
}

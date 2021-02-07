package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPedido(pedido);
        sendMail(simpleMailMessage);
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
}

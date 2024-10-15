package com.alonsofritz.email_service.application.services;

import com.alonsofritz.email_service.adapters.interfaces.EmailSenderGateway;
import com.alonsofritz.email_service.domain.usecases.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void execute(String to, String subject, String body) {
        this.emailSenderGateway.sendEmail(to, subject, body);
    }
}

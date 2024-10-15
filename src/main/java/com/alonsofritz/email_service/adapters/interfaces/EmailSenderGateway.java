package com.alonsofritz.email_service.adapters.interfaces;

public interface EmailSenderGateway {
    void sendEmail(String to, String subject, String body);
}

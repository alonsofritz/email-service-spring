package com.alonsofritz.email_service.domain.usecases;

public interface EmailSenderUseCase {
    void execute(String to, String subject, String body);
}

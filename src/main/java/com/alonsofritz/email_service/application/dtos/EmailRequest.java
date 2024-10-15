package com.alonsofritz.email_service.application.dtos;

public record EmailRequest(String to, String subject, String body) {
}

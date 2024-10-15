package ru.collection.doll_collection.reportService.emailService;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmailWithFile() throws MessagingException;
}

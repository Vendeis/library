package com.example.library.activation;

public interface EmailSender {
    String sendEmail(String actOrAuth, String email);
    String generateRandomActivationCode();
    Boolean confirm(String verificationCode, String email);
}


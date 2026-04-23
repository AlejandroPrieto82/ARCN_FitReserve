package com.fitreserve.infrastructure.external;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalServicesTest {

    @Test
    void shouldSendEmailWithoutException_WhenSendEmailCalled() {

        EmailService service = new EmailService();

        assertDoesNotThrow(() -> service.sendEmail("to@test.com", "Subject", "Body"));
    }

    @Test
    void shouldNotifyWithoutException_WhenNotifyCalled() {

        NotificationService service = new NotificationService();

        assertDoesNotThrow(() -> service.notify("Test notification"));
    }
}

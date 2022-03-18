package org.store.web.security.email;

import javax.naming.NamingException;

class MessagingServiceTest {
    MessagingService service
            = ServiceLocator.getService("EmailService");
    String email = service.getMessageBody();

    MessagingService smsService
            = ServiceLocator.getService("SMSService");
    String sms = smsService.getMessageBody();

    MessagingService emailService
            = ServiceLocator.getService("EmailService");
    String newEmail = emailService.getMessageBody();

    MessagingServiceTest() throws NamingException {
    }
}
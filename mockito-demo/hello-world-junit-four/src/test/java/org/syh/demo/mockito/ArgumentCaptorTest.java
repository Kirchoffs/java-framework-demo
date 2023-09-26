package org.syh.demo.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class Email {
    private String address;
    private String title;
    private String content;

    public Email(String address, String title, String content) {
        this.address = address;
        this.title = title;
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

class DeliveryService {
    public void deliver(Email email) {
        System.out.println("Deliver email: " + email.getAddress() + ", " + email.getTitle() + ", " + email.getContent());
    }
}

class EmailService {
    public DeliveryService deliveryService;

    public EmailService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void send(String address, String title, String content) {
        Email email = new Email(address, title, content);
        deliveryService.deliver(email);
    }
}

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ArgumentCaptorTest {
    @Mock
    public DeliveryService deliveryService;

    @InjectMocks
    public EmailService emailService;

    @Test
    public void testArgumentCaptor() {
        String address = "Skytop Street";
        String title = "Hello";
        String content = "Hello World!";

        emailService.send(address, title, content);

        ArgumentCaptor<Email> emailArgumentCaptor = ArgumentCaptor.forClass(Email.class);
        Mockito.verify(deliveryService).deliver(emailArgumentCaptor.capture());
        Assert.assertEquals(address, emailArgumentCaptor.getValue().getAddress());
        System.out.println("Email address: " + emailArgumentCaptor.getValue().getAddress());
    }
}

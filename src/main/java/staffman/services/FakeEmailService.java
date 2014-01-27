package staffman.services;

import staffman.application.EmailService;

public class FakeEmailService implements EmailService {

    @Override
    public void emailSent() {
        System.out.println("Email sent!");
    }

}

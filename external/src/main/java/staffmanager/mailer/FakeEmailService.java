package staffmanager.mailer;

import staffmanager.gateway.EmailService;

public class FakeEmailService implements EmailService {

    private int emailsReceived = 0;

    @Override
    public void emailSent(String email) {
        emailsReceived += 1;
        System.out.println("Email sent!");
    }

    public int getEmailsReceived() {
        return emailsReceived;
    }
}

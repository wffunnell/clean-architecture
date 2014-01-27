package staffmanager.presentation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.joda.time.DateTime;
import staffmanager.birthday.BirthdayEmailer;
import staffmanager.configuration.BirthdayModuleConfiguration;
import staffmanager.usecase.BirthdayMessenger;

public class BirthdayController {

    private BirthdayMessenger messenger;

    public BirthdayController(BirthdayMessenger messenger) {
        this.messenger = messenger;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BirthdayModuleConfiguration());
        BirthdayMessenger birthdayMessenger = injector.getInstance(BirthdayEmailer.class);

        BirthdayController controller = new BirthdayController(birthdayMessenger);
        controller.handleMessages();
    }

    private void handleMessages() {
        messenger.emailSentToEmployeesWithBirthday(new DateTime());
    }


}

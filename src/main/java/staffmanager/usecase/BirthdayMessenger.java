package staffmanager.usecase;

import org.joda.time.DateTime;

public interface BirthdayMessenger {
    void emailSentToEmployeesWithBirthday(DateTime today);
}

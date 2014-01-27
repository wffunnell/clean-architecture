package staffman.application;

import org.joda.time.DateTime;

public interface BirthdayService {
    void emailSentToEmployeesWithBirthday(EmployeeRepository repository, DateTime today);
}

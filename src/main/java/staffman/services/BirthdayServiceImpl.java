package staffman.services;

import ddd.hexagonal.application.BirthdayService;
import ddd.hexagonal.application.EmailService;
import ddd.hexagonal.application.EmployeeRepository;
import ddd.hexagonal.domain.Employee;
import org.joda.time.DateTime;

public class BirthdayServiceImpl implements BirthdayService {

    private final EmailService emailService;

    public BirthdayServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void emailSentToEmployeesWithBirthday(EmployeeRepository repository, DateTime today) {
        for (Employee employee : repository.employeesWithBirthday(today)) {
            emailService.emailSent();
        }
    }

}

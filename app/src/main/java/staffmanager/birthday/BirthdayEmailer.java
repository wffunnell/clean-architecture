package staffmanager.birthday;

import com.google.common.collect.Iterables;
import org.joda.time.DateTime;
import staffmanager.usecase.BirthdayMessenger;
import staffmanager.gateway.EmailService;
import staffmanager.gateway.EmployeeRepository;
import staffmanager.employee.Employee;

import javax.inject.Inject;

public class BirthdayEmailer implements BirthdayMessenger {

    private final EmailService emailService;
    private final EmployeeRepository employeeRepository;

    @Inject
    public BirthdayEmailer(EmailService emailService, EmployeeRepository employeeRepository) {
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void emailSentToEmployeesWithBirthday(DateTime today) {
        Iterable<Employee> employees = employeeRepository.employeesWithBirthday(today);
        if (Iterables.isEmpty(employees)) {
            System.out.println("No employees today!");
        } else {
            for (Employee e : employees) {
                emailService.emailSent(e.getEmailAddress());
            }
        }
    }

}

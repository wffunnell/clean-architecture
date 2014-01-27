package staffmanager.configuration;

import com.google.inject.AbstractModule;
import staffmanager.employee.FileReadingEmployeeRepository;
import staffmanager.gateway.EmailService;
import staffmanager.gateway.EmployeeRepository;
import staffmanager.mailer.FakeEmailService;

public class BirthdayModuleConfiguration extends AbstractModule {

    private final String employeeFilename;

    public BirthdayModuleConfiguration() {
        this.employeeFilename = "/employees.txt";
    }

    @Override
    protected void configure() {
        bind(EmailService.class).to(FakeEmailService.class);
        bind(EmployeeRepository.class).to(FileReadingEmployeeRepository.class);
        bindConstant().annotatedWith(FileReadingEmployeeRepository.EmployeeFilename.class).to(employeeFilename);
    }
}

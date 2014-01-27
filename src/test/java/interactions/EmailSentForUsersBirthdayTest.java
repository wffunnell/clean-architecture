package interactions;

import com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import staffmanager.domain.Employee;
import staffmanager.gateway.EmployeeRepository;
import staffmanager.interactions.BirthdayEmailer;
import staffmanager.services.FakeEmailService;
import staffmanager.usecase.BirthdayMessenger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailSentForUsersBirthdayTest {

    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private FakeEmailService emailService = new FakeEmailService();
    private DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
    private DateTime today = fmt.parseDateTime("2020/10/08");

    @Test
    public void emailSentForUsersBirthday() throws Exception {
        BirthdayMessenger birthdayService = new BirthdayEmailer(emailService, employeeRepository);

        when(employeeRepository.employeesWithBirthday(today)).thenReturn(getFakeEmployees());
        birthdayService.emailSentToEmployeesWithBirthday(today);

        assertThat(emailService.getEmailsReceived(), is(equalTo(1)));
    }

    private Iterable<Employee> getFakeEmployees() {
        return ImmutableList.of(
                new Employee.Builder()
                        .dateOfBirth(today)
                        .email("test@test.com")
                        .firstName("john")
                        .lastName("doe")
                        .build()
        );
    }

}

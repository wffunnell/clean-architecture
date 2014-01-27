package staffmanager.employee;

import com.google.common.collect.Iterables;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import staffmanager.gateway.EmployeeRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FilterEmployeesWithBirthdayTodayTest {

    private EmployeeRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new FileReadingEmployeeRepository("/employees.txt");
    }

    @Test
    public void onlyEmployeesWithBirthdayFiltered() throws Exception {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");

        Iterable<Employee> employees = repository.employeesWithBirthday(fmt.parseDateTime("2020/10/08"));

        assertThat(Iterables.size(employees), is(equalTo(1)));
    }

}

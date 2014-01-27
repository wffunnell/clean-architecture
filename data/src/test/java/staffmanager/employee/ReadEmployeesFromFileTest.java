package staffmanager.employee;

import com.google.common.collect.Iterables;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import staffmanager.gateway.EmployeeRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class ReadEmployeesFromFileTest {

    private EmployeeRepository repository;
    private Iterable<Employee> employees;

    @Before
    public void setUp() throws Exception {
        repository = new FileReadingEmployeeRepository("/employees.txt");
        employees = repository.employeesRetrieved();
    }

    @Test
    public void canReadOneEmployeeRecordFromAFile() throws Exception {
        assertThat(Iterables.size(employees), is(equalTo(2)));
    }

    @Test
    public void hasLastName() throws Exception {
        assertThat(getFirstEmployee().getLastName(), is("Doe"));
    }

    @Test
    public void hasFirstName() throws Exception {
        assertThat(getFirstEmployee().getFirstName(), is("John"));
    }

    @Test
    public void hasDOB() throws Exception {
        DateTime dob = new EmployeeFileDateFormatter().getDateTime("1982/10/08");

        assertThat(getFirstEmployee().getDateOfBirth(), is(dob));
    }

    @Test
    public void hasEmail() throws Exception {
        assertThat(getFirstEmployee().getEmailAddress(), is("john.doe@foobar.com"));
    }

    @Test
    public void allEmployeesReadFromFile() throws Exception {
        assertThat(Iterables.size(employees), is(greaterThan(0)));
    }

    private Employee getFirstEmployee() {
        return Iterables.get(employees, 0);
    }

}

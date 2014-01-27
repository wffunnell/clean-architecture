package staffmanager.gateway;

import org.joda.time.DateTime;
import staffmanager.domain.Employee;

public interface EmployeeRepository {
    Iterable<Employee> employeesRetrieved();

    Iterable<Employee> employeesWithBirthday(DateTime today);
}

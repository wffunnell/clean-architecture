package staffman.application;

import org.joda.time.DateTime;
import staffman.domain.Employee;

public interface EmployeeRepository {
    Iterable<Employee> employeesRetrieved();

    Iterable<Employee> employeesWithBirthday(DateTime today);
}

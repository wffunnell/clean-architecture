package staffmanager.services;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.inject.BindingAnnotation;
import org.joda.time.DateTime;
import staffmanager.gateway.EmployeeRepository;
import staffmanager.domain.Employee;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class FileReadingEmployeeRepository implements EmployeeRepository {

    public static final int LAST_NAME_PART = 0;
    public static final int FIRST_NAME_PART = 1;
    public static final int DOB_PART = 2;
    public static final int EMAIL_PART = 3;
    private final String filename;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @BindingAnnotation
    public @interface EmployeeFilename {}

    @Inject
    public FileReadingEmployeeRepository(@EmployeeFilename String filename) {
        this.filename = filename;
    }

    @Override
    public Iterable<Employee> employeesRetrieved() {
        return ImmutableList.copyOf(
            Collections2.transform(readFile(), new Function<String, Employee>() {
                @Override
                public Employee apply(String s) {
                    return createEmployee(s);
                }
            })
        );
    }

    @Override
    public Iterable<Employee> employeesWithBirthday(final DateTime today) {
        return Iterables.filter(employeesRetrieved(), new Predicate<Employee>() {
            @Override
            public boolean apply(Employee employee) {
                return today.dayOfMonth().equals(employee.getDateOfBirth().dayOfMonth()) &&
                        today.monthOfYear().equals(employee.getDateOfBirth().monthOfYear());
            }
        });
    }

    private Employee createEmployee(String fileEntry) {
        String[] parts = fileEntry.split(", ");

        return new Employee.Builder()
                .lastName(parts[LAST_NAME_PART])
                .firstName(parts[FIRST_NAME_PART])
                .dateOfBirth(new EmployeeFileDateFormatter().getDateTime(parts[DOB_PART]))
                .email(parts[EMAIL_PART])
                .build();
    }

    private List<String> readFile() {
        List<String> fileEntries = new ArrayList<>();
        try {
            InputStream stream = getClass().getResourceAsStream(filename);

            InputStreamReader isr = new InputStreamReader(stream);
            BufferedReader ir = new BufferedReader(isr);
            String line = null;
            while ((line = ir.readLine()) != null) {
                fileEntries.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return fileEntries;
    }

}

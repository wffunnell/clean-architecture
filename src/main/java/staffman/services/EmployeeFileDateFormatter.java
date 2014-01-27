package staffman.services;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class EmployeeFileDateFormatter {
    public DateTime getDateTime(String date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
        return fmt.parseDateTime(date);
    }
}
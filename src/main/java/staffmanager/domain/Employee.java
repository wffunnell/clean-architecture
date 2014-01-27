package staffmanager.domain;

import org.joda.time.DateTime;

public class Employee {
    private final String lastName;
    private final String firstName;
    private final DateTime dateOfBirth;
    private final String email;

    private Employee(Builder b) {
        lastName = b.lastName;
        firstName = b.firstName;
        dateOfBirth = b.dateOfBirth;
        email = b.email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmailAddress() {
        return email;
    }

    public static class Builder {
        private String lastName;
        private String firstName;
        private DateTime dateOfBirth;
        private String email;

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder dateOfBirth(DateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }

    }

}

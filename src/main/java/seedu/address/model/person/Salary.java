package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Salary class to store the necessary information about a salary.
 */

public class Salary {

    public static final String MESSAGE_SALARY_CONSTRAINTS =
            "Salary should only contain integers with no spaces or commas";

    public static final String SALARY_VALIDATION_REGEX = "^[0-9]+$";

    public final int salary;

    public Salary(String value) {
        requireNonNull(value);
        checkArgument(isValidSalary(value), MESSAGE_SALARY_CONSTRAINTS);
        salary = Integer.parseInt(value);
    }

    public static boolean isValidSalary(String test) {
        return test.matches(SALARY_VALIDATION_REGEX);
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {

        Salary s = (Salary) o;

        if (this.getSalary() == s.getSalary()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(salary);
    }


}

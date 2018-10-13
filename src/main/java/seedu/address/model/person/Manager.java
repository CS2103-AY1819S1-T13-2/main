package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's manager in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidManager(String)}
 */
public class Manager {

    public static final String MESSAGE_CONSTRAINTS =
            "Managers should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the manager must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Manager(String name) {
        requireNonNull(name);
        checkArgument(isValidManager(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidManager(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Manager // instanceof handles nulls
                && fullName.equals(((Manager) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}

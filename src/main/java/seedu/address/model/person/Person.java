package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    //

    // Data fields
    private final Department department;
    private final Manager manager;
    private final Address address;
    private final Salary salary;
    private final OtHour otHours;
    private final OtRate otRate;
    private final PayDeductibles deductibles;
    private final Rating rating;
    private final Set<Tag> tags = new HashSet<>();
    private boolean favourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Rating rating, Department department,
                  Manager manager, Salary salary, OtHour hours, OtRate rate,
                  PayDeductibles deductibles, Set<Tag> tags, boolean favourite) {
        requireAllNonNull(name, phone, email, address, rating, department, manager, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.otHours = hours;
        this.otRate = rate;
        this.deductibles = deductibles;
        this.rating = rating;
        this.department = department;
        this.manager = manager;
        this.tags.addAll(tags);
        this.favourite = favourite;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Salary getSalary() {
        return salary;
    }

    public OtHour getOtHours() {
        return otHours;
    }

    public OtRate getOtRate() {
        return otRate;
    }

    public PayDeductibles getDeductibles() {
        return deductibles;
    }

    public Rating getRating() {
        return rating;
    }

    public Department getDepartment() {
        return department;
    }

    public Manager getManager() {
        return manager;
    }

    public boolean getFavourite() {
        return favourite;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSalary().equals(getSalary())
                && otherPerson.getOtHours().equals(getOtHours())
                && otherPerson.getOtRate().equals(getOtRate())
                && otherPerson.getDeductibles().equals(getDeductibles())
                && otherPerson.getRating().equals(getRating())
                && otherPerson.getDepartment().equals(getDepartment())
                && otherPerson.getManager().equals(getManager())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, rating, department, manager, tags, favourite);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Salary: ")
                .append(getSalary())
                .append(" OT Hours: ")
                .append(getOtHours())
                .append(" OT Rate: ")
                .append(getOtRate())
                .append(" Deductibles: ")
                .append(getDeductibles())
                .append(" Rating: ")
                .append(getRating())
                .append(" Department: ")
                .append(getDepartment())
                .append(" Manager: ")
                .append(getManager())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}

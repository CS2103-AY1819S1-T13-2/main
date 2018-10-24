package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Feedback;
import seedu.address.model.person.Manager;
import seedu.address.model.person.Name;
import seedu.address.model.person.OtHour;
import seedu.address.model.person.OtRate;
import seedu.address.model.person.PayDeductibles;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_RATING = "0";
    public static final String DEFAULT_SALARY = "0";
    public static final String DEFAULT_OTHOUR = "0";
    public static final String DEFAULT_OTRATE = "0";
    public static final String DEFAULT_DEDUCTIBLES = "0";
    public static final String DEFAULT_DEPARTMENT = "Accounting";
    public static final String DEFAULT_MANAGER = "Ben Leong";
    public static final String DEFAULT_FEEDBACK = "-NO FEEDBACK YET-";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Salary salary;
    private OtHour hours;
    private OtRate rate;
    private PayDeductibles deductibles;
    private Rating rating;
    private Department department;
    private Manager manager;
    private Feedback feedback;
    private Set<Tag> tags;
    private boolean favourite;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        salary = new Salary(DEFAULT_SALARY);
        hours = new OtHour(DEFAULT_OTHOUR);
        rate = new OtRate(DEFAULT_OTRATE);
        deductibles = new PayDeductibles(DEFAULT_DEDUCTIBLES);
        rating = new Rating(DEFAULT_RATING);
        department = new Department(DEFAULT_DEPARTMENT);
        manager = new Manager(DEFAULT_MANAGER);
        feedback = new Feedback(DEFAULT_FEEDBACK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        salary = personToCopy.getSalary();
        hours = personToCopy.getOtHours();
        rate = personToCopy.getOtRate();
        deductibles = personToCopy.getDeductibles();
        rating = personToCopy.getRating();
        department = personToCopy.getDepartment();
        manager = personToCopy.getManager();
        feedback = personToCopy.getFeedback();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the (@code Salary) of the (@code Person) that we are building.
     * @param salary
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the (@code OtHour) of the (@code Person) that we are building.
     * @param hours
     */
    public PersonBuilder withHours(String hours) {
        this.hours = new OtHour(hours);
        return this;
    }

    /**
     * Sets the (@code OtRate) of the (@code Person) that we are building.
     * @param rate
     */
    public PersonBuilder withRate(String rate) {
        this.rate = new OtRate(rate);
        return this;
    }

    /**
     * Sets the (@code PayDeductibles) of the (@code Person) that we are building.
     * @param deductibles
     */
    public PersonBuilder withDeductibles(String deductibles) {
        this.deductibles = new PayDeductibles(deductibles);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Person} that we are building.
     */
    public PersonBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code Person} that we are building.
     */
    public PersonBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    /**
     * Sets the {@code Manager} of the {@code Person} that we are building.
     */
    public PersonBuilder withManager(String manager) {
        this.manager = new Manager(manager);
        return this;
    }

    /**
     * Sets the {@code Favourite} of the {@code Person} that we are building.
     */
    public PersonBuilder withFavourite(boolean favourite) {
        this.favourite = favourite;
        return this;
    }

    /**
     * Sets the {@code Feedback} of the {@code Person} that we are building.
     */
    public PersonBuilder withFeedback(String feedback) {
        this.feedback = new Feedback(feedback);
        return this;
    }

    /**
     * Builds a new person based on the current one.
     */
    public Person build() {
        return new Person(name, phone, email, address, rating, department, manager,
          salary, hours, rate, deductibles, feedback, tags, favourite);
    }

}

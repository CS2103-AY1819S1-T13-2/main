package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
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

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String rating;
    @XmlElement(required = true)
    private String department;
    @XmlElement(required = true)
    private String manager;
    @XmlElement(required = true)
    private String salary;
    @XmlElement(required = true)
    private String hours;
    @XmlElement(required = true)
    private String rate;
    @XmlElement(required = true)
    private String deductibles;
    @XmlElement(required = true)
    private boolean favourite;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPerson() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given person details.
     */
    public XmlAdaptedPerson(String name, String phone, String email, String address, String department, String manager,
                            List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.rating = "0";
        this.department = department;
        this.manager = manager;
        this.salary = "0";
        this.hours = "0";
        this.rate = "0";
        this.deductibles = "0";
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        rating = source.getRating().value;
        department = source.getDepartment().value;
        manager = source.getManager().fullName;
        salary = source.getSalary().salary;
        hours = source.getOtHours().overTimeHour;
        rate = source.getOtRate().overTimeRate;
        deductibles = source.getDeductibles().payDeductibles;
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);
        if (department == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Department.class.getSimpleName()));
        }
        if (!Department.isValidDepartment(department)) {
            throw new IllegalValueException(Department.MESSAGE_CONSTRAINTS);
        }
        final Department modelDepartment = new Department(department);

        if (manager == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Manager.class.getSimpleName()));
        }
        if (!Manager.isValidManager(manager)) {
            throw new IllegalValueException(Manager.MESSAGE_CONSTRAINTS);
        }
        final Manager modelManager = new Manager(manager);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        if (hours == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              OtHour.class.getSimpleName()));
        }
        if (!OtHour.isValidTwoDecimalNumber(hours)) {
            throw new IllegalValueException(OtHour.MESSAGE_CONSTRAINTS);
        }
        final OtHour modelHours = new OtHour(hours);

        if (rate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              OtRate.class.getSimpleName()));
        }
        if (!OtRate.isValidTwoDecimalNumber(rate)) {
            throw new IllegalValueException(OtRate.MESSAGE_CONSTRAINTS);
        }
        final OtRate modelRate = new OtRate(rate);

        if (deductibles == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              PayDeductibles.class.getSimpleName()));
        }
        if (!PayDeductibles.isValidTwoDecimalNumber(deductibles)) {
            throw new IllegalValueException(PayDeductibles.MESSAGE_CONSTRAINTS);
        }
        final PayDeductibles modelDeductibles = new PayDeductibles(deductibles);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final boolean modelFavourite = this.favourite;

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelRating, modelDepartment, modelManager,
                modelSalary, modelHours, modelRate, modelDeductibles, modelTags, modelFavourite);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedPerson)) {
            return false;
        }

        XmlAdaptedPerson otherPerson = (XmlAdaptedPerson) other;
        return Objects.equals(name, otherPerson.name)
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(email, otherPerson.email)
                && Objects.equals(address, otherPerson.address)
                && Objects.equals(rating, otherPerson.rating)
                && Objects.equals(department, otherPerson.department)
                && Objects.equals(manager, otherPerson.manager)
                && Objects.equals(salary, otherPerson.salary)
                && Objects.equals(hours, otherPerson.hours)
                && Objects.equals(rate, otherPerson.rate)
                && Objects.equals(deductibles, otherPerson.deductibles)
                && tagged.equals(otherPerson.tagged);
    }
}

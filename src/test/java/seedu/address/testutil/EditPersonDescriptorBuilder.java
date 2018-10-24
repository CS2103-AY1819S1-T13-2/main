package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
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
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setDepartment(person.getDepartment());
        descriptor.setManager(person.getManager());
        descriptor.setSalary(person.getSalary());
        descriptor.setHours(person.getOtHours());
        descriptor.setRate(person.getOtRate());
        descriptor.setDeductibles(person.getDeductibles());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDepartment(String department) {
        descriptor.setDepartment(new Department(department));
        return this;
    }

    /**
     * Sets the {@code Manager} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withManager(String manager) {
        descriptor.setManager(new Manager(manager));
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    /**
     * Sets the {@code OtHour} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHour(String hours) {
        descriptor.setHours(new OtHour(hours));
        return this;
    }

    /**
     * Sets the {@code OtRate} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRate(String rate) {
        descriptor.setRate(new OtRate(rate));
        return this;
    }

    /**
     * Sets the {@code PayDeductibles} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDeductibles(String deductibles) {
        descriptor.setDeductibles(new PayDeductibles(deductibles));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

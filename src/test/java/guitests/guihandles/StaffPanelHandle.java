package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;

import seedu.address.model.person.Person;

/**
 * Provides a handle to a staff display in the staff panel
 */
public class StaffPanelHandle extends NodeHandle<Node> {
    private static final String NAME_FIELD_ID = "#nameLabel";
    private static final String PHONE_FIELD_ID = "#phoneLabel";
    private static final String EMAIL_FIELD_ID = "#emailLabel";
    private static final String ADDRESS_FIELD_ID = "#addressLabel";
    private static final String SALARY_FIELD_ID = "#salaryLabel";
    private static final String DEPARTMENT_FIELD_ID = "#departmentLabel";
    private static final String MANAGER_FIELD_ID = "#managerLabel";
    private static final String RATING_FIELD_ID = "#ratingLabel";

    private final Label nameLabel;
    private final Label phoneLabel;
    private final Label emailLabel;
    private final Label addressLabel;
    private final Label salaryLabel;
    private final Label departmentLabel;
    private final Label managerLabel;
    private final Label ratingLabel;

    public StaffPanelHandle(Node staffNode) {
        super(staffNode);

        nameLabel = getChildNode(NAME_FIELD_ID);
        phoneLabel = getChildNode(PHONE_FIELD_ID);
        emailLabel = getChildNode(EMAIL_FIELD_ID);
        addressLabel = getChildNode(ADDRESS_FIELD_ID);
        salaryLabel = getChildNode(SALARY_FIELD_ID);
        departmentLabel = getChildNode(DEPARTMENT_FIELD_ID);
        managerLabel = getChildNode(MANAGER_FIELD_ID);
        ratingLabel = getChildNode(RATING_FIELD_ID);
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getPhone() {
        return phoneLabel.getText();
    }

    public String getEmail() {
        return emailLabel.getText();
    }

    public String getAddress() {
        return addressLabel.getText();
    }

    public String getSalary() {
        return salaryLabel.getText();
    }

    public String getDepartment() {
        return departmentLabel.getText();
    }

    public String getManager() {
        return managerLabel.getText();
    }

    public String getRating() {
        return ratingLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code person}.
     */
    public boolean equals(Person person) {
        return getName().equals(person.getName().fullName)
                && getPhone().equals(person.getPhone().value)
                && getEmail().equals(person.getEmail().value)
                && getAddress().equals(person.getAddress().value)
                && getSalary().equals(person.getSalary().salary)
                && getDepartment().equals(person.getDepartment().value)
                && getManager().equals(person.getManager().fullName)
                && getRating().equals(person.getRating().value);
    }
}

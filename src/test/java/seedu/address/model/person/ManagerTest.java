package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ManagerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Manager(null));
    }

    @Test
    public void constructor_invalidManager_throwsIllegalArgumentException() {
        String invalidManager = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Manager(invalidManager));
    }

    @Test
    public void isValidManager() {
        // null manager
        Assert.assertThrows(NullPointerException.class, () -> Manager.isValidManager(null));

        // invalid manager
        assertFalse(Manager.isValidManager("")); // empty string
        assertFalse(Manager.isValidManager(" ")); // spaces only
        assertFalse(Manager.isValidManager("^")); // only non-alphanumeric characters
        assertFalse(Manager.isValidManager("peter*")); // contains non-alphanumeric characters

        // valid manager
        assertTrue(Manager.isValidManager("peter jack")); // alphabets only
        assertTrue(Manager.isValidManager("12345")); // numbers only
        assertTrue(Manager.isValidManager("peter the 2nd")); // alphanumeric characters
        assertTrue(Manager.isValidManager("Capital Tan")); // with capital letters
        assertTrue(Manager.isValidManager("David Roger Jackson Ray Jr 2nd")); // long names
    }
}

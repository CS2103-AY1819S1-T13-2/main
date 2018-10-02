package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        Assert.assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid rating
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating(" ")); // spaces only
        assertFalse(Rating.isValidRating("91")); // more than 10
        assertFalse(Rating.isValidRating("rating")); // non-numeric
        assertFalse(Rating.isValidRating("9011p041")); // alphabets within digits
        assertFalse(Rating.isValidRating("9312 1534")); // spaces within digits

        // valid rating
        assertTrue(Rating.isValidRating("0")); // between 0 to 10
        assertTrue(Rating.isValidRating("10"));
        assertTrue(Rating.isValidRating("5"));
    }

    @Test
    public void equals() {
        Rating zeroRating = new Rating("0");
        Rating fullRating = new Rating("10");

        // same object -> returns true
        assertTrue(zeroRating.equals(zeroRating));

        // null -> returns false
        assertFalse(zeroRating.equals(null));

        // different type -> returns false
        assertFalse(zeroRating.equals("0"));

        // different rating -> returns false
        assertFalse(zeroRating.equals(fullRating));
    }
}

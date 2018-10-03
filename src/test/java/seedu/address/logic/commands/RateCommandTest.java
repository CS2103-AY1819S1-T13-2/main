package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;

public class RateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_throwsError() {
        Person personToRate = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RateCommand rateCommand = new RateCommand(INDEX_FIRST_PERSON, new Rating("5") );
        Assertions.assertThrows(CommandException.class, () -> rateCommand.execute(model, commandHistory));
    }

    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(INDEX_FIRST_PERSON, new Rating("1"));
        RateCommand rateSecondCommand = new RateCommand(INDEX_SECOND_PERSON, new Rating("10"));

        // same object -> returns true
        assertTrue(rateFirstCommand.equals(rateFirstCommand));

        // same values -> returns true
        RateCommand rateFirstCommandCopy = new RateCommand(INDEX_FIRST_PERSON, new Rating("1"));
        assertTrue(rateFirstCommand.equals(rateFirstCommandCopy));

        // different types -> returns false
        assertFalse(rateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rateFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(rateFirstCommand.equals(rateSecondCommand));
    }

}

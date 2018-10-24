package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Feedback;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class FeedbackCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        FeedbackCommand feedbackFirstCommand = new FeedbackCommand(INDEX_FIRST_PERSON, new Feedback("Good"));
        FeedbackCommand feedbackSecondCommand = new FeedbackCommand(INDEX_SECOND_PERSON, new Feedback("Bad"));

        // same object -> returns true
        assertTrue(feedbackFirstCommand.equals(feedbackFirstCommand));

        // same values -> returns true
        FeedbackCommand feedbackFirstCommandCopy = new FeedbackCommand(INDEX_FIRST_PERSON, new Feedback("Good"));
        assertTrue(feedbackFirstCommand.equals(feedbackFirstCommandCopy));

        // different types -> returns false
        assertFalse(feedbackFirstCommand.equals(1));

        // null -> returns false
        assertFalse(feedbackFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(feedbackFirstCommand.equals(feedbackSecondCommand));
    }
    @Test
    public void execute_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withFeedback("You are cool!").build();

        FeedbackCommand feedbackCommand = new FeedbackCommand(indexLastPerson, new Feedback("You are cool!"));

        String expectedMessage = String.format(FeedbackCommand.MESSAGE_FEEDBACK_PERSON_SUCCESS, editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updatePerson(lastPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(feedbackCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        FeedbackCommand feedbackCommand = new FeedbackCommand(outOfBoundIndex, new Feedback("Work harder."));

        assertCommandFailure(feedbackCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}

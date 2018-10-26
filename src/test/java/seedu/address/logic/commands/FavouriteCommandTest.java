package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
// import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
// import seedu.address.model.person.Person;
// import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code FavouriteCommand}.
 */
public class FavouriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    /*
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToFavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person favouritedPerson = new PersonBuilder().build();
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavouriteCommand.MESSAGE_FAVOURITE_PERSON_SUCCESS,
                favouritedPerson.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.favouritePerson(personToFavourite, favouritedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(favouriteCommand, model, commandHistory, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FavouriteCommand favouriteCommand = new FavouriteCommand(outOfBoundIndex);

        assertCommandFailure(favouriteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /*
    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToFavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person favouritedPerson = new PersonBuilder().build();
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavouriteCommand.MESSAGE_FAVOURITE_PERSON_SUCCESS,
                favouritedPerson.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.favouritePerson(personToFavourite, favouritedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(favouriteCommand, model, commandHistory, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        FavouriteCommand favouriteCommand = new FavouriteCommand(outOfBoundIndex);

        assertCommandFailure(favouriteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /*
    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Person personToFavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person favouritedPerson = new PersonBuilder().build();
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_PERSON);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.favouritePerson(personToFavourite, favouritedPerson);
        expectedModel.commitAddressBook();

        // delete -> first person favourited
        favouriteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first person favourited again
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
    */

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FavouriteCommand favouriteCommand = new FavouriteCommand(outOfBoundIndex);

        // execution failed -> address book state not added into model
        assertCommandFailure(favouriteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        FavouriteCommand favouriteFirstCommand = new FavouriteCommand(INDEX_FIRST_PERSON);
        FavouriteCommand favouriteSecondCommand = new FavouriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(favouriteFirstCommand.equals(favouriteFirstCommand));

        // same values -> returns true
        FavouriteCommand favouriteFirstCommandCopy = new FavouriteCommand(INDEX_FIRST_PERSON);
        assertTrue(favouriteFirstCommand.equals(favouriteFirstCommandCopy));

        // different types -> returns false
        assertFalse(favouriteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(favouriteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(favouriteFirstCommand.equals(favouriteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}

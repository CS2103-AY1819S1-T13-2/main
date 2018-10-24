package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Feedback;
import seedu.address.model.person.Person;


/**
 * Edits the feedback details of an existing person in the address book.
 */
public class FeedbackCommand extends Command {

    public static final String COMMAND_WORD = "feedback";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the feedback for the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing feedback will be overwritten by the input feedback.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_FEEDBACK + "FEEDBACK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "fb/5";

    public static final String MESSAGE_FEEDBACK_PERSON_SUCCESS = "Feedback updated for Person: %1$s";

    private final Index index;
    private final Feedback feedback;

    /**
     * @param index of the person in the filtered person list to edit
     */
    public FeedbackCommand(Index index, Feedback feedback) {
        requireNonNull(index);

        this.index = index;
        this.feedback = feedback;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRating(), personToEdit.getDepartment(),
                personToEdit.getManager(), personToEdit.getSalary(), personToEdit.getOtHours(),
                personToEdit.getOtRate(), personToEdit.getDeductibles(), feedback, personToEdit.getTags());

        model.updatePerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_FEEDBACK_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FeedbackCommand // instanceof handles nulls
                && index.equals(((FeedbackCommand) other).index)); // state check
    }
}

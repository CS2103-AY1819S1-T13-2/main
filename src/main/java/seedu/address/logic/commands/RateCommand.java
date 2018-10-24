package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;


/**
 * Edits the details of an existing person in the address book.
 */
public class RateCommand extends Command {

    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Rates the person identified "
            + "by the index number used in the displayed person list from 1 to 10. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RATING + "RATING]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/5";

    public static final String MESSAGE_RATING_PERSON_SUCCESS = "Rate updated for Person: %1$s";
    public static final String MESSAGE_RATING_UNCHANGED = "This person already has this rating.";

    private final Index index;
    private final Rating rating;

    /**
     * @param index of the person in the filtered person list to edit
     * @param rating of the person to edit
     */
    public RateCommand(Index index, Rating rating) {
        requireNonNull(index);

        this.index = index;
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.getRating().equals(rating)) {
            throw new CommandException(MESSAGE_RATING_UNCHANGED);
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), rating, personToEdit.getDepartment(), personToEdit.getManager(),
                personToEdit.getSalary(), personToEdit.getOtHours(), personToEdit.getOtRate(),
                personToEdit.getDeductibles(), personToEdit.getTags(), personToEdit.getFavourite());

        model.updatePerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_RATING_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RateCommand // instanceof handles nulls
                && index.equals(((RateCommand) other).index)); // state check
    }
}

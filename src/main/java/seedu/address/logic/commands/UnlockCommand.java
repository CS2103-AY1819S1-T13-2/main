package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unlocks the address book.
 */
public class UnlockCommand extends Command {

    public static final String COMMAND_WORD = "unlock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unlocks the address book with a password.\n"
            + "Parameters: PASSWORD\n"
            + "Example: " + COMMAND_WORD + "password123";

    public static final String MESSAGE_SUCCESS = "Address book is successfully unlocked.";

    private final String password;

    public UnlockCommand(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnlockCommand // instanceof handles nulls
                && this.password.equals(((UnlockCommand) other).password)); // state check
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }
}

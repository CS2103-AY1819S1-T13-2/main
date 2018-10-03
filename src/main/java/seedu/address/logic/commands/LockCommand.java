package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Locks the address book.
 */
public class LockCommand extends Command{

    public static final String COMMAND_WORD = "lock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Locks the address book with a password.\n"
            + "Parameters: PASSWORD (at least contains 4 characters)\n"
            + "Example: " + COMMAND_WORD + "password123";

    public static final String MESSAGE_SUCCESS = "Address book is successfully locked.";

    private final String password;

    public LockCommand(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LockCommand // instanceof handles nulls
                && this.password.equals(((LockCommand) other).password)); // state check
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }
}

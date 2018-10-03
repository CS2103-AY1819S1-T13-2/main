package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.commands.LockCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LockCommand object
 */
public class LockCommandParser implements Parser<LockCommand> {

    public static final String PARSE_EXCEPTION_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LockCommand.MESSAGE_USAGE);
    /**
     * Parses the given {@code args} of arguments in the context of the LockCommand
     * and returns an LockCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public LockCommand parse(String userInput) throws ParseException {
        return new LockCommand(userInput);
    }

}
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FeedbackCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Feedback;

/**
 * Parses input arguments and creates a new FeedbackCommand object
 */
public class FeedbackCommandParser implements Parser<FeedbackCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FeedbackCommand
     * and returns an FeedbackCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FeedbackCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FEEDBACK);

        Index index;
        String feedback;

        if (!isPrefixPresent(argMultimap, PREFIX_FEEDBACK) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FeedbackCommand.MESSAGE_USAGE));
        }
        feedback = argMultimap.getValue(PREFIX_FEEDBACK).get();
        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        return new FeedbackCommand(index, new Feedback(feedback));
    }

    /**
     * Returns true if prefix does not contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}

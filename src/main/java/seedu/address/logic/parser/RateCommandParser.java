package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Rating;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class RateCommandParser implements Parser<RateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RateCommand
     * and returns an RateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RATING);

        Index index;
        String rating;

        if (!isPrefixPresent(argMultimap, PREFIX_RATING) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
        }
        rating = argMultimap.getValue(PREFIX_RATING).get();
        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        return new RateCommand(index, new Rating(rating));
    }

    /**
     * Returns true if prefix does not contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}

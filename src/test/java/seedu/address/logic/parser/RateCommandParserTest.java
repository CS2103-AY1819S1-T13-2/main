package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.address.logic.commands.RateCommand;
import seedu.address.model.person.Rating;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RateCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the RateCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RateCommandParserTest {

    private RateCommandParser parser = new RateCommandParser();

    @Test
    public void parse_validArgs_returnsRateCommand() {
        assertParseSuccess(parser, "1 " + PREFIX_RATING + "5",
                new RateCommand(INDEX_FIRST_PERSON, new Rating("5")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a " + PREFIX_RATING + "5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
    }
}

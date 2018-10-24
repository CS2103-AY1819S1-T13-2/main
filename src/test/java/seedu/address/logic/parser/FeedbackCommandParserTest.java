package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.address.logic.commands.FeedbackCommand;
import seedu.address.model.person.Feedback;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the FeedbackCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the FeedbackCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class FeedbackCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FeedbackCommand.MESSAGE_USAGE);

    private FeedbackCommandParser parser = new FeedbackCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "1 " + PREFIX_FEEDBACK + "Great",
                new FeedbackCommand(INDEX_FIRST_PERSON, new Feedback("Great")));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PREFIX_FEEDBACK + "Sweet", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PREFIX_FEEDBACK + "Awesome", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing rating prefix
        assertParseFailure(parser, PREFIX_FEEDBACK + "Very good!", MESSAGE_INVALID_FORMAT);
    }

    /*@Test
    public void parse_invalidArgs_failure() {
        // larger than 10
        assertParseFailure(parser, PREFIX_FEEDBACK + "11", MESSAGE_INVALID_FORMAT);

        // non-numeric argument
        assertParseFailure(parser, PREFIX_FEEDBACK + "five", MESSAGE_INVALID_FORMAT);
    }*/
}

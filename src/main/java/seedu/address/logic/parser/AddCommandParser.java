package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIVATE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIVATE_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIVATE_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRIVATE_PHONE, PREFIX_PHONE,
                        PREFIX_PRIVATE_EMAIL, PREFIX_EMAIL, PREFIX_PRIVATE_ADDRESS, PREFIX_ADDRESS, PREFIX_TAG);

        if (argMultimap.getPreamble().isEmpty() && arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            if (arePrefixesPresent(argMultimap, PREFIX_PRIVATE_PHONE)
                    || arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
                if (arePrefixesPresent(argMultimap, PREFIX_PRIVATE_ADDRESS)
                        || arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
                    if (arePrefixesPresent(argMultimap, PREFIX_PRIVATE_EMAIL)
                            || arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
                    } else {
                        wrongFormat();
                    }
                } else {
                    wrongFormat();
                }
            } else {
                wrongFormat();
            }
        } else {
            wrongFormat();
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Phone phone;
        if (arePrefixesPresent(argMultimap, PREFIX_PRIVATE_PHONE)) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PRIVATE_PHONE).get());
            phone.setPrivate("Y");
        } else {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }

        Email email;
        if (arePrefixesPresent(argMultimap, PREFIX_PRIVATE_EMAIL)) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_PRIVATE_EMAIL).get());
            email.setPrivate("Y");
        } else {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        Address address;
        if (arePrefixesPresent(argMultimap, PREFIX_PRIVATE_ADDRESS)) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_PRIVATE_ADDRESS).get());
            address.setPrivate("Y");
        } else {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, address, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Throws a ParseException to tell user that the command format was entered wrongly.
     * @throws ParseException
     */
    public static void wrongFormat() throws ParseException {
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

}

package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

/** Indicates the AddressBook in the model has changed*/
public class UnlockEvent extends BaseEvent {

    public final String password;

    public UnlockEvent(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "dia ngirim password " + password;
    }
}

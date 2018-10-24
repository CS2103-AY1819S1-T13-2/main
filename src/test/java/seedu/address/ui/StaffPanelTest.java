package seedu.address.ui;

import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.ui.testutil.GuiTestAssert.assertPanelDisplaysPerson;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.StaffPanelHandle;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Person;

public class StaffPanelTest extends GuiUnitTest {
    private PersonPanelSelectionChangedEvent selectionChangeEventStub;

    private StaffPanel staffPanel;

    @Before
    public void setUp() {
        selectionChangeEventStub = new PersonPanelSelectionChangedEvent(ALICE);

        guiRobot.interact(() -> staffPanel = new StaffPanel());
        uiPartRule.setUiPart(staffPanel);
    }

    @Test
    public void display() throws Exception {
        postNow(selectionChangeEventStub);
        assertPanelDisplay(ALICE, staffPanel);
    }

    /**
     * Asserts that {@code staffPanel} displays the details of {@code expectedPerson} correctly
     */
    private void assertPanelDisplay(Person expectedPerson, StaffPanel staffPanel) {
        guiRobot.pauseForHuman();
        StaffPanelHandle staffPanelHandle = new StaffPanelHandle(staffPanel.getRoot());

        assertPanelDisplaysPerson(expectedPerson, staffPanelHandle);
    }
}

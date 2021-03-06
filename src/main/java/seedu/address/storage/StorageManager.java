package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.SsenisubChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySsenisub;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Ssenisub data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SsenisubStorage ssenisubStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(SsenisubStorage ssenisubStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.ssenisubStorage = ssenisubStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Ssenisub methods ==============================

    @Override
    public Path getSsenisubFilePath() {
        return ssenisubStorage.getSsenisubFilePath();
    }

    @Override
    public Optional<ReadOnlySsenisub> readSsenisub() throws DataConversionException, IOException {
        return readSsenisub(ssenisubStorage.getSsenisubFilePath());
    }

    @Override
    public Optional<ReadOnlySsenisub> readSsenisub(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ssenisubStorage.readSsenisub(filePath);
    }

    @Override
    public void saveSsenisub(ReadOnlySsenisub ssenisub) throws IOException {
        saveSsenisub(ssenisub, ssenisubStorage.getSsenisubFilePath());
    }

    @Override
    public void saveSsenisub(ReadOnlySsenisub ssenisub, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ssenisubStorage.saveSsenisub(ssenisub, filePath);
    }


    @Override
    @Subscribe
    public void handleSsenisubChangedEvent(SsenisubChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveSsenisub(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}

package ua.edu.ucu.apps.task2;

import java.util.Optional;

public interface LocalCache {
    Optional<String> retrieveFromCache(Document doc);

    void saveToCache(Document doc, String result);
}

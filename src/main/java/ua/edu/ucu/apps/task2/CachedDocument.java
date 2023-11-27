package ua.edu.ucu.apps.task2;

import java.util.Optional;

public class CachedDocument implements Document {
    private final Document doc;
    private final LocalCache cache;

    public CachedDocument(Document doc, LocalCache cache) {
        this.doc = doc;
        this.cache = cache;
    }

    @Override
    public String parse() {
        Optional<String> cachedResult = cache.retrieveFromCache(doc);
        if (cachedResult.isPresent()) {
            System.out.println("Successfully retrieved from cache");
            return cachedResult.get();
        } else {
            String parsedResult = doc.parse();
            cache.saveToCache(doc, parsedResult);
            return parsedResult;
        }
    }

    public static void main(String[] args) {
        Document smartDocument = new SmartDocument("your_gcs_path");
        LocalCache localCache = new SQLiteLocalCache();

        CachedDocument cachedDocument = new CachedDocument(smartDocument, localCache);

        String result = cachedDocument.parse();
        System.out.println("Final result: " + result);
    }
}

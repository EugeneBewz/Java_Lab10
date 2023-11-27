package ua.edu.ucu.apps.task2;

public class TimedDocument implements Document{
    private final Document doc;

    public TimedDocument(Document doc) {
        this.doc = doc;
    }

    @Override
    public String parse() {
        long startTime = System.nanoTime();
        String result = doc.parse();
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;

        System.out.println("Time consumed: " + elapsedTime + " ns.");
        return result;
    }
}

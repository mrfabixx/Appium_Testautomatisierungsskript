package UI;

public class Timer {
    private long startTime;
    private long endTime;


    // Startet die Zeitmessung
    public void start() {
        startTime = System.currentTimeMillis();
    }

    // Beendet die Zeitmessung und gibt die Dauer in Sekunden aus
    public void stopAndPrintDuration() {
        endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double durationInSeconds = duration / 1000.0;
        System.out.println(String.format("Dauer: %.2f Sekunden", durationInSeconds));
    }

}

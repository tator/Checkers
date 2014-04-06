
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.2.3
 */
public class fb {

    private boolean p = true;
    private Formatter x, y;
    private int hist;
    private static String holding = "";

    public fb() {
    }

    public void history(int h) {
        hist = h;
        try {
            File l = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\History\\Hist_" + (hist - 10) + ".txt");
            if (l.exists()) {
                l.delete();
            }
        } catch (Exception e) {
        }
    }

    public void addRecord(String k, int n, int m, int b, int c) {
        holding += String.format("\n"
                + "%s\tfrom\t%d,%d \tto\t%d, %d", k, n, m, c, b);
    }

    public int getHist() {
        return hist;
    }

    public void close() {
        try {
            x = new Formatter(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Hist.txt");
            x.format("%d", (hist + 1));
            x.close();
        } catch (FileNotFoundException e) {
            throw new Error("failed to save Hist.txt");
        }
        try {
            y = new Formatter(new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\History\\Hist_" + hist + ".txt"));
            y.format("%s", holding);
            y.close();
        } catch (FileNotFoundException e) {
            throw new Error("failed to save Hist_" + hist + ".txt");
        }

    }
}

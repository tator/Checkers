
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;


/**
 *
 * <strong>Author:</strong> Tator
 * <p>
 * <strong>Version:</strong> win.1.1
 */
public class fa {

    private Scanner z;
    private Formatter x, y;
    private int h = 0;
    public fb fb;
    
    public void open() {
        File t = new File(System.getProperty("user.home") +"\\AppData\\Roaming\\Checker");
        if (!t.exists()) {
            File dir = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\History");
            dir.mkdirs();
        }
        try {
            z = new Scanner(new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Hist.txt"));
            h = z.nextInt();
            fb = new fb();
            fb.history(h);
        } catch (FileNotFoundException e) {
            try {
                x = new Formatter(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Hist.txt");
                x.format("%d", h);
                fb = new fb();
                fb.history(h);
                x.close();
            } catch (FileNotFoundException q) {
                //throw new Error("Hist.txt failed to load and/or create");
                cb.fileError = true;
            }
        }
    }

    public void close() {
        try {
            x = new Formatter(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Hist.txt");
            x.format("%d", (h + 1));
            x.close();
        } catch (FileNotFoundException e) {
            throw new Error("failed to save Hist.txt");
        }
        try {
            z.close();
        } catch (Exception e) {
        }
    }
}

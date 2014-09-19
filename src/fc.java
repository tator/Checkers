
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
public class fc {

    private Scanner zz;
    private Formatter zx;
    public static int time_click, piece_move;

    public void open() {
        try {
            zz = new Scanner(new File(System.getProperty("user.home") + "\\Appdata\\Roaming\\Checker\\times.txt"));
            String q = zz.nextLine();
            time_click = Integer.parseInt(q.substring(16));
            String w = zz.nextLine();
            piece_move = Integer.parseInt(w.substring(15));
        } catch (FileNotFoundException e) {
            try {
                zx = new Formatter(System.getProperty("user.home") + "\\Appdata\\Roaming\\Checker\\Times.txt");
                time_click = 0;
                piece_move = 0;
                zx.format("%s", " times_clicked: ");
                zx.format("%s\n", time_click);
                zx.format("%s", " pieces_moved: ");
                zx.format("%s\n", piece_move);
                zx.close();
            } catch (FileNotFoundException q) {
                cb.fileError = true;
            }
        }
    }

    public int getTimeClicked() {
        return time_click;
    }

    public int getPieceMoved() {
        return piece_move;
    }

    public void timeClicked() {
        time_click++;
    }

    public void pieceMoved() {
        piece_move++;
    }

    public void close() {
        try {
            zx = new Formatter(System.getProperty("user.home") + "\\Appdata\\Roaming\\Checker\\Times.txt");
            zx.format("%s", " times_clicked: ");
            zx.format("%s\n", time_click);
            zx.format("%s", " pieces_moved: ");
            zx.format("%s\n", piece_move);
            zx.close();
        } catch (FileNotFoundException q) {
            throw new Error("times.txt failed to save");
        }

        try {
            zz.close();
        } catch (Exception e) {
        }
    }
}

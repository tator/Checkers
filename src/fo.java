
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *<strong>author:<\strong> Tator
 * 
 */
public class fo {

    String name;
    private Scanner x;

    public fo(cb cb) {
        do {
            name = JOptionPane.showInputDialog(null, "What is your name?");
            if (name == null) {
                return;
            }
        } while (name.length() == 0);
        cb.ch.toggleVisiblity(true);
        try {
            x = new Scanner(new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Saves\\" + name.toLowerCase() + ".txt"));
            for (int a = 0; a < 4; a++) {
                boolean[][] hold = new boolean[8][8];
                for (int q = 0; q < 8; q++) {
                    for (int w = 0; w < 8; w++) {
                        int p = x.nextInt();
                        hold[q][w] = p == 1;
                    }
                }
                switch (a) {
                    case 0:
                        cb.setBlackPiece(hold);
                        break;
                    case 1:
                        cb.setRedPiece(hold);
                        break;
                    case 2:
                        cb.setBlackKing(hold);
                        break;
                    case 3:
                        cb.setRedKing(hold);
                        break;
                }
            }
            cb.setBlackDouble(x.nextInt() == 1);
            cb.setTurn(x.nextInt() == 1, x.nextInt() == 1);
            cb.setRedDouble(x.nextInt() == 1);
            cb.repaint();
            x.close();
        } catch (FileNotFoundException q) {
            //throw new Error("Hist.txt failed to load and/or create");
            cb.fileError = true;
        }
    }
}

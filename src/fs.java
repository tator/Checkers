
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 738493
 */
public class fs {

    String name;
    private Formatter x;

    public fs(cb cb) {
        do {
            name = JOptionPane.showInputDialog(null, "What is your name?\n\nYou well need to reenter this name to resume this game.").toLowerCase();
        } while (name == null);
        File t = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Saves");
        if (!t.exists()) {
            File dir = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\Saves");
            dir.mkdirs();

        }
        try {
            x = new Formatter(System.getProperty("user.home") + "\\AppData\\Roaming\\Checker\\" + name + ".txt");
            for (int a = 0; a < 4; a++) {
                boolean[][] hold;
                switch(a){
                    case 0:
                        hold = cb.getBlackPiece();
                        break;
                    case 1:
                        hold = cb.getRedPiece();
                        break;
                    case 2:
                        hold = cb.getBlackKing();
                        break;
                    case 3:
                        hold = cb.getRedKing();
                        break;
                    default:
                        hold = new boolean[8][8];
                        break;
                }
                for(boolean[] q : hold){
                    for(boolean w :q){
                        x.format("%d",(w)?1:0);
                    }
                }
            }
            x.close();
        } catch (FileNotFoundException q) {
            //throw new Error("Hist.txt failed to load and/or create");
            cb.fileError = true;
        }
    }

}

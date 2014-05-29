
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;
import javax.swing.AbstractButton;
import javax.swing.JApplet;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.3
 */
public class checker extends JApplet implements WindowListener {

    cb cb;
    ca ca;
    JFrame frame;
    JFrame start;
    private static int height;
    private static int width;
    private JMenu gameMenu;
    private JMenuBar menuBar;
    private JMenuItem menuItem;
    private JCheckBoxMenuItem checkBox;

    public static boolean gameGoing;
    private JMenu helpMenu;
    @Override
    public void init() {
        //cb = new cb(); 
        //getContentPane().add(new cb()); 

        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        //setLocation(new Point(Toolkit.getDefaultToolkit().getScreenSize().height/2,Toolkit.getDefaultToolkit().getScreenSize().width/2));

        frame = new JFrame();
        frame.setVisible(false);
        frame.setSize(840, 840);
        frame.setLocation(100, 100);
        frame.setTitle("Checkers");

        Container contentpane = frame.getContentPane();
        contentpane.add(cb = new cb(this));
        cb.requestFocus();
        frame.addWindowListener(this);

        menuBar = new JMenuBar();

        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        menuItem = new JMenuItem("Restart");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cb.start();
            }
        });

        gameMenu.add(menuItem);
        gameMenu.addSeparator();

        menuItem = new JMenuItem("Pause");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_P)));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cb.pause.paused();
            }
        });

        gameMenu.add(menuItem);
        menuItem = new JMenuItem("Satatistics");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Statistics: \n"
                        + "Times Played : " +cb.fa.fb.getHist()+"\n"
                        + "Times clicked: "+cb.fc.getTimeClicked()+"\n"
                        + "Peaces moved : "+cb.fc.getPieceMoved()+"\n"
                        + "More coming soon");
            }
        });
        gameMenu.add(menuItem);
        gameMenu.addSeparator();
        checkBox = new JCheckBoxMenuItem("Turn logic off");
        checkBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean turn = ((AbstractButton)e.getSource()).getModel().isSelected();
                if(turn){
                    cb.offTurn();
                }
            }
        });
        
        gameMenu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_ESCAPE)));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleVisiblity();
            }
        });

        gameMenu.add(menuItem);

        menuBar.add(gameMenu);

        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        start = new JFrame();
        start.setSize(400, 300);
        start.setTitle("Checkers");
        //Container content = start.getContentPane();
        //content.add(ca = new ca(this));

        getContentPane().add(start.add(ca = new ca(this)));

    }

    public void toggleVisiblity() {
        if (frame.isVisible()) {
            ca.start();
            frame.setVisible(false);
        } else {
            cb.start();
            frame.setSize(840, 840);
            frame.setVisible(true);
        }
    }

    public void closeFile() {
        cb.fa.close();
        cb.fc.close();
        cb.fa.fb.close();
    }

    @Override
    public void destroy() {
        cb.fa.close();
        cb.fc.close();
        cb.fa.fb.close();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        ca.start();
        cb.pause.setOff();
        closeFile();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

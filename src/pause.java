
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 *<strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.10
 */
public class pause extends JFrame {

    cb cb;
    private JButton resume, restart, save, quit;

    private JLabel title;
    private final FlowLayout layout;
    private final Container container;

    public pause(final cb cb) {
        super("Paused");
        this.cb = cb;
        layout = new FlowLayout();
        container = getContentPane();
        setLayout(layout);
        title = new JLabel("**PAUSE**");
        add(title);
        resume = new JButton("Resume");
        add(resume);
        resume.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setOff();
                    }
                });
        restart = new JButton("Restart");
        add(restart);
        restart.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cb.ch.closeFile();
                        cb.start(false);
                        setOff();
                    }
                });
        save = new JButton("Save");
        add(save);
        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "I'm sorry, but the diveloper is still"
                                        + " working on this function.");
                        setOff();
                    }
                });
        quit = new JButton("Exit");
        add(quit);
        quit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setOff();
                        cb.ch.toggleVisiblity(true);
                    }
                });

    }

    public void paused() {
        this.setSize(100, 200);
        setLocation(
                (((cb.ch.frame.getSize().width / 2)
                + cb.ch.frame.getLocation().x) - this.getSize().width / 2),
                (((cb.ch.frame.getSize().height / 2)
                + cb.ch.frame.getLocation().y) - this.getSize().height / 2)
        );
        setOn();
    }

    public void setOff() {
        this.setVisible(false);
    }

    public void setOn() {
        this.setVisible(true);
    }
}

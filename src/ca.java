
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.2
 */
public class ca extends JPanel implements MouseListener, Runnable {

    public cb cb;
    public checker ch;
    private JButton saved;
    public boolean active = true;
    private boolean flip = true;
    Thread thread;

    public ca(checker ch) {
        this.ch = ch;
        addMouseListener(this);
        saved = new JButton("Resumed saved game");
        add(saved);
        saved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "I'm sorry, but the diveloper is still working on this function.");
            }
        });

        start();
    }

    public void start() {
        active = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (active) {
            flip = !flip;
            repaint();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.white);
        int w = getWidth();
        int h = getHeight();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        String str = "CLICK ANYWHERE TO BEGIN";
        String a = "CHECKERS";
        g.drawString(str, (int) (w - g.getFontMetrics().stringWidth(str) - 5), (int) (h - 5));
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        if (!flip) {
            g.setColor(Color.RED);
        }
        g.drawString(a, 30, 100);
        g.setColor(Color.red);
        if (!flip) {
            g.setColor(Color.BLACK);
        }
        g.drawString("C", 30, 100);
        g.drawString("E", 74, 100);
        g.drawString("K", 117, 100);
        g.drawString("R", 158, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        active = false;
        thread.stop();
        ch.toggleVisiblity();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

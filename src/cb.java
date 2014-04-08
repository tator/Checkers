import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.10
 */
public class cb extends JPanel implements MouseListener, MouseMotionListener, KeyListener, Runnable {

    public fa fa;
    public fc fc;
    public pause pause;
    public checker ch;
    private Point point1 = null, point2 = null, point3 = null, newKing = null;
    private Color yellow;
    Thread thread;
    private int smallest, moveX = 0, moveY = 0;
    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    public static boolean fileError = false;
    private char newKingT = '*';
    private boolean[][] blackPiece = {
        {true, false, true, false, true, false, true, false},
        {false, true, false, true, false, true, false, true},
        {true, false, true, false, true, false, true, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false}
    };

    private boolean[][] redPiece = {
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, true, false, true, false, true, false, true},
        {true, false, true, false, true, false, true, false},
        {false, true, false, true, false, true, false, true}
    };
    private boolean[][] blackKing = new boolean[8][8];
    private boolean[][] redKing = new boolean[8][8];
    private boolean moving = false, blackMoving = false,
            redMoving = false, blackKingMoving = false,
            redKingMoving = false;

    public cb(checker ch) {
        this.ch = ch;
        checker.gameGoing = true;
        fa = new fa();
        fc = new fc();
        pause = new pause(this);
        thread = new Thread(this);
        fa.open();
        fc.open();
        if (fileError) {
            fa = new fd();
            fc = new ff();
            fa.open();
            fc.open();
        }
        yellow = Color.YELLOW;
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setSize(840, 840);
        repaint();
    }

    public void start() {
        blackPiece = new boolean[][]{
            {true, false, true, false, true, false, true, false},
            {false, true, false, true, false, true, false, true},
            {true, false, true, false, true, false, true, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false}
        };
        redPiece = new boolean[][]{
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, true, false, true, false, true, false, true},
            {true, false, true, false, true, false, true, false},
            {false, true, false, true, false, true, false, true}
        };
        blackKing = new boolean[8][8];
        redKing = new boolean[8][8];
        moving = false;
        blackMoving = false;
        redMoving = false;
        blackKingMoving = false;
        redKingMoving = false;
        checker.gameGoing = true;
        fileError = false;
        newKingT = '*';
        moveX = 0;
        moveY = 0;
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        fa = new fa();
        fc = new fc();
        fa.open();
        fc.open();
        if (fileError) {
            fa = new fd();
            fc = new ff();
            fa.open();
            fc.open();
        }
        setSize(840, 840);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.lightGray);
        int width = getSize().width;
        int height = getSize().height;
        smallest = Math.min(width, height);

        if (newKing != null) {
            System.out.println("one");
        }

        for (int q = 0; q <= 7; q++) {
            for (int w = 0; w <= 7; w++) {
                if ((q + w) % 2 == 1) {
                    g.setColor(Color.darkGray);
                } else {
                    g.setColor(Color.white);
                }
                g.fillRect(
                        ((((smallest - 2 * (smallest / 42))) / 8) * w)
                        + (smallest / 42),
                        ((((smallest - 2 * (smallest / 42))) / 8) * q)
                        + (smallest / 42),
                        ((smallest - 2 * (smallest / 42)) / 8),
                        ((smallest - 2 * (smallest / 42)) / 8)
                );
            }
        }
        for (int q = 0; q <= 7; q++) {
            for (int w = 0; w <= 7; w++) {
                if (newKing != null) {
                    System.out.print("two " + q + " " + w);
                }
                if (blackPiece[q][w]) {
                    if (newKing != null) {
                        System.out.println(" black");
                    }
                    g.setColor(Color.black);
                } else if (redPiece[q][w]) {

                    
                    if (newKing != null) {
                        System.out.println(" red");
                    }
                    g.setColor(Color.red);
                } else {
                    if (newKing != null) {
                        System.out.println();
                    }
                }
                if (blackPiece[q][w] || redPiece[q][w]) {
                    if (newKing != null) {
                        System.out.println("paint piece");
                    }
                    g.fillOval(
                            ((((smallest - 2 * (smallest / 42)) / 8) * w)
                            + (smallest / 28)),
                            ((((smallest - 2 * (smallest / 42)) / 8) * q)
                            + (smallest / 28)),
                            ((smallest - 2 * (smallest / 42)) / 10),
                            ((smallest - 2 * (smallest / 42)) / 10)
                    );
                }
                if (blackKing[q][w] || redKing[q][w]) {
                    if (newKing != null) {
                        System.out.println("paint king");
                    }
                    g.setColor(yellow);
                    g.fillOval(
                            (int) ((((smallest - 2 * (smallest / 42)) / 8)
                            * w) + (smallest / 16.8)),
                            (int) ((((smallest - 2 * (smallest / 42)) / 8)
                            * q) + (smallest / 16.8)),
                            ((smallest - 2 * (smallest / 42)) / 20),
                            ((smallest - 2 * (smallest / 42)) / 20)
                    );
                }
            }
        }
        if (newKing != null) {
            System.out.println("three");
        }
        int d = 20;
        double a = (Math.PI / 2.0) / d;
        if (newKing != null) {
            thread.start();
            for (int t = 0; t <= d; t++) {
                g.setColor(yellow);
                g.fillOval(
                        (int) (((((smallest - 2 * (smallest / 42)) / 8)
                        * newKing.x) + (smallest / 16.8) + ((smallest
                        - 2 * (smallest / 42)) / 40)
                        - (Math.sin(a * t) * (smallest - 2
                        * (smallest / 42)) / 40))),
                        (int) ((((smallest - 2 * (smallest / 42)) / 8)
                        * newKing.y) + (smallest / 16.8)),
                        (int) ((Math.sin(a * t) * (smallest - 2
                        * (smallest / 42)) / 20)),
                        ((smallest - 2 * (smallest / 42)) / 20)
                );
                System.out.println("Oval " + t + " (" + newKing.x + "," + newKing.y + ")" + "\nV:"
                        + smallest + "\nX: "
                        + (int) (((((smallest - 2 * (smallest / 42.0)) / 8.0)
                        * (5)) + (smallest / 16.8) + ((smallest - 2
                        * (smallest / 42)) / 40)
                        - (Math.sin(a * t) * (smallest - 2
                        * (smallest / 42)) / 40))) + "\nY: "
                        + (int) ((((smallest - 2 * (smallest / 42)) / 8)
                        * (5)) + (smallest / 16.8)) + "\nW: "
                        + (int) ((Math.sin(a * t) * (smallest - 2
                        * (smallest / 42)) / 20)) + "\nH: "
                        + ((smallest - 2 * (smallest / 42)) / 20) + "\nSIN: "
                        + Math.sin(a * t) + "\nSIN & 1/2 width: "
                        + Math.sin(a * t) * ((smallest - 2
                        * (smallest / 42)) / 40) + "\n");
                try {
                    thread.wait(50);
                } catch (Exception e) {
                    System.out.println("Error on thread");
                }
            }
            thead.stop();
            if (newKingT == 'b') {
                blackKing[newKing.y][newKing.x] = true;
                newKingT = '*';
                newKing = null;
            }
            if (newKingT == 'r') {
                redKing[newKing.y][newKing.x] = true;
                newKingT = '*';
                newKing = null;
            }
        }
        if (newKing != null) {
            System.out.println("four");
        }
        if (moving) {
            if (blackMoving) {
                g.setColor(Color.black);
            } else if (redMoving) {
                g.setColor(Color.red);
            }
            g.fillOval(
                    (point3.x - ((smallest - 2 * (smallest / 42)) / 10) / 2),
                    (point3.y - ((smallest - 2 * (smallest / 42)) / 10) / 2),
                    ((smallest - 2 * (smallest / 42)) / 10),
                    ((smallest - 2 * (smallest / 42)) / 10)
            );
            if (blackKingMoving || redKingMoving) {
                g.setColor(yellow);
                g.fillOval(
                        (point3.x - ((smallest - 2 * (smallest / 42)) / 20) / 2),
                        (point3.y - ((smallest - 2 * (smallest / 42)) / 20) / 2),
                        ((smallest - 2 * (smallest / 42)) / 20),
                        ((smallest - 2 * (smallest / 42)) / 20)
                );
            }
        }
    }

    @Override
    public void run() {

    }

    public void move(int x1, int y1, int x2, int y2) {
        if (!playing()) {
            return;
        }
        if (redPiece[y2][x2] || blackPiece[y2][x2]) {
        } else {
            if (blackPiece[y1][x1]) {
                if (!blackKing[y1][x1]) {
                    if (blackPieceMovementL(x1, y1, x2, y2)) {
                        if (blackPieceL(x1, y1, x2, y2)) {
                            fa.fb.addRecord("Black move", x1, y1, x2, y2);
                            fc.pieceMoved();
                            blackMove(x1, y1, x2, y2);
                        } else if (blackPieceJumpL(x1, y1, x2, y2, true)) {
                            fa.fb.addRecord("Black jump", x1, y1, x2, y2);
                            fc.pieceMoved();
                            blackJumpMove(x1, y1, true);
                        } else if (blackPieceJumpL(x1, y1, x2, y2, false)) {
                            fa.fb.addRecord("Black jump", x1, y1, x2, y2);
                            fc.pieceMoved();
                            blackJumpMove(x1, y1, false);
                        }
                        if (y2 == 7 && !blackKing[y2][x2]) {
                            newKing = new Point(x2, y2);
                            newKingT = 'b';
                        }
                    }
                } else if (blackKing[y1][x1]) {
                    if (allKingMoveL(x1, y1, x2, y2)) {
                        fa.fb.addRecord("Black king move", x1, y1, x2, y2);
                        fc.pieceMoved();
                        blackKing(x1, y1, x2, y2);
                    } else if (allKingJumpL(x1, y1, x2, y2)) {
                        fa.fb.addRecord("Black king jump", x1, y1, x2, y2);
                        fc.pieceMoved();
                        blackKingJumpMove(x1, y1, allKingLeftL(y1, y2), allKingDownL(x1, x2));
                    }
                }
            } else if (redPiece[y1][x1]) {
                if (!redKing[y1][x1]) {
                    if (redPieceMovementL(x1, y1, x2, y2)) {
                        if (redPieceL(x1, y1, x2, y2)) {
                            fa.fb.addRecord("Red move", x1, y1, x2, y2);
                            fc.pieceMoved();
                            redMove(x1, y1, x2, y2);
                        } else if (redPieceJumpL(x1, y1, x2, y2, false)) {
                            fa.fb.addRecord("Red jump", x1, y1, x2, y2);
                            fc.pieceMoved();
                            redJumpMove(x1, y1, false);
                        } else if (redPieceJumpL(x1, y1, x2, y2, true)) {
                            fa.fb.addRecord("Red jump", x1, y1, x2, y2);
                            fc.pieceMoved();
                            redJumpMove(x1, y1, true);
                        }
                        if (y2 == 0 && !redKing[y2][x2]) {
                            newKing = new Point(x2, y2);
                            newKingT = 'r';
                        }
                    }
                } else if (redKing[y1][x1]) {
                    if (allKingMoveL(x1, y1, x2, y2)) {
                        fa.fb.addRecord("Red king move", x1, y1, x2, y2);
                        fc.pieceMoved();
                        redKing(x1, y1, x2, y2);
                    } else if (allKingJumpL(x1, y1, x2, y2)) {
                        fa.fb.addRecord("Red king jump", x1, y1, x2, y2);
                        fc.pieceMoved();
                        redKingJumpMove(x1, y1, allKingLeftL(y1, y2), allKingDownL(x1, x2));
                    }
                }
            }
        }
    }

    public boolean bbscan(int x, int y, boolean[][] a) {
        if (x == 0 && y == 0 && a[x][y] == false) {
            return false;
        }
        if (a[x][y] == true) {
            return true;
        }
        if (y == 0) {
            y = 8;
            x--;
        }
        return bbscan(x, y - 1, a);
    }

    public boolean blackPieceScan() {
        for (boolean[] a : blackPiece) {
            for (boolean b : a) {
                if (b == true) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean redPieceScan() {
        for (boolean[] a : redPiece) {
            for (boolean b : a) {
                if (b == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean playing() {
        return (blackPieceScan() && redPieceScan());
    }

    public boolean allKingMoveL(int x1, int y1, int x2, int y2) {
        return ((y2 == y1 + 1 && x2 == x1 + 1)
                || (y2 == y1 + 1 && x2 == x1 - 1)
                || (y2 == y1 - 1 && x2 == x1 - 1)
                || (y2 == y1 - 1 && x2 == x1 + 1));
    }

    public boolean allKingJumpL(int x1, int y1, int x2, int y2) {
        return ((y2 == y1 - 2 || y2 == y1 + 2)
                && (x2 == x1 + 2 || x2 == x1 - 2));
    }

    public void blackKingJumpMove(int x1, int y1, boolean l, boolean d) {
        int L = 0, D = 0;
        if (d) {
            D = 1;
        } else if (!d) {
            D = -1;
        }
        if (l) {
            L = -1;
        } else if (!l) {
            L = 1;
        }
        if (redPiece[y1 + D][x1 + L]) {
            blackKing[y1][x1] = false;
            blackPiece[y1][x1] = false;
            redPiece[y1 + D][x1 + L] = false;
            if (redKing[y1 + D][x1 + L]) {
                redKing[y1 + D][x1 + L] = false;
            }
            blackPiece[y1 + D + D][x1 + L + L] = true;
            blackKing[y1 + D + D][x1 + L + L] = true;
        }
    }

    public boolean allKingDownL(int y1, int y2) {
        return y1 == (y2 - 2);
    }

    public boolean allKingLeftL(int x1, int x2) {
        return x2 == (x1 - 2);
    }

    public boolean blackPieceMovementL(int x1, int y1, int x2, int y2) {
        return ((y2 == y1 + 1 || y2 == y1 + 2) && (x2 == x1 - 1 || x2 == x1 - 2
                || x2 == x1 + 1 || x2 == x1 + 2));
    }

    public boolean blackPieceJumpL(int x1, int y1, int x2, int y2, boolean l) {
        if (l) {
            return ((!(y1 >= 6 || x1 <= 1)
                    && (redPiece[y1 + 1][x1 - 1]))
                    && (y2 == y1 + 2 && x2 == x1 - 2));
        } else {
            return ((!(y1 >= 6 || x1 >= 6)
                    && (redPiece[y1 + 1][x1 + 1]))
                    && (y2 == y1 + 2 && x2 == x1 + 2));
        }
    }

    public boolean blackPieceL(int x1, int y1, int x2, int y2) {
        return ((y2 == y1 + 1 && x2 == x1 + 1)
                || (y2 == y1 + 1 && x2 == x1 - 1));
    }

    public void blackMove(int x1, int y1, int x2, int y2) {
        blackPiece[y1][x1] = false;
        blackPiece[y2][x2] = true;
    }

    public void blackKing(int x1, int y1, int x2, int y2) {
        blackPiece[y1][x1] = false;
        blackPiece[y2][x2] = true;
        blackKing[y1][x1] = false;
        blackKing[y2][x2] = true;

    }

    public void blackJumpMove(int x, int y, boolean left) {
        if (left) {
            blackPiece[y][x] = false;
            if (redKing[y + 1][x - 1]) {
                redKing[y + 1][x - 1] = false;
            }
            redPiece[y + 1][x - 1] = false;
            blackPiece[y + 2][x - 2] = true;
        } else {
            blackPiece[y][x] = false;
            if (redKing[y + 1][x + 1]) {
                redKing[y + 1][x + 1] = false;
            }
            redPiece[y + 1][x + 1] = false;
            blackPiece[y + 2][x + 2] = true;
        }
    }

    public void redJumpMove(int x, int y, boolean left) {
        if (left) {
            redPiece[y][y] = false;
            if (blackKing[y - 1][x - 1]) {
                blackKing[y - 1][x - 1] = false;
            }
            blackPiece[y - 1][x - 1] = false;
            redPiece[y - 2][x - 2] = true;
        } else {
            redPiece[y][y] = false;
            if (blackKing[y - 1][x + 1]) {
                blackKing[y - 1][x + 1] = false;
            }
            blackPiece[y - 1][x + 1] = false;
            redPiece[y - 2][x + 2] = true;
        }
    }

    public void redKingJumpMove(int x1, int y1, boolean l, boolean d) {
        int L = 0, D = 0;
        if (d) {
            D = 1;
        } else if (!d) {
            D = -1;
        }
        if (l) {
            L = -1;
        } else if (!l) {
            L = 1;
        }
        if (blackPiece[y1 + D][x1 + L]) {
            redKing[y1][x1] = false;
            redPiece[y1][x1] = false;
            blackPiece[y1 + D][x1 + L] = false;
            if (blackKing[y1 + D][x1 + L]) {
                blackKing[y1 + D][x1 + L] = false;
            }
            redPiece[y1 + D + D][x1 + L + L] = true;
            redKing[y1 + D + D][x1 + L + L] = true;
        }
    }

    public boolean redPieceMovementL(int x1, int y1, int x2, int y2) {
        return ((y2 == y1 - 1 || y2 == y1 - 2)
                && (x2 == x1 - 1 || x2 == x1 - 2
                || x2 == x1 + 1 || x2 == x1 + 2));
    }

    public boolean redPieceJumpL(int x1, int y1, int x2, int y2, boolean l) {
        if (l) {
            return ((!(y1 <= 1 || x1 <= 1)
                    && (blackPiece[y1 - 1][x1 - 1]))
                    && (y2 == y1 - 2 && x2 == x1 - 2));
        } else {
            return ((!(y1 <= 1 || x1 >= 6)
                    && (blackPiece[y1 - 1][x1 + 1]))
                    && (y2 == y1 - 2 && x2 == x1 + 2));
        }
    }

    public boolean redPieceL(int x1, int y1, int x2, int y2) {
        return ((y2 == y1 - 1 && x2 == x1 + 1)
                || (y2 == y1 - 1 && x2 == x1 - 1));
    }

    public void redMove(int x1, int y1, int x2, int y2) {
        redPiece[y1][x1] = false;
        redPiece[y2][x2] = true;
    }

    public void redKing(int x1, int y1, int x2, int y2) {
        redPiece[y1][x1] = false;
        redPiece[y2][x2] = true;
        redKing[y1][x1] = false;
        redKing[y2][x2] = true;

    }

    public boolean[][] getBlackPiece() {
        return blackPiece;
    }

    public void setBlackPiece(boolean[][] blackPiece) {
        this.blackPiece = blackPiece;
    }

    public boolean[][] getRedPiece() {
        return redPiece;
    }

    public void setRedPiece(boolean[][] redPiece) {
        this.redPiece = redPiece;
    }

    public boolean[][] getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(boolean[][] blackKing) {
        this.blackKing = blackKing;
    }

    public boolean[][] getRedKing() {
        return redKing;
    }

    public void setRedKing(boolean[][] redKing) {
        this.redKing = redKing;
    }

    @Override
    public void mousePressed(MouseEvent event) {

        point1 = event.getPoint();
        point3 = event.getPoint();
        for (int q = 0; q <= 7; q++) {
            for (int w = 0; w <= 7; w++) {

                if ((point1.x >= (((smallest - 2 * (smallest / 42)) / 8) * w) + (smallest / 28))
                        && (point1.x < (((smallest - 2 * (smallest / 42)) / 8) * (w + 1))
                        + (smallest / 28))) {

                    if ((point1.y >= (((smallest - 2 * (smallest / 42)) / 8) * q)
                            + (smallest / 28)) && (point1.y < (((smallest - 2 * (smallest / 42))
                            / 8) * (q + 1)) + (smallest / 28))) {

                        x1 = moveX = w;
                        y1 = moveY = q;
                    }
                }
            }
        }

        if (redPiece[y1][x1]) {
            moving = true;
            redMoving = true;
            redPiece[y1][x1] = false;
            if (redKing[y1][x1]) {
                redKingMoving = true;
                redKing[y1][x1] = false;
            }
        } else if (blackPiece[y1][x1]) {
            moving = true;
            blackMoving = true;
            blackPiece[y1][x1] = false;
            if (blackKing[y1][x1]) {
                blackKingMoving = true;
                blackKing[y1][x1] = false;
            }
        } else {
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        point3 = event.getPoint();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (moving) {
            if (blackMoving) {
                blackPiece[moveY][moveX] = true;
                if (blackKingMoving) {
                    blackKingMoving = false;
                    blackKing[moveY][moveX] = true;
                }
            } else if (redMoving) {
                redPiece[moveY][moveX] = true;
                if (redKingMoving) {
                    redKingMoving = false;
                    redKing[moveY][moveX] = true;
                }
            }
        }
        moving = false;
        blackMoving = false;
        redMoving = false;
        point2 = event.getPoint();
        point3 = null;
        for (int q = 0; q <= 7; q++) {
            for (int w = 0; w <= 7; w++) {
                if ((point2.x >= (((smallest - 2 * (smallest / 42)) / 8) * w) + (smallest / 28))
                        && (point2.x < (((smallest - 2 * (smallest / 42)) / 8) * (w + 1))
                        + (smallest / 28))) {
                    if ((point2.y >= (((smallest - 2 * (smallest / 42)) / 8) * q)
                            + (smallest / 28)) && (point2.y < (((smallest - 2 * (smallest / 42))
                            / 8) * (q + 1)) + (smallest / 28))) {
                        x2 = w;
                        y2 = q;
                    }
                }
            }
        }
        fc.timeClicked();
        move(x1, y1, x2, y2);
        repaint();
        if (!playing()) {
            GameOver();
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == 'p') {
            pause.paused();
        } else {
            //System.out.println(KeyEvent.getKeyText(event.getKeyCode()));
        }
        if (("Escape".equals(KeyEvent.getKeyText(event.getKeyCode())))) {
            ch.toggleVisiblity();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    private void GameOver() {

        checker.gameGoing = false;
    }

}

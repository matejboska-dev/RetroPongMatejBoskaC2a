package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGamePvsAI extends JPanel implements KeyListener {

    Font font = new Font("Comic Sans MS", Font.BOLD, 15);
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameBall;
    private UserPaddle userPaddle;
    private AIPaddle aiPaddle;
    private int userScore, pcScore;
    private int bounceCount;
    private final boolean[] keys = new boolean[256];
    public PongGamePvsAI() {

        gameBall = new Ball(300, 200, 3, 3, 3, Color.WHITE, 10);
        userPaddle = new UserPaddle(10, 200, 75, 10, Color.WHITE);
        aiPaddle = new AIPaddle(610, 200, 75, 10, Color.WHITE);

        userScore = 0;
        pcScore = 0;
        bounceCount = 0;

        addKeyListener(this);

        setFocusable(true);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));



    }

    public void reset() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameBall.setX(300);
        gameBall.setY(200);
        gameBall.setCurrentX(3);
        gameBall.setCurrentY(3);
        gameBall.setSpeed(3);
        bounceCount = 0;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gameBall.paint(g);

        userPaddle.paint(g);
        aiPaddle.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Score - Player [ " + userScore + " ]   AI [ " + pcScore + " ]", 200, 20);
    }

    public void gameLogic() {
        gameBall.moveBall();

        gameBall.detectCollisionWithFrameEdges(0, WINDOW_HEIGHT);

        aiPaddle.moveTowards(gameBall.getY());

        userPaddle.move();

        if (aiPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)) {
            gameBall.reverseX();
            bounceCount++;
            gameBall.increaseSpeed();
        }

        if (bounceCount == 5) {
            bounceCount = 0;

        }

        if (gameBall.getX() < 0) {
            pcScore++;
            reset();
        } else if (gameBall.getX() > WINDOW_WIDTH) {
            userScore++;
            reset();
        }
    }



    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            userPaddle.moveUp();
        } else if (keyCode == KeyEvent.VK_S) {
            userPaddle.moveDown();
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            userPaddle.moveUp();
        } else if (keyCode == KeyEvent.VK_S) {
            userPaddle.moveDown();
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not used
    }
}

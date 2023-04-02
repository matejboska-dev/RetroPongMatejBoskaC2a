package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGamePvsP extends JPanel implements KeyListener {

    Font font = new Font("Comic Sans MS", Font.BOLD, 15);
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameBall;
    private UserPaddle user1Paddle, user2Paddle;
    private int user1Score, user2Score;
    private int bounceCount;
    private final boolean[] keys = new boolean[256];
    public PongGamePvsP() {

        gameBall = new Ball(300, 200, 3, 3, 3, Color.WHITE, 10);
        user1Paddle = new UserPaddle(10, 200, 75, 10, Color.WHITE);
        user2Paddle = new UserPaddle(610, 200, 75, 10, Color.WHITE);

        user1Score = 0;
        user2Score = 0;
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

        user1Paddle.paint(g);
        user2Paddle.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Score - Player1 [ " + user1Score + " ]   Player2 [ " + user2Score + " ]", 185, 20);
    }

    public void gameLogic() {
        gameBall.moveBall();

        gameBall.detectCollisionWithFrameEdges(0, WINDOW_HEIGHT);

        user1Paddle.move();
        user2Paddle.move();

        if (user1Paddle.checkCollision(gameBall) || user2Paddle.checkCollision(gameBall)) {
            gameBall.reverseX();
            bounceCount++;
            gameBall.increaseSpeed();
        }

        if (bounceCount == 5) {
            bounceCount = 0;

        }

        if (gameBall.getX() < 0) {
            user2Score++;
            reset();
        } else if (gameBall.getX() > WINDOW_WIDTH) {
            user1Score++;
            reset();
        }
    }



    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch(keyCode) {
            case KeyEvent.VK_W:
                user1Paddle.moveUp();
                break;
            case KeyEvent.VK_S:
                user1Paddle.moveDown();
                break;
            case KeyEvent.VK_UP:
                user2Paddle.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                user2Paddle.moveDown();
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch(keyCode) {
            case KeyEvent.VK_W:
                user1Paddle.moveUp();
                break;
            case KeyEvent.VK_S:
                user1Paddle.moveDown();
                break;
            case KeyEvent.VK_UP:
                user2Paddle.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                user2Paddle.moveDown();
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not used
    }
}

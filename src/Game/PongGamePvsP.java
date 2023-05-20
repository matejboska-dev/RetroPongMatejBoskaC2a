package Game;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class PongGamePvsP extends JPanel implements KeyListener {

    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private final Ball gameBall;
    private final UserPaddle user1Paddle;
    private final UserPaddle user2Paddle;
    private int user1Score, user2Score;
    private final boolean[] keys = new boolean[256];
    private boolean gameEnded = false;
    private Font customFontForScore;
    private Clip clip;

    public PongGamePvsP() {

        try {
            customFontForScore = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x3.ttf")).deriveFont(72f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForScore);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        gameBall = new Ball(300, 200, 3, 3, 3, Color.WHITE, 10);
        user1Paddle = new UserPaddle(10, 200, 75, 10, Color.WHITE);
        user2Paddle = new UserPaddle(610, 200, 75, 10, Color.WHITE);

        user1Score = 0;
        user2Score = 0;

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
        gameBall.setCurrentX(Math.random() > 0.5 ? 3 : -3); // Randomly choose positive or negative X direction
        gameBall.setCurrentY(Math.random() > 0.5 ? 3 : -3); // Randomly choose positive or negative Y direction
        gameBall.setSpeed(3);

    }


    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gameBall.paint(g);

        user1Paddle.paint(g);
        user2Paddle.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(customFontForScore);
        g.drawString(user1Score + "   " + user2Score, 235, 60);
    }

    public void gameLogic() {
        gameBall.moveBall();

        gameBall.detectCollisionWithFrameEdges(0, WINDOW_HEIGHT);

        updatePaddlePositions();

        if (user1Paddle.checkCollision(gameBall) || user2Paddle.checkCollision(gameBall)) {
            playSound();
            gameBall.reverseX();
            gameBall.increaseSpeed();
        }

        if (gameBall.getX() < 0) {
            user2Score++;
            reset();
        } else if (gameBall.getX() > WINDOW_WIDTH) {
            user1Score++;
            reset();
        }

        if (user1Score >= 10 && !gameEnded) {
            new WinningWindow("P1");
            gameEnded = true;

            user1Paddle.moveTowards(0);
            user2Paddle.moveTowards(0);

            stopGame();

        } else if (user2Score >= 10 && !gameEnded) {
            new WinningWindow("P2");
            gameEnded = true;
            user1Paddle.moveTowards(0);
            user2Paddle.moveTowards(0);
            stopGame();
        }
    }

    private void updatePaddlePositions() {
        if (keys[KeyEvent.VK_W]) {
            user1Paddle.moveUp();
        } else if (keys[KeyEvent.VK_S]) {
            user1Paddle.moveDown();
        }

        if (keys[KeyEvent.VK_UP]) {
            user2Paddle.moveUp();
        } else if (keys[KeyEvent.VK_DOWN]) {
            user2Paddle.moveDown();
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keys[keyCode] = true;
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keys[keyCode] = false;
    }

    public void keyTyped(KeyEvent e) {
        // Not used
    }

    private void playSound() {
        try {
            File soundFile = new File("ballSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    stopSound();
                }
            });
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    private void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void stopGame() {
        Container topLevelContainer = this.getTopLevelAncestor();

        if (topLevelContainer instanceof JFrame frame) {
            frame.dispose();
        }
    }

}
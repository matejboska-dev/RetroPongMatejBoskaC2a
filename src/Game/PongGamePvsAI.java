package Game;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class PongGamePvsAI extends JPanel implements KeyListener {

    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameBall;
    protected UserPaddle userPaddle;
    protected AIPaddle aiPaddle;
    private int userScore, aiScore;
    private int bounceCount;
    private boolean gameEnded = false;

    Clip clip;
    Font customFontForScore;

    public PongGamePvsAI() {

        try {
            customFontForScore = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x3.ttf")).deriveFont(72f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForScore);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        gameBall = new Ball(300, 200, 3, 3, 3, Color.WHITE, 10);
        userPaddle = new UserPaddle(10, 200, 75, 10, Color.WHITE);
        aiPaddle = new AIPaddle(610, 200, 75, 10, Color.WHITE);

        userScore = 0;
        aiScore = 0;
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
        g.setFont(customFontForScore);
        g.drawString(userScore  + "   " + aiScore , 235, 60);
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
            playSound();
        }

        if (bounceCount == 5) {
            bounceCount = 0;

        }

        if (gameBall.getX() < 0) {
            aiScore++;
            reset();
        } else if (gameBall.getX() > WINDOW_WIDTH) {
            userScore++;
            reset();
        }

        if (aiScore >= 1 && !gameEnded) {
            new WinningWindow("AI");
            gameEnded = true;
            aiPaddle.setY(9999);
            aiPaddle.setSpeed(0);
            stopSound();
            stopGame();

        } else if ((userScore >= 1) && !gameEnded) {
            new WinningWindow("Player");
            gameEnded = true;
            aiPaddle.setY(9999);
            aiPaddle.setSpeed(0);
            stopSound();
            stopGame();
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

    private void playSound() {
        try {
            File soundFile = new File("ballSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    private void stopSound() {
        clip.stop();
    }

    public void stopGame() {
        Container topLevelContainer = this.getTopLevelAncestor();

        if (topLevelContainer instanceof JFrame frame) {
            frame.dispose();
        }
    }


}


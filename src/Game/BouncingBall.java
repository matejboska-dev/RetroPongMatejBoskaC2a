package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BouncingBall extends JPanel {

    private int x = 0;
    private int y = 0;
    private int xSpeed = 3;
    private int ySpeed = 3;
    private BufferedImage ballImage;

    public BouncingBall() {
        try {
            // Load the ball image from a file
            ballImage = ImageIO.read(new File("ball.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void moveBall() {
        x += xSpeed;
        y += ySpeed;
        if (x > getWidth() - ballImage.getWidth() || x < 0) {
            xSpeed = -xSpeed;
        }
        if (y > getHeight() - ballImage.getHeight() || y < 0) {
            ySpeed = -ySpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(ballImage, x, y, null);
    }

}



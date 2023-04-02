package Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UserPaddle extends Paddle {
    // instance variable
    private boolean moveUp, moveDown;

    /**
     * Basic constructor
     *
     * @param x     where the paddle is on the x-axis
     * @param y     where the paddle is on the y-axis
     * @param size  the size of the paddle
     * @param speed its current speed
     * @param color color of the paddle
     */
    public UserPaddle(int x, int y, int size, int speed, Color color) {
        super(x, y, size, speed, color);
    }

    /**
     * Draws the paddle within its size
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    /**
     * Handles the keyPressed event to move the paddle up or down
     *
     * @param e the KeyEvent object that contains information about the key event
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = true;
        }
    }

    /**
     * Handles the keyReleased event to stop moving the paddle up or down
     *
     * @param e the KeyEvent object that contains information about the key event
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = false;
        }
    }

    /**
     * Moves the paddle based on the moveUp and moveDown instance variables
     */
    public void move() {
        if (moveUp) {
            moveUp();
        }
        if (moveDown) {
            moveDown();
        }
    }
}
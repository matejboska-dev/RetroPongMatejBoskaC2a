package Game;

import java.awt.*; //needed for Color

public class Paddle {
    // final variables
    static final int PADDLE_WIDTH = 8;
    // instance variables
    private int size, x, y, speed;
    private Color color;

    /**
     * Basic constructor
     * @param x where the paddle is on the x-axis
     * @param y where the paddle is on the y-axis
     * @param size the size of the paddle
     * @param speed its current speed
     * @param color color of the paddle
     */
    public Paddle(int x, int y, int size, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.color = color;
    }

    /**
     * Draws the paddle within its size
     * @param g
     */
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, PADDLE_WIDTH, size);
    }

    /**
     * Move the paddle towards this y position until it's centered on it
     * @param moveToY - position the paddle is centered on
     */
    public void moveTowards(int moveToY) {

        //find the location of the center of the paddle
        int centerY = y + size / 2;

        //make sure the paddle is far enough away from the target moveToY position. If it's closer than the speed to where it should be, don't bother moving the paddle.
        if(Math.abs(centerY - moveToY) > speed){
            //if the center of the paddle is too far down
            if(centerY > moveToY){
                //move the paddle up by the speed
                y -= speed;
            }
            //if the center of the paddle is too far up
            if(centerY < moveToY){
                //move the paddle down by speed
                y += speed;
            }
        }

    }

    /**
     * if statement checks if ball has collided with the paddle
     * @param b the ball we're checking for a collision with
     * @return true if collision is detected
     */
    public boolean checkCollision(Ball b){

        int rightX = x + PADDLE_WIDTH;
        int bottomY = y + size;

        if(b.getX() > (x - b.getSize()) && b.getX() < rightX){
            if(b.getY() > y && b.getY() < bottomY){
                return true;
            }
        }

        return false; // returns false if ball hasn't collided

    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
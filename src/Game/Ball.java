package Game;

import java.awt.*;

public class Ball {

    /**
     * final variables
     */
    static final int MAX_SPEED = 6;
    /**
     * variables
     */
    private int x, y, currentX, currentY, speed, size;
    private Color color;

    /**
     *
     * @param x
     * @param y
     * @param cx
     * @param cy
     * @param speed
     * @param color
     * @param size
     *
     * simple constructor
     */
    public Ball(int x, int y, int cx, int cy, int speed, Color color, int size) {
        this.x = x;
        this.y = y;
        this.currentX = cx;
        this.currentY = cy;
        this.speed = speed;
        this.color = color;
        this.size = size;
    }
    /**
     * Getters and setters
     * @return
     */
    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setCurrentY(int currentY){
        this.currentY = currentY;
    }

    public void setCurrentX(int currentX){
        this.currentX = currentX;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getSize(){
        return size;
    }
    /**
     * sets the brush color as the ball color
     * and paints the ball at x and y coordinates within the initialized size of the ball
     * @param g
     */
    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public void moveBall(){
        x += currentX;
        y += currentY;
    }

    /**
     * Detect collision with screen borders and reverse direction
     * @param top - the y value of the top of the screen
     * @param bottom - the y value of the bottom of the screen
     */
    public void detectCollisionWithFrameEdges(int top, int bottom){
        if (y > bottom - size){
            reverseY();
        }
        else if(y < top){
            reverseY();
        }
    }

    /**
     * These 2 speak for themselves
     */
    public void reverseX(){
        currentX *= -1;
    }
    public void reverseY(){
        currentY *= -1;
    }

    /**
     * Increases the speed of the ball (therefore increases the variables currentX and currentY)
     */
    public void increaseSpeed() {
        // Reset speed to initial value
        speed = 7;

        // Multiply currentX and currentY by a constant value
        double multiplier = 1.2;
        if (currentX > 0) {
            currentX = (int) Math.ceil(currentX * multiplier);
        } else {
            currentX = (int) Math.floor(currentX * multiplier);
        }
        if (currentY > 0) {
            currentY = (int) Math.ceil(currentY * multiplier);
        } else {
            currentY = (int) Math.floor(currentY * multiplier);
        }
    }



}
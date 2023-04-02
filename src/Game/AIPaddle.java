package Game;

import java.awt.Color;

public class AIPaddle extends Paddle {

    private Ball ball;

    public AIPaddle(int x, int y, int size, int speed, Color color) {
        super(x, y, size, speed, color);
        this.ball = ball;
    }

    public void update() {
        int ballCenterY = ball.getY() + ball.getSize() / 2;
        int paddleCenterY = getY() + getSize() / 2;

        if (ballCenterY < paddleCenterY) {
            moveUp();
        } else if (ballCenterY > paddleCenterY) {
            moveDown();
        }
    }
}
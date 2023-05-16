package JUnitTests;

import Game.Ball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    private Ball ball;

    @BeforeEach
    void setUp() {
        // Initialize the Ball instance before each test
        ball = new Ball(100, 100, 1, 1, 5, Color.RED, 20);
    }

    @Test
    void testGetX() {
        // Perform the test
        int x = ball.getX();

        // Assert the initial x coordinate
        assertEquals(100, x);
    }

    @Test
    void testGetY() {
        // Perform the test
        int y = ball.getY();

        // Assert the initial y coordinate
        assertEquals(100, y);
    }

    @Test
    void testSetX() {
        // Perform the test
        ball.setX(200);

        // Assert the updated x coordinate
        assertEquals(200, ball.getX());
    }

    @Test
    void testSetY() {
        // Perform the test
        ball.setY(150);

        // Assert the updated y coordinate
        assertEquals(150, ball.getY());
    }

    @Test
    void testSetSpeed() {
        // Perform the test
        ball.setSpeed(8);

        // Assert the updated speed
        assertEquals(8, ball.getSpeed());
    }

    @Test
    void testSetCurrentX() {
        // Perform the test
        ball.setCurrentX(2);

        // Assert the updated currentX value
        assertEquals(2, ball.getCurrentX());
    }

    @Test
    void testSetCurrentY() {
        // Perform the test
        ball.setCurrentY(2);

        // Assert the updated currentY value
        assertEquals(2, ball.getCurrentY());
    }

    @Test
    void testGetSize() {
        // Perform the test
        int size = ball.getSize();

        // Assert the initial size
        assertEquals(20, size);
    }

    @Test
    void testDetectCollisionWithFrameEdges() {
        // Perform the test - Ball hits the top edge
        ball.setY(0);
        ball.detectCollisionWithFrameEdges(0, 500);

        // Assert that the ball's currentY is reversed
        assertEquals(-1, ball.getCurrentY());

        // Perform the test - Ball hits the bottom edge
        ball.setY(480);
        ball.detectCollisionWithFrameEdges(0, 500);

        // Assert that the ball's currentY is reversed
        assertEquals(1, ball.getCurrentY());
    }

    @Test
    void testReverseX() {
        // Perform the test
        ball.reverseX();

        // Assert that the ball's currentX is reversed
        assertEquals(-1, ball.getCurrentX());
    }

    @Test
    void testReverseY() {
        // Perform the test
        ball.reverseY();

        // Assert that the ball's currentY is reversed
        assertEquals(-1, ball.getCurrentY());
    }

    @Test
    void testIncreaseSpeed() {
        // Perform the test
        ball.increaseSpeed();

        // Assert the updated speed
        assertEquals(7, ball.getSpeed());

        // Assert that the currentX and currentY values are increased
        assertTrue(ball.getCurrentX() > 1);
        assertTrue(ball.getCurrentY() > 1);
    }
}

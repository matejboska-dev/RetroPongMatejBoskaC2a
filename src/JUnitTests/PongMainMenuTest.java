package JUnitTests;

import Game.AIDifficultyMenu;
import Main.PongMainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PongMainMenuTest {

    private PongMainMenu pongMainMenu;

    @BeforeEach
    void setUp() throws InterruptedException {
        // Initialize the PongMainMenu instance before each test
        pongMainMenu = new PongMainMenu();
    }

    @Test
    void testPvpGameInitialization() {
        // Perform the test
        pongMainMenu.startPvpGame();

        // Assert that the PongMainMenu instance is not visible
        assertFalse(pongMainMenu.isVisible());

        // Assert that the created JFrame for PVP game is displayed
        JFrame gameFrame = (JFrame) SwingUtilities.getWindowAncestor(pongMainMenu);
        assertNotNull(gameFrame);
        assertTrue(gameFrame.getTitle().equals("Player vs Player"));
        assertTrue(gameFrame.isVisible());
    }

    @Test
    void testPvaGameInitialization() throws InterruptedException {
        // Perform the test
        pongMainMenu.startPvaGame();

        // Assert that the PongMainMenu instance is not visible
        assertFalse(pongMainMenu.isVisible());

        // Assert that the created AI Difficulty Menu is displayed
        AIDifficultyMenu aiMenu = getAIDifficultyMenu();
        assertNotNull(aiMenu);
        assertTrue(aiMenu.isVisible());
    }

    @Test
    void testActionPerformed() {
        // Perform the test
        assertDoesNotThrow(() -> pongMainMenu.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "TestEvent")));

        // No assertions made as the method does not have any code
    }

    @Test
    void testCustomFontLoading() {
        // Assert that the custom fonts are successfully loaded
        Font customFont = pongMainMenu.customFont;
        assertNotNull(customFont);
        assertEquals(Font.TRUETYPE_FONT, customFont.getFontName());
        assertEquals(72f, customFont.getSize());

        Font customFontForButtons = pongMainMenu.customFontForButtons;
        assertNotNull(customFontForButtons);
        assertEquals(Font.TRUETYPE_FONT, customFontForButtons.getFontName());
        assertEquals(16f, customFontForButtons.getSize());

        Font customFontForOffButton = pongMainMenu.customFontForOffButton;
        assertNotNull(customFontForOffButton);
        assertEquals(Font.TRUETYPE_FONT, customFontForOffButton.getFontName());
        assertEquals(42f, customFontForOffButton.getSize());
    }

    private AIDifficultyMenu getAIDifficultyMenu() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof AIDifficultyMenu) {
                return (AIDifficultyMenu) window;
            }
        }
        return null;
    }
}

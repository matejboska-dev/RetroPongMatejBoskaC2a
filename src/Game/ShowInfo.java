package Game;

import Main.PongMainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ShowInfo extends JFrame {

    Font customFont, customFontForBackButton;
    private PongMainMenu pongMainMenu;

    public ShowInfo(PongMainMenu pongMainMenu) {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x5.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try {
            customFontForBackButton = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x3.ttf")).deriveFont(32f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForBackButton);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.pongMainMenu = pongMainMenu;

        setSize(640, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);

        // Create a label with the game description
        JLabel descriptionLabel = new JLabel("<html><center>Pong is a classic arcade game<br>developed by Atari in 1972.<br>It is one of the earliest arcade<br>video games and simulates table tennis.<br>The objective is to beat the opponent.<br>Enjoy playing Pong!</center></html>");
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(customFont);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setVerticalAlignment(SwingConstants.CENTER);

        JButton backButton = new JButton("<-");
        backButton.addActionListener(e -> {
            setVisible(false);
            pongMainMenu.setVisible(true);
        });

        backButton.setBounds(0,0, 50, 50);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(customFontForBackButton);
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        backButton.setFocusPainted(false);

        getContentPane().add(backButton);
        getContentPane().add(descriptionLabel);

    }
}

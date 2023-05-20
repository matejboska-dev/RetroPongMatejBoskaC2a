package Game;

import Main.PongMainMenu;
import com.sun.tools.javac.Main;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinningWindow {

    private Font customFont, customFontForOffButton;

    public WinningWindow(String winner) {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x5.ttf")).deriveFont(36f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try {
            customFontForOffButton = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x3.ttf")).deriveFont(36f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForOffButton);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Winner!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JButton offButton = new JButton("Retry");
        offButton.addActionListener(e -> {
            frame.dispose();
            PongMainMenu.showMainMenu();
        });

        offButton.setBackground(Color.BLACK);
        offButton.setForeground(Color.WHITE);
        offButton.setFont(customFontForOffButton);
        offButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        offButton.setFocusPainted(false);

        JLabel label = new JLabel(winner + " won!", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(customFont);

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(offButton, BorderLayout.SOUTH);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}

package Game;

import Main.PongMainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class AIDifficultyMenu extends JFrame {

    private JButton easyButton, normalButton, hardButton, backButton;
    Font customFont, customFontForButtons, customFontForBackButton;
    private PongMainMenu pongMainMenu;

    public AIDifficultyMenu(PongMainMenu pongMainMenu) throws InterruptedException {
        super("AI Difficulty Menu");

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x5.ttf")).deriveFont(42f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try {
            customFontForButtons = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x5.ttf")).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForButtons);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try {
            customFontForBackButton = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x3.ttf")).deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForBackButton);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.pongMainMenu = pongMainMenu;

        JLabel title = new JLabel("Choose AI Difficulty");
        title.setBounds(30,30, 800, 100);
        title.setFont(customFont);
        title.setForeground(Color.WHITE);

        // Create the buttons and add action listeners
        backButton = new JButton("<-");
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

        easyButton = new JButton("Easy");
        easyButton.addActionListener(e -> {
            setVisible(false);
            startEasyGame();
        });

        easyButton.setBounds(210,130, 200, 50);
        easyButton.setBackground(Color.BLACK);
        easyButton.setForeground(Color.WHITE);
        easyButton.setFont(customFontForButtons);
        easyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        easyButton.setFocusPainted(false);


        normalButton = new JButton("Normal");
        normalButton.addActionListener(e -> {
            setVisible(false);
            startNormalGame();
        });

        normalButton.setBounds(210,210, 200, 50);
        normalButton.setBackground(Color.BLACK);
        normalButton.setForeground(Color.WHITE);
        normalButton.setFont(customFontForButtons);
        normalButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        normalButton.setFocusPainted(false);


        hardButton = new JButton("Hard");
        hardButton.addActionListener(e -> {
            setVisible(false);
            startHardGame();
        });

        hardButton.setBounds(210,290, 200, 50);
        hardButton.setBackground(Color.BLACK);
        hardButton.setForeground(Color.WHITE);
        hardButton.setFont(customFontForButtons);
        hardButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        hardButton.setFocusPainted(false);


        // Add the buttons to the frame

        add(easyButton);
        add(normalButton);
        add(hardButton);
        add(backButton);
        add(title);

        // Set the frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);


    }
    private void startEasyGame() {

        JFrame frame = new JFrame("Player vs AI");

        PongGamePvsAIeasy game = new PongGamePvsAIeasy();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(655,510);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);

        Timer timer = new Timer(16, e -> {
            game.gameLogic();
            game.repaint();
        });

        //start the timer after it's been created
        timer.start();

    }
    private void startNormalGame() {
        JFrame frame = new JFrame("Player vs AI");

        PongGamePvsAInormal game = new PongGamePvsAInormal();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(655,510);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);

        Timer timer = new Timer(16, e -> {
            game.gameLogic();
            game.repaint();
        });

        //start the timer after it's been created
        timer.start();
    }
    private void startHardGame() {
        JFrame frame = new JFrame("Player vs AI");

        PongGamePvsAIhard game = new PongGamePvsAIhard();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(655,510);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);

        Timer timer = new Timer(16, e -> {
            game.gameLogic();
            game.repaint();
        });

        //start the timer after it's been created
        timer.start();
    }

}

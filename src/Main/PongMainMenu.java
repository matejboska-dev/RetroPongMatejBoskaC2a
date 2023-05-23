package Main;

import Game.AIDifficultyMenu;
import Game.BouncingBall;
import Game.PongGamePvsP;
import Game.ShowInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class PongMainMenu extends JFrame implements ActionListener {

    public Font customFont;
    public Font customFontForButtons;
    public Font customFontForOffButton;
    private static BouncingBall ball;

    private static JFrame mainMenuFrame;

    public PongMainMenu() throws InterruptedException {
        // Fonts
        /*-----------------------------------------------*/
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x5.ttf")).deriveFont(72f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try {
            customFontForButtons = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x5.ttf")).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForButtons);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        try {
            customFontForOffButton = Font.createFont(Font.TRUETYPE_FONT, new File("bit5x3.ttf")).deriveFont(42f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontForOffButton);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        /*-----------------------------------------------*/

        mainMenuFrame = this;

        /*-----------------------------------------------*/
        setTitle("Pong Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        /*-----------------------------------------------*/

        /*-----------------------------------------------*/
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        /*-----------------------------------------------*/

        /*-----------------------------------------------*/
        ball = new BouncingBall();
        ball.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(ball, JLayeredPane.DEFAULT_LAYER);
        /*-----------------------------------------------*/

        //Title
        /*-----------------------------------------------*/
        JLabel title = new JLabel("Pong");
        title.setBounds(210, 55, 1000, 100);
        title.setFont(customFont);
        title.setForeground(Color.WHITE);
        layeredPane.add(title, JLayeredPane.PALETTE_LAYER);
        /*-----------------------------------------------*/

        //OFF Button
        /*-----------------------------------------------*/
        JButton offButton = new JButton("X");
        offButton.addActionListener(e -> {
            System.exit(0);
            dispose();
        });
        offButton.setBounds(0,0, 50, 50);
        offButton.setBackground(Color.BLACK);
        offButton.setForeground(Color.WHITE);
        offButton.setFont(customFontForOffButton);
        offButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        offButton.setFocusPainted(false);
        layeredPane.add(offButton, JLayeredPane.PALETTE_LAYER);
        /*-----------------------------------------------*/

        /*-----------------------------------------------*/
        JButton pvaButton = new JButton("Player vs AI");
        pvaButton.setBounds(340, 190, 200, 50);
        pvaButton.setBackground(Color.BLACK);
        pvaButton.setFont(customFontForButtons);
        pvaButton.setForeground(Color.WHITE);
        pvaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        pvaButton.setFocusPainted(false);
        pvaButton.addActionListener(e -> {
            try {
                startPvaGame();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        layeredPane.add(pvaButton, JLayeredPane.PALETTE_LAYER);
        /*-----------------------------------------------*/

        /*-----------------------------------------------*/
        JButton pvpButton = new JButton("Player vs Player");
        pvpButton.setBounds(90, 190, 200, 50);
        pvpButton.setBackground(Color.BLACK);
        pvpButton.setFont(customFontForButtons);
        pvpButton.setForeground(Color.WHITE);
        pvpButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        pvpButton.setFocusPainted(false);
        pvpButton.addActionListener(e -> startPvpGame());
        layeredPane.add(pvpButton, JLayeredPane.PALETTE_LAYER);
        /*-----------------------------------------------*/
        JButton aboutButton = new JButton("About");
        aboutButton.setBounds(215, 265, 200, 50);
        aboutButton.setBackground(Color.BLACK);
        aboutButton.setFont(customFontForButtons);
        aboutButton.setForeground(Color.WHITE);
        aboutButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        aboutButton.setFocusPainted(false);
        aboutButton.addActionListener(e -> openShowInfo());
        layeredPane.add(aboutButton, JLayeredPane.PALETTE_LAYER);
        /*-----------------------------------------------*/
        setVisible(true);

        while (true) {
            ball.moveBall();
            ball.repaint();
            Thread.sleep(10);
        }

        /*-----------------------------------------------*/
    }

    /*-----------------------------------------------*/
    public void startPvpGame(){
        setVisible(false);

        JFrame frame = new JFrame("Player vs Player");

        PongGamePvsP game = new PongGamePvsP();

        frame.setSize(655,510);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);


        /**
         *  I wanted to use ActionListener in the timer but idea told me to use lambda
         *  I don't know what it is, but I used it because idea said so
         */

        Timer timer = new Timer(16, e -> {
            game.gameLogic();
            game.repaint();
        });

        //start the timer after it's been created
        timer.start();
    }
    /*-----------------------------------------------*/

    /*-----------------------------------------------*/
    public void startPvaGame() throws InterruptedException {
        AIDifficultyMenu aiDifficultyMenu = new AIDifficultyMenu(this);
        setVisible(false);
        aiDifficultyMenu.setVisible(true);
    }
    public void openShowInfo() {
        ShowInfo showInfo = new ShowInfo(this);
        setVisible(false);     
        showInfo.setVisible(true);
    }
    /*-----------------------------------------------*/

    /*-----------------------------------------------*/
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    /*-----------------------------------------------*/
    public static void showMainMenu() {
        mainMenuFrame.setVisible(true);
    }
    /*-----------------------------------------------*/
    public static void main(String[] args) throws InterruptedException {
        new PongMainMenu();
    }
    /*-----------------------------------------------*/
}

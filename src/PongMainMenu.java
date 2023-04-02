import Game.PongGamePvsAI;
import Game.PongGamePvsP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongMainMenu extends JFrame implements ActionListener {



    public PongMainMenu() {
        setTitle("Pong Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use FlowLayout to position the buttons
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 180));

        // Set preferred size of the buttons
        JButton pvaButton = new JButton("Player vs AI");
        pvaButton.setPreferredSize(new Dimension(200, 50));
        pvaButton.setBackground(Color.BLACK);
        pvaButton.setForeground(Color.WHITE);
        pvaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        pvaButton.setFocusPainted(false);

        JButton pvpButton = new JButton("Player vs Player");
        pvpButton.setPreferredSize(new Dimension(200, 50));
        pvpButton.setBackground(Color.BLACK);
        pvpButton.setForeground(Color.WHITE);
        pvpButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        pvpButton.setFocusPainted(false);

        pvaButton.addActionListener(e -> startPvaGame());
        pvpButton.addActionListener(e -> startPvpGame());

        add(pvaButton);
        add(pvpButton);

        setVisible(true);
    }
    private void startPvpGame(){
        dispose();

        JFrame frame = new JFrame("Player vs Player");

        PongGamePvsP game = new PongGamePvsP();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    private void startPvaGame(){
        dispose();

        JFrame frame = new JFrame("Player vs AI");

        PongGamePvsAI game = new PongGamePvsAI();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        PongMainMenu menu = new PongMainMenu();
    }

}

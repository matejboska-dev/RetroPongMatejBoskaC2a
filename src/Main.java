import Game.PongGamePvsAI;

import javax.swing.*;

public class Main {
    static JFrame frame = new JFrame("Pong");
    public static void main(String[] args) {
        /**
         * setting up the frame
         */
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

       Timer timer = new Timer(33, e -> {
            game.gameLogic();
            game.repaint();
        });

        //start the timer after it's been created
        timer.start();

    }
}
package Game;

import java.awt.*;

public class PongGamePvsAIhard extends PongGamePvsAI{

    public PongGamePvsAIhard() {
        super();
        userPaddle = new UserPaddle(10, 200, 75, 10, Color.WHITE);
        aiPaddle = new AIPaddle(610, 200, 100, 12, Color.WHITE);
    }

}

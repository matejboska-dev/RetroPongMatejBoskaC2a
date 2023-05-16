package Game;

import java.awt.*;

public class PongGamePvsAIeasy extends PongGamePvsAI {

    public PongGamePvsAIeasy() {
        super();
        userPaddle = new UserPaddle(10, 200, 75, 10, Color.WHITE);
        aiPaddle = new AIPaddle(610, 200, 75, 6, Color.WHITE);
    }
}

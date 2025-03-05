package Games.Pong;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PongGame extends JFrame {
    public PongGame() {
        setTitle("Pong Game");
        setSize(800, 640);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Games.Pong.PongGame.class.getResource("/Games/Pong/icon/PongIcon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);
    }

    public static void main(String[] args) {
        PongGame game = new PongGame();
        game.setVisible(true);
    }
}

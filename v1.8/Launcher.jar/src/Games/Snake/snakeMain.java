package Games.Snake;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.*;
public class snakeMain extends JFrame {
	public snakeMain() {
		snakeWin win = new snakeWin();
		add(win);
		setTitle("Snake");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Games.Snake.snakeMain.class.getResource("/Games/Snake/icon/SnakeIcon.png")));
		setSize(435,390);
		setLocation(200, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new snakeMain();
	}
}

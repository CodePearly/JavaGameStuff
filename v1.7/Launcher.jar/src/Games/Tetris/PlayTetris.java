package Games.Tetris;

import javax.swing.*;
import java.awt.*;

public class PlayTetris extends JFrame
{
	public PlayTetris()
	{
		setTitle("Tetris");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Games.Tetris.PlayTetris.class.getResource("/Games/Tetris/icon/TetrisIcon.png")));
		getContentPane().setLayout(new FlowLayout());
		add(new TetrisComponent(10,20));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayTetris();
	}
}
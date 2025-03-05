import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Window.Type;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class Launcher {
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                System.out.println("Tried to open the Launcher");
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frmThisIsThe = new JFrame("My Application Window");
        frmThisIsThe.getContentPane().setBackground(SystemColor.desktop);
        frmThisIsThe.setTitle("This is the Launcher to Some Stuff");
        frmThisIsThe.setType(Type.UTILITY);
        frmThisIsThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmThisIsThe.setSize(377, 80);

        // Center the window on the screen
        frmThisIsThe.setLocationRelativeTo(null);

        // Create the button.
        JButton button = new JButton("Pong");

        // Add an action listener to the button.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Games.Pong.PongGame.main(null);
            	JOptionPane.showMessageDialog(button, "Tried to open Pong");
        		System.out.println("Tried to open Pong");
            }
        });
        frmThisIsThe.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Add the button to the window.
        frmThisIsThe.getContentPane().add(button);
        
     // Create the button.
        JButton but2 = new JButton("Snake");

        // Add an action listener to the button.
        but2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Games.Snake.snakeMain.main(null);
                JOptionPane.showMessageDialog(but2, "Tried to open Snake");
        		System.out.println("Tried to open Snake");
            }
        });

        // Add the button to the window.
        frmThisIsThe.getContentPane().add(but2);
        // Display the window.
        frmThisIsThe.setVisible(true);
     // Create the button.
        JButton but3 = new JButton("Tetris");

        // Add an action listener to the button.
        but3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Games.Tetris.PlayTetris.main(null);
                JOptionPane.showMessageDialog(but3, "Tried to open Tetris");
        		System.out.println("Tried to open Tetris");
            }
        });

        // Add the button to the window.
        frmThisIsThe.getContentPane().add(but3);
        
        JButton but4 = new JButton("Flappy Bird");
        but4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Games.FlappyBird.com.kingyu.flappybird.app.App.main(null);
        		JOptionPane.showMessageDialog(but4, "Tried to open Flappy Bird");
        		System.out.println("Tried to open Flappy Bird");
        	}
        });
        frmThisIsThe.getContentPane().add(but4);
        // Display the window.
        frmThisIsThe.setVisible(true);
    }
}

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Window.Type;
import java.awt.SystemColor;

public class Launcher {
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
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
        frmThisIsThe.setSize(266, 86);
        frmThisIsThe.getContentPane().setLayout(null); // Set layout manager to null for absolute layout

        // Center the window on the screen
        frmThisIsThe.setLocationRelativeTo(null);

        // Create the button.
        JButton button = new JButton("Pong");
        button.setBounds(10, 13, 70, 25); // Set position and size

        // Add an action listener to the button.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Games.Pong.PongGame.main(null);
            }
        });

        // Add the button to the window.
        frmThisIsThe.getContentPane().add(button);
        
     // Create the button.
        JButton but2 = new JButton("Snake");
        but2.setBounds(90, 13, 70, 25); // Set position and size

        // Add an action listener to the button.
        but2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Games.Snake.snakeMain.main(null);
            }
        });

        // Add the button to the window.
        frmThisIsThe.getContentPane().add(but2);
        // Display the window.
        frmThisIsThe.setVisible(true);
     // Create the button.
        JButton but3 = new JButton("Tetris");
        but3.setBounds(170, 13, 70, 25); // Set position and size

        // Add an action listener to the button.
        but3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Games.Tetris.PlayTetris.main(null);
            }
        });

        // Add the button to the window.
        frmThisIsThe.getContentPane().add(but3);
        // Display the window.
        frmThisIsThe.setVisible(true);
    }
}

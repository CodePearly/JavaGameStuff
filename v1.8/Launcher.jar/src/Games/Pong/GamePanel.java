package Games.Pong;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Timer timer;
    private int leftScore = 0;
    private int rightScore = 0;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                leftPaddle.keyPressed(e);
                rightPaddle.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                leftPaddle.keyReleased(e);
                rightPaddle.keyReleased(e);
            }
        });

        ball = new Ball(400, 300, 20);
        leftPaddle = new Paddle(30, 250, 10, 100, KeyEvent.VK_W, KeyEvent.VK_S);
        rightPaddle = new Paddle(760, 250, 10, 100, KeyEvent.VK_UP, KeyEvent.VK_DOWN);

        timer = new Timer(5, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        leftPaddle.draw(g);
        rightPaddle.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + leftScore, 100, 50);
        g.drawString("Score: " + rightScore, 600, 50);
    }

    private void resetBall() {
        ball = new Ball(400, 300, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();
        if (ball.isLeftOut()) {
            rightScore++;
            resetBall();
        } else if (ball.isRightOut()) {
            leftScore++;
            resetBall();
        }
        ball.checkCollision(leftPaddle);
        ball.checkCollision(rightPaddle);
        leftPaddle.move();
        rightPaddle.move();
        repaint();
    }
}

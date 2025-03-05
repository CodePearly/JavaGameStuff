package Games.Pong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y, width, height, yVelocity;
    private boolean upPressed, downPressed;
    private int upKey, downKey;

    public Paddle(int x, int y, int width, int height, int upKey, int downKey) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == upKey) {
            upPressed = true;
        } else if (e.getKeyCode() == downKey) {
            downPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == upKey) {
            upPressed = false;
        } else if (e.getKeyCode() == downKey) {
            downPressed = false;
        }
    }

    public void move() {
        if (upPressed && y > 0) {
            y -= 5;
        }
        if (downPressed && y < 500) {
            y += 5;
        }
    }
}

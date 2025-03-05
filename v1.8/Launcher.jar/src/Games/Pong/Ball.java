package Games.Pong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private int x, y, xVelocity, yVelocity, diameter;

    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xVelocity = 2;
        this.yVelocity = 2;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
        
        if (x <= 0 || x >= 780) {
            xVelocity = -xVelocity;
        }
        if (y <= 0 || y >= 580) {
            yVelocity = -yVelocity;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public void checkCollision(Paddle paddle) {
        if (new Rectangle(x, y, diameter, diameter).intersects(paddle.getBounds())) {
            xVelocity = -xVelocity;
        }
    }
    public boolean isLeftOut() {
    	return x <= 0;
    }
    public boolean isRightOut() {
    	return x >= 780;
    }
    
    }


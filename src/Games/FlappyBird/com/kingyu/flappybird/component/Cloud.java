package Games.FlappyBird.com.kingyu.flappybird.component;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * äº‘æœµç±»ï¼Œå®žçŽ°äº‘æœµçš„ç»˜åˆ¶å’Œè¿�åŠ¨é€»è¾‘
 *
 * @author Kingyu
 */
public class Cloud {

    private final int speed; // é€Ÿåº¦
    private int x; // å��æ ‡
    private final int y;

    private final BufferedImage img;

    private final int scaleImageWidth;
    private final int scaleImageHeight;

    // æž„é€ å™¨
    public Cloud(BufferedImage img, int x, int y) {
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = Constant.GAME_SPEED * 2; //äº‘æœµçš„é€Ÿåº¦
        // äº‘æœµå›¾ç‰‡ç¼©æ”¾çš„æ¯”ä¾‹ 1.0~2.0
        double scale = 1 + Math.random(); // Math.random()è¿”å›ž0.0~1.0çš„éš�æœºå€¼
        // ç¼©æ”¾äº‘æœµå›¾ç‰‡
        scaleImageWidth = (int) (scale * img.getWidth());
        scaleImageHeight = (int) (scale * img.getWidth());
    }

    // ç»˜åˆ¶æ–¹æ³•
    public void draw(Graphics g, Bird bird) {
        int speed = this.speed;
        if (bird.isDead())
            speed = 1;
        x -= speed;
        g.drawImage(img, x, y, scaleImageWidth, scaleImageHeight, null);
    }

    /**
     * åˆ¤æ–­äº‘æœµæ˜¯å�¦é£žå‡ºå±�å¹•
     *
     * @return é£žå‡ºåˆ™è¿”å›žtrueï¼Œå�¦åˆ™è¿”å›žfalse
     */
    public boolean isOutFrame() {
        return x < -1 * scaleImageWidth;
    }

}

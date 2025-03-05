package Games.FlappyBird.com.kingyu.flappybird.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

/**
 * å·¥å…·ç±»ï¼Œæ¸¸æˆ�ä¸­ç”¨åˆ°çš„å·¥å…·éƒ½åœ¨æ­¤ç±»
 *
 * @author Kingyu
 */
public class GameUtil {

    private GameUtil() {
    } // ç§�æœ‰åŒ–ï¼Œé˜²æ­¢å…¶ä»–ç±»å®žä¾‹åŒ–æ­¤ç±»

    /**
     * è£…è½½å›¾ç‰‡çš„æ–¹æ³•
     *
     * @param imgPath å›¾ç‰‡è·¯å¾„
     * @return å›¾ç‰‡èµ„æº�
     */
    public static BufferedImage loadBufferedImage(String imgPath) {
        try {
            return ImageIO.read(new FileInputStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * åˆ¤æ–­ä»»æ„�æ¦‚çŽ‡çš„æ¦‚çŽ‡æ€§äº‹ä»¶æ˜¯å�¦å�‘ç”Ÿ
     *
     * @param numerator   åˆ†å­�ï¼Œä¸�å°�äºŽ0çš„å€¼
     * @param denominator åˆ†æ¯�ï¼Œä¸�å°�äºŽ0çš„å€¼
     * @return æ¦‚çŽ‡æ€§äº‹ä»¶å�‘ç”Ÿè¿”å›žtrueï¼Œå�¦åˆ™è¿”å›žfalse
     */
    public static boolean isInProbability(int numerator, int denominator) throws Exception {
        // åˆ†å­�åˆ†æ¯�ä¸�å°�äºŽ0
        if (numerator <= 0 || denominator <= 0) {
            throw new Exception("ä¼ å…¥äº†é�žæ³•çš„å�‚æ•°");
        }
        //åˆ†å­�å¤§äºŽåˆ†æ¯�ï¼Œä¸€å®šå�‘ç”Ÿ
        if (numerator >= denominator) {
            return true;
        }

        return getRandomNumber(1, denominator + 1) <= numerator;
    }

    /**
     * è¿”å›žæŒ‡å®šåŒºé—´çš„ä¸€ä¸ªéš�æœºæ•°
     *
     * @param min åŒºé—´æœ€å°�å€¼ï¼ŒåŒ…å�«
     * @param max åŒºé—´æœ€å¤§å€¼ï¼Œä¸�åŒ…å�«
     * @return è¯¥åŒºé—´çš„éš�æœºæ•°
     */
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * èŽ·å¾—æŒ‡å®šå­—ç¬¦ä¸²åœ¨æŒ‡å®šå­—ä½“çš„å®½é«˜
     */
    public static int getStringWidth(Font font, String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(str, frc).getWidth());
    }

    public static int getStringHeight(Font font, String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(str, frc).getHeight());
    }


    /**
     *
     * @param image:å›¾ç‰‡èµ„æº�
     * @param xï¼šxå��æ ‡
     * @param yï¼šyå��æ ‡
     * @param gï¼šç”»ç¬”
     */
    public static void drawImage(BufferedImage image, int x, int y, Graphics g) {
        g.drawImage(image, x, y, null);
    }

}

package Games.FlappyBird.com.kingyu.flappybird.component;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * æ¸¸æˆ�ç»“æ�Ÿç•Œé�¢
 *
 * @author Kingyu
 *
 */
public class GameOverAnimation {
    private final BufferedImage scoreImg; // è®¡åˆ†ç‰Œ
    private final BufferedImage overImg; // ç»“æ�Ÿæ ‡å¿—
    private final BufferedImage againImg; // ç»§ç»­æ ‡å¿—

    public GameOverAnimation(){
        overImg = GameUtil.loadBufferedImage(Constant.OVER_IMG_PATH);
        scoreImg = GameUtil.loadBufferedImage(Constant.SCORE_IMG_PATH);
        againImg = GameUtil.loadBufferedImage(Constant.AGAIN_IMG_PATH);
    }

    private static final int SCORE_LOCATE = 5; // è®¡åˆ†ç‰Œä½�ç½®è¡¥å�¿å�‚æ•°
    private int flash = 0; // å›¾ç‰‡é—ªçƒ�å�‚æ•°

    public void draw(Graphics g, Bird bird) {
        int x = Constant.FRAME_WIDTH - overImg.getWidth() >> 1;
        int y = Constant.FRAME_HEIGHT / 4;
        g.drawImage(overImg, x, y, null);

        // ç»˜åˆ¶è®¡åˆ†ç‰Œ
        x = Constant.FRAME_WIDTH - scoreImg.getWidth() >> 1;
        y = Constant.FRAME_HEIGHT / 3;
        g.drawImage(scoreImg, x, y, null);

        // ç»˜åˆ¶æœ¬å±€çš„åˆ†æ•°
        g.setColor(Color.white);
        g.setFont(Constant.SCORE_FONT);
        x = (Constant.FRAME_WIDTH - scoreImg.getWidth() / 2 >> 1) + SCORE_LOCATE;// ä½�ç½®è¡¥å�¿
        y += scoreImg.getHeight() >> 1;
        String str = Long.toString(bird.getCurrentScore());
        x -= GameUtil.getStringWidth(Constant.SCORE_FONT, str) >> 1;
        y += GameUtil.getStringHeight(Constant.SCORE_FONT, str);
        g.drawString(str, x, y);

        // ç»˜åˆ¶æœ€é«˜åˆ†æ•°
        if (bird.getBestScore() > 0) {
            str = Long.toString(bird.getBestScore());
            x = (Constant.FRAME_WIDTH + scoreImg.getWidth() / 2 >> 1) - SCORE_LOCATE;// ä½�ç½®è¡¥å�¿
            x -= GameUtil.getStringWidth(Constant.SCORE_FONT, str) >> 1;
            g.drawString(str, x, y);
        }

        // ç»˜åˆ¶ç»§ç»­æ¸¸æˆ�ï¼Œå›¾åƒ�é—ªçƒ�
        final int COUNT = 30; // é—ªçƒ�å‘¨æœŸ
        if (flash++ > COUNT)
            GameUtil.drawImage(againImg,Constant.FRAME_WIDTH - againImg.getWidth() >> 1, Constant.FRAME_HEIGHT / 5 * 3, g);
        if (flash == COUNT * 2) // é‡�ç½®é—ªçƒ�å�‚æ•°
            flash = 0;
    }
}

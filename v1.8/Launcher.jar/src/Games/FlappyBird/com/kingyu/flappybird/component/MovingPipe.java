package Games.FlappyBird.com.kingyu.flappybird.component;

import java.awt.Graphics;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;

/**
 * ç§»åŠ¨æ°´ç®¡ç±»ï¼Œç»§æ‰¿Pipeç±»
 *
 * @author Kingyu
 */

public class MovingPipe extends Pipe {

    private int dealtY; // ç§»åŠ¨æ°´ç®¡çš„å��æ ‡
    public static final int MAX_DELTA = 50; // æœ€å¤§ç§»åŠ¨è·�ç¦»
    private int direction;
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;

    // æž„é€ å™¨
    public MovingPipe() {
        super();
    }

    /**
     * è®¾ç½®æ°´ç®¡å�‚æ•°
     *
     * @param x:xå��æ ‡
     * @param yï¼šyå��æ ‡
     * @param heightï¼šæ°´ç®¡é«˜åº¦
     * @param typeï¼šæ°´ç®¡ç±»åž‹
     * @param visibleï¼šæ°´ç®¡å�¯è§�æ€§
     */
    public void setAttribute(int x, int y, int height, int type, boolean visible) {
        super.setAttribute(x, y, height, type, visible);
        dealtY = 0;
        direction = DIR_DOWN;
        if (type == TYPE_TOP_HARD) {
            direction = DIR_UP;
        }
    }

    // ç»˜åˆ¶æ–¹æ³•
    public void draw(Graphics g, Bird bird) {
        switch (type) {
            case TYPE_HOVER_HARD:
                drawHoverHard(g);
                break;
            case TYPE_TOP_HARD:
                drawTopHard(g);
                break;
            case TYPE_BOTTOM_HARD:
                drawBottomHard(g);
                break;

        }
        // é¸Ÿæ­»å�Žæ°´ç®¡å�œæ­¢ç§»åŠ¨
        if (bird.isDead()) {
            return;
        }
        movement();

        // ç»˜åˆ¶ç¢°æ’žçŸ©å½¢
//		g.setColor(Color.black);
//		g.drawRect((int) pipeRect.getX(), (int) pipeRect.getY(), (int) pipeRect.getWidth(), (int) pipeRect.getHeight());
    }

    // ç»˜åˆ¶ç§»åŠ¨çš„æ‚¬æµ®æ°´ç®¡
    private void drawHoverHard(Graphics g) {
        // æ‹¼æŽ¥çš„ä¸ªæ•°
        int count = (height - 2 * PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1;
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸Šé¡¶éƒ¨
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), y + dealtY, null);
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸»ä½“
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + dealtY + i * PIPE_HEIGHT + PIPE_HEAD_HEIGHT, null);
        }
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸‹åº•éƒ¨
        int y = this.y + height - PIPE_HEAD_HEIGHT;
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1), y + dealtY, null);
    }

    // ç»˜åˆ¶ä»Žä¸Šå¾€ä¸‹çš„ç§»åŠ¨æ°´ç®¡
    private void drawTopHard(Graphics g) {
        // æ‹¼æŽ¥çš„ä¸ªæ•°
        int count = (height - PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1; // å�–æ•´+1
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸»ä½“
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + dealtY + i * PIPE_HEIGHT, null);
        }
        // ç»˜åˆ¶æ°´ç®¡çš„é¡¶éƒ¨
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1),
                height - Constant.TOP_PIPE_LENGTHENING - PIPE_HEAD_HEIGHT + dealtY, null);
    }

    // ç»˜åˆ¶ä»Žä¸‹å¾€ä¸Šçš„ç§»åŠ¨æ°´ç®¡
    private void drawBottomHard(Graphics g) {
        // æ‹¼æŽ¥çš„ä¸ªæ•°
        int count = (height - PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1;
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸»ä½“
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAME_HEIGHT - PIPE_HEIGHT - i * PIPE_HEIGHT + dealtY, null);
        }
        // ç»˜åˆ¶æ°´ç®¡çš„é¡¶éƒ¨
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), Constant.FRAME_HEIGHT - height + dealtY, null);
    }

    /**
     * å�¯åŠ¨æ°´ç®¡çš„è¿�åŠ¨é€»è¾‘
     */
    private void movement() {
        //xå��æ ‡çš„è¿�åŠ¨é€»è¾‘ä¸Žæ™®é€šæ°´ç®¡ç›¸å�Œ
        x -= speed;
        pipeRect.x -= speed;
        if (x < -1 * PIPE_HEAD_WIDTH) {// æ°´ç®¡å®Œå…¨ç¦»å¼€äº†çª—å�£
            visible = false;
        }

        //æ°´ç®¡ä¸Šä¸‹ç§»åŠ¨çš„é€»è¾‘
        if (direction == DIR_DOWN) {
            dealtY++;
            if (dealtY > MAX_DELTA) {
                direction = DIR_UP;
            }
        } else {
            dealtY--;
            if (dealtY <= 0) {
                direction = DIR_DOWN;
            }
        }
        pipeRect.y = this.y + dealtY;
    }

}

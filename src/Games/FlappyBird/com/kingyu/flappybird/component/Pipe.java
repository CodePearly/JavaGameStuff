package Games.FlappyBird.com.kingyu.flappybird.component;

import java.awt.*;
import java.awt.image.BufferedImage;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.GameUtil;

/**
 * æ°´ç®¡ç±»ï¼Œå®žçŽ°æ°´ç®¡çš„ç»˜åˆ¶ä¸Žè¿�åŠ¨é€»è¾‘
 *
 * @author Kingyu
 */
public class Pipe {
    static BufferedImage[] imgs; // æ°´ç®¡çš„å›¾ç‰‡ï¼Œstaticä¿�è¯�å›¾ç‰‡å�ªåŠ è½½ä¸€æ¬¡

    static {// é�™æ€�ä»£ç �å�—ï¼Œç±»åŠ è½½çš„æ—¶å€™ï¼Œåˆ�å§‹åŒ–å›¾ç‰‡
        final int PIPE_IMAGE_COUNT = 3;
        imgs = new BufferedImage[PIPE_IMAGE_COUNT];
        for (int i = 0; i < PIPE_IMAGE_COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.PIPE_IMG_PATH[i]);
        }
    }

    // æ‰€æœ‰æ°´ç®¡çš„å®½é«˜
    public static final int PIPE_WIDTH = imgs[0].getWidth();
    public static final int PIPE_HEIGHT = imgs[0].getHeight();
    public static final int PIPE_HEAD_WIDTH = imgs[1].getWidth();
    public static final int PIPE_HEAD_HEIGHT = imgs[1].getHeight();

    int x, y; // æ°´ç®¡çš„å��æ ‡ï¼Œç›¸å¯¹äºŽå…ƒç´ å±‚
    int width, height; // æ°´ç®¡çš„å®½ï¼Œé«˜

    boolean visible; // æ°´ç®¡å�¯è§�çŠ¶æ€�ï¼Œtrueä¸ºå�¯è§�ï¼Œfalseè¡¨ç¤ºå�¯å½’è¿˜è‡³å¯¹è±¡æ± 
    // æ°´ç®¡çš„ç±»åž‹
    int type;
    public static final int TYPE_TOP_NORMAL = 0;
    public static final int TYPE_TOP_HARD = 1;
    public static final int TYPE_BOTTOM_NORMAL = 2;
    public static final int TYPE_BOTTOM_HARD = 3;
    public static final int TYPE_HOVER_NORMAL = 4;
    public static final int TYPE_HOVER_HARD = 5;

    // æ°´ç®¡çš„é€Ÿåº¦
    int speed;

    Rectangle pipeRect; // æ°´ç®¡çš„ç¢°æ’žçŸ©å½¢

    // æž„é€ å™¨
    public Pipe() {
        this.speed = Constant.GAME_SPEED;
        this.width = PIPE_WIDTH;

        pipeRect = new Rectangle();
        pipeRect.width = PIPE_WIDTH;
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
        this.x = x;
        this.y = y;
        this.height = height;
        this.type = type;
        this.visible = visible;
        setRectangle(this.x, this.y, this.height);
    }

    /**
     * è®¾ç½®ç¢°æ’žçŸ©å½¢å�‚æ•°
     */
    public void setRectangle(int x, int y, int height) {
        pipeRect.x = x;
        pipeRect.y = y;
        pipeRect.height = height;
    }

    // åˆ¤æ–­æ°´ç®¡æ˜¯å�¦ä½�äºŽçª—å�£
    public boolean isVisible() {
        return visible;
    }

    // ç»˜åˆ¶æ–¹æ³•
    public void draw(Graphics g, Bird bird) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawBottomNormal(g);
                break;
            case TYPE_HOVER_NORMAL:
                drawHoverNormal(g);
                break;
        }
//      //ç»˜åˆ¶ç¢°æ’žçŸ©å½¢
//      g.setColor(Color.black);
//      g.drawRect((int) pipeRect.getX(), (int) pipeRect.getY(), (int) pipeRect.getWidth(), (int) pipeRect.getHeight());

        //é¸Ÿæ­»å�Žæ°´ç®¡å�œæ­¢ç§»åŠ¨
        if (bird.isDead()) {
            return;
        }
        movement();
    }

    // ç»˜åˆ¶ä»Žä¸Šå¾€ä¸‹çš„æ™®é€šæ°´ç®¡
    private void drawTopNormal(Graphics g) {
        // æ‹¼æŽ¥çš„ä¸ªæ•°
        int count = (height - PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1; // å�–æ•´+1
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸»ä½“
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * PIPE_HEIGHT, null);
        }
        // ç»˜åˆ¶æ°´ç®¡çš„é¡¶éƒ¨
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1),
                height - Constant.TOP_PIPE_LENGTHENING - PIPE_HEAD_HEIGHT, null); // æ°´ç®¡å¤´éƒ¨ä¸Žæ°´ç®¡ä¸»ä½“çš„å®½åº¦ä¸�å�Œï¼Œxå��æ ‡éœ€è¦�å¤„ç�†
    }

    // ç»˜åˆ¶ä»Žä¸‹å¾€ä¸Šçš„æ™®é€šæ°´ç®¡
    private void drawBottomNormal(Graphics g) {
        // æ‹¼æŽ¥çš„ä¸ªæ•°
        int count = (height - PIPE_HEAD_HEIGHT - Constant.GROUND_HEIGHT) / PIPE_HEIGHT + 1;
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸»ä½“
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAME_HEIGHT - PIPE_HEIGHT - Constant.GROUND_HEIGHT - i * PIPE_HEIGHT,
                    null);
        }
        // ç»˜åˆ¶æ°´ç®¡çš„é¡¶éƒ¨
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), Constant.FRAME_HEIGHT - height, null);
    }

    // ç»˜åˆ¶æ‚¬æµ®çš„æ™®é€šæ°´ç®¡
    private void drawHoverNormal(Graphics g) {
        // æ‹¼æŽ¥çš„ä¸ªæ•°
        int count = (height - 2 * PIPE_HEAD_HEIGHT) / PIPE_HEIGHT + 1;
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸Šé¡¶éƒ¨
        g.drawImage(imgs[2], x - ((PIPE_HEAD_WIDTH - width) >> 1), y, null);
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸»ä½“
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * PIPE_HEIGHT + PIPE_HEAD_HEIGHT, null);
        }
        // ç»˜åˆ¶æ°´ç®¡çš„ä¸‹åº•éƒ¨
        int y = this.y + height - PIPE_HEAD_HEIGHT;
        g.drawImage(imgs[1], x - ((PIPE_HEAD_WIDTH - width) >> 1), y, null);
    }

    /**
     * æ™®é€šæ°´ç®¡çš„è¿�åŠ¨é€»è¾‘
     */
    private void movement() {
        x -= speed;
        pipeRect.x -= speed;
        if (x < -1 * PIPE_HEAD_WIDTH) {// æ°´ç®¡å®Œå…¨ç¦»å¼€äº†çª—å�£
            visible = false;
        }
    }

    /**
     * åˆ¤æ–­å½“å‰�æ°´ç®¡æ˜¯å�¦å®Œå…¨å‡ºçŽ°åœ¨çª—å�£ä¸­
     *
     * @return è‹¥å®Œå…¨å‡ºçŽ°åˆ™è¿”å›žtrueï¼Œå�¦åˆ™è¿”å›žfalse
     */
    public boolean isInFrame() {
        return x + width < Constant.FRAME_WIDTH;
    }

    // èŽ·å�–æ°´ç®¡çš„xå��æ ‡
    public int getX() {
        return x;
    }

    // èŽ·å�–æ°´ç®¡çš„ç¢°æ’žçŸ©å½¢
    public Rectangle getPipeRect() {
        return pipeRect;
    }

}

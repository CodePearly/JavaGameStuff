package Games.FlappyBird.com.kingyu.flappybird.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Games.FlappyBird.com.kingyu.flappybird.app.Game;
import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.GameUtil;
import Games.FlappyBird.com.kingyu.flappybird.util.MusicUtil;

/**
 * å°�é¸Ÿç±»ï¼Œå®žçŽ°å°�é¸Ÿçš„ç»˜åˆ¶ä¸Žé£žè¡Œé€»è¾‘
 *
 * @author Kingyu
 */
public class Bird {
    public static final int IMG_COUNT = 8; // å›¾ç‰‡æ•°é‡�
    public static final int STATE_COUNT = 4; // çŠ¶æ€�æ•°
    private final BufferedImage[][] birdImages; // å°�é¸Ÿçš„å›¾ç‰‡æ•°ç»„å¯¹è±¡
    private final int x;
    private int y; // å°�é¸Ÿçš„å��æ ‡
    private int wingState; // ç¿…è†€çŠ¶æ€�

    // å›¾ç‰‡èµ„æº�
    private BufferedImage image; // å®žæ—¶çš„å°�é¸Ÿå›¾ç‰‡

    // å°�é¸Ÿçš„çŠ¶æ€�
    private int state;
    public static final int BIRD_NORMAL = 0;
    public static final int BIRD_UP = 1;
    public static final int BIRD_FALL = 2;
    public static final int BIRD_DEAD_FALL = 3;
    public static final int BIRD_DEAD = 4;

    private final Rectangle birdCollisionRect; // ç¢°æ’žçŸ©å½¢
    public static final int RECT_DESCALE = 2; // è¡¥å�¿ç¢°æ’žçŸ©å½¢å®½é«˜çš„å�‚æ•°

    private final ScoreCounter counter; // è®¡åˆ†å™¨
    private final GameOverAnimation gameOverAnimation;

    public static int BIRD_WIDTH;
    public static int BIRD_HEIGHT;

    // åœ¨æž„é€ å™¨ä¸­å¯¹èµ„æº�åˆ�å§‹åŒ–
    public Bird() {
        counter = ScoreCounter.getInstance(); // è®¡åˆ†å™¨
        gameOverAnimation = new GameOverAnimation();

        // è¯»å�–å°�é¸Ÿå›¾ç‰‡èµ„æº�
        birdImages = new BufferedImage[STATE_COUNT][IMG_COUNT];
        for (int j = 0; j < STATE_COUNT; j++) {
            for (int i = 0; i < IMG_COUNT; i++) {
                birdImages[j][i] = GameUtil.loadBufferedImage(Constant.BIRDS_IMG_PATH[j][i]);
            }
        }

        assert birdImages[0][0] != null;
        BIRD_WIDTH = birdImages[0][0].getWidth();
        BIRD_HEIGHT = birdImages[0][0].getHeight();

        // åˆ�å§‹åŒ–å°�é¸Ÿçš„å��æ ‡
        x = Constant.FRAME_WIDTH >> 2;
        y = Constant.FRAME_HEIGHT >> 1;

        // åˆ�å§‹åŒ–ç¢°æ’žçŸ©å½¢
        int rectX = x - BIRD_WIDTH / 2;
        int rectY = y - BIRD_HEIGHT / 2;
        birdCollisionRect = new Rectangle(rectX + RECT_DESCALE, rectY + RECT_DESCALE * 2, BIRD_WIDTH - RECT_DESCALE * 3,
                BIRD_WIDTH - RECT_DESCALE * 4); // ç¢°æ’žçŸ©å½¢çš„å��æ ‡ä¸Žå°�é¸Ÿç›¸å�Œ
    }

    // ç»˜åˆ¶æ–¹æ³•
    public void draw(Graphics g) {
        movement();
        int state_index = Math.min(state, BIRD_DEAD_FALL); // å›¾ç‰‡èµ„æº�ç´¢å¼•
        // å°�é¸Ÿä¸­å¿ƒç‚¹è®¡ç®—
        int halfImgWidth = birdImages[state_index][0].getWidth() >> 1;
        int halfImgHeight = birdImages[state_index][0].getHeight() >> 1;
        if (velocity > 0)
            image = birdImages[BIRD_UP][0];
        g.drawImage(image, x - halfImgWidth, y - halfImgHeight, null); // xå��æ ‡äºŽçª—å�£1/4å¤„ï¼Œyå��æ ‡ä½�çª—å�£ä¸­å¿ƒ

        if (state == BIRD_DEAD)
            gameOverAnimation.draw(g, this);
        else if (state != BIRD_DEAD_FALL)
            drawScore(g);
        // ç»˜åˆ¶ç¢°æ’žçŸ©å½¢
//      g.setColor(Color.black);
//      g.drawRect((int) birdRect.getX(), (int) birdRect.getY(), (int) birdRect.getWidth(), (int) birdRect.getHeight());
    }

    public static final int ACC_FLAP = 14; // players speed on flapping
    public static final double ACC_Y = 2; // players downward acceleration
    public static final int MAX_VEL_Y = 15; // max vel along Y, max descend speed
    private int velocity = 0; // bird's velocity along Y, default same as playerFlapped
    private final int BOTTOM_BOUNDARY = Constant.FRAME_HEIGHT - GameBackground.GROUND_HEIGHT - (BIRD_HEIGHT / 2);

    // å°�é¸Ÿçš„é£žè¡Œé€»è¾‘
    private void movement() {
        // ç¿…è†€çŠ¶æ€�ï¼Œå®žçŽ°å°�é¸ŸæŒ¯ç¿…é£žè¡Œ
        wingState++;
        image = birdImages[Math.min(state, BIRD_DEAD_FALL)][wingState / 10 % IMG_COUNT];
        if (state == BIRD_FALL || state == BIRD_DEAD_FALL) {
            freeFall();
            if (birdCollisionRect.y > BOTTOM_BOUNDARY) {
                if (state == BIRD_FALL) {
                    MusicUtil.playCrash();
                }
                die();
            }
        }
    }

    private void freeFall() {
        if (velocity < MAX_VEL_Y)
            velocity -= ACC_Y;
        y = Math.min((y - velocity), BOTTOM_BOUNDARY);
        birdCollisionRect.y = birdCollisionRect.y - velocity;
    }

    private void die() {
        counter.saveScore();
        state = BIRD_DEAD;
        Game.setGameState(Game.STATE_OVER);
    }

    // å°�é¸ŸæŒ¯ç¿…
    public void birdFlap() {
        if (keyIsReleased()) {
            if (isDead())
                return;
            MusicUtil.playFly(); // æ’­æ”¾éŸ³æ•ˆ
            state = BIRD_UP;
            if (birdCollisionRect.y > Constant.TOP_BAR_HEIGHT) {
                velocity = ACC_FLAP; // æ¯�æ¬¡æŒ¯ç¿…å°†é€Ÿåº¦æ”¹ä¸ºä¸Šå�‡é€Ÿåº¦
                wingState = 0; // é‡�ç½®ç¿…è†€çŠ¶æ€�
            }
            keyPressed();
        }
    }

    // å°�é¸Ÿä¸‹é™�
    public void birdFall() {
        if (isDead())
            return;
        state = BIRD_FALL;
    }

    // å°�é¸Ÿå� è�½ï¼ˆå·²æ­»ï¼‰
    public void deadBirdFall() {
        state = BIRD_DEAD_FALL;
        MusicUtil.playCrash(); // æ’­æ”¾éŸ³æ•ˆ
        velocity = 0;  // é€Ÿåº¦ç½®0ï¼Œé˜²æ­¢å°�é¸Ÿç»§ç»­ä¸Šå�‡ä¸Žæ°´ç®¡é‡�å� 
    }

    // åˆ¤æ–­å°�é¸Ÿæ˜¯å�¦æ­»äº¡
    public boolean isDead() {
        return state == BIRD_DEAD_FALL || state == BIRD_DEAD;
    }

    // ç»˜åˆ¶å®žæ—¶åˆ†æ•°
    private void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(Constant.CURRENT_SCORE_FONT);
        String str = Long.toString(counter.getCurrentScore());
        int x = Constant.FRAME_WIDTH - GameUtil.getStringWidth(Constant.CURRENT_SCORE_FONT, str) >> 1;
        g.drawString(str, x, Constant.FRAME_HEIGHT / 10);
    }

    // é‡�ç½®å°�é¸Ÿ
    public void reset() {
        state = BIRD_NORMAL; // å°�é¸ŸçŠ¶æ€�
        y = Constant.FRAME_HEIGHT >> 1; // å°�é¸Ÿå��æ ‡
        velocity = 0; // å°�é¸Ÿé€Ÿåº¦

        int ImgHeight = birdImages[state][0].getHeight();
        birdCollisionRect.y = y - ImgHeight / 2 + RECT_DESCALE * 2; // å°�é¸Ÿç¢°æ’žçŸ©å½¢å��æ ‡

        counter.reset(); // é‡�ç½®è®¡åˆ†å™¨
    }

    private boolean keyFlag = true; // æŒ‰é”®çŠ¶æ€�ï¼Œtrueä¸ºå·²é‡Šæ”¾ï¼Œä½¿å½“æŒ‰ä½�æŒ‰é”®æ—¶ä¸�ä¼šé‡�å¤�è°ƒç”¨æ–¹æ³•

    public void keyPressed() {
        keyFlag = false;
    }

    public void keyReleased() {
        keyFlag = true;
    }

    public boolean keyIsReleased() {
        return keyFlag;
    }

    public long getCurrentScore() {
        return counter.getCurrentScore();
    }

    public long getBestScore() {
        return counter.getBestScore();
    }

    public int getBirdX() {
        return x;
    }

    // èŽ·å�–å°�é¸Ÿçš„ç¢°æ’žçŸ©å½¢
    public Rectangle getBirdCollisionRect() {
        return birdCollisionRect;
    }
}

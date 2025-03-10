package Games.FlappyBird.com.kingyu.flappybird.app;

import Games.FlappyBird.com.kingyu.flappybird.component.GameElementLayer;
import Games.FlappyBird.com.kingyu.flappybird.component.Bird;
import Games.FlappyBird.com.kingyu.flappybird.component.GameBackground;
import Games.FlappyBird.com.kingyu.flappybird.component.GameForeground;
import Games.FlappyBird.com.kingyu.flappybird.component.WelcomeAnimation;

import static Games.FlappyBird.com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static Games.FlappyBird.com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static Games.FlappyBird.com.kingyu.flappybird.util.Constant.FRAME_X;
import static Games.FlappyBird.com.kingyu.flappybird.util.Constant.FRAME_Y;
import static Games.FlappyBird.com.kingyu.flappybird.util.Constant.FPS;
import static Games.FlappyBird.com.kingyu.flappybird.util.Constant.GAME_TITLE;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/**
 * æ¸¸æˆ�ä¸»ä½“ï¼Œç®¡ç�†æ¸¸æˆ�çš„ç»„ä»¶å’Œçª—å�£ç»˜åˆ¶
 *
 * @author Kingyu
 */

public class Game extends Frame {
    private static final long serialVersionUID = 1L; // ä¿�æŒ�ç‰ˆæœ¬çš„å…¼å®¹æ€§

    private static int gameState; // æ¸¸æˆ�çŠ¶æ€�
    public static final int GAME_READY = 0; // æ¸¸æˆ�æœªå¼€å§‹
    public static final int GAME_START = 1; // æ¸¸æˆ�å¼€å§‹
    public static final int STATE_OVER = 2; // æ¸¸æˆ�ç»“æ�Ÿ

    private GameBackground background; // æ¸¸æˆ�èƒŒæ™¯å¯¹è±¡
    private GameForeground foreground; // æ¸¸æˆ�å‰�æ™¯å¯¹è±¡
    private Bird bird; // å°�é¸Ÿå¯¹è±¡
    private GameElementLayer gameElement; // æ¸¸æˆ�å…ƒç´ å¯¹è±¡
    private WelcomeAnimation welcomeAnimation; // æ¸¸æˆ�æœªå¼€å§‹æ—¶å¯¹è±¡

    // åœ¨æž„é€ å™¨ä¸­åˆ�å§‹åŒ–
    public Game() {
        initFrame(); // åˆ�å§‹åŒ–æ¸¸æˆ�çª—å�£
        setVisible(true); // çª—å�£é»˜è®¤ä¸ºä¸�å�¯è§�ï¼Œè®¾ç½®ä¸ºå�¯è§�
        initGame(); // åˆ�å§‹åŒ–æ¸¸æˆ�å¯¹è±¡
    }

    // åˆ�å§‹åŒ–æ¸¸æˆ�çª—å�£
    private void initFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // è®¾ç½®çª—å�£å¤§å°�
        setTitle(GAME_TITLE); // è®¾ç½®çª—å�£æ ‡é¢˜
        setLocation(FRAME_X, FRAME_Y); // çª—å�£åˆ�å§‹ä½�ç½®
        setResizable(false); // è®¾ç½®çª—å�£å¤§å°�ä¸�å�¯å�˜
        setIconImage(Toolkit.getDefaultToolkit().getImage(Games.FlappyBird.com.kingyu.flappybird.app.Game.class.getResource("/Games/FlappyBird/icon/FlappyBirdIcon.png")));
        // æ·»åŠ å…³é—­çª—å�£äº‹ä»¶ï¼ˆç›‘å�¬çª—å�£å�‘ç”Ÿçš„äº‹ä»¶ï¼Œæ´¾å�‘ç»™å�‚æ•°å¯¹è±¡ï¼Œå�‚æ•°å¯¹è±¡è°ƒç”¨å¯¹åº”çš„æ–¹æ³•ï¼‰
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // ç»“æ�Ÿç¨‹åº�
            }
        });
        addKeyListener(new BirdKeyListener()); // æ·»åŠ æŒ‰é”®ç›‘å�¬
    }

    // ç”¨äºŽæŽ¥æ”¶æŒ‰é”®äº‹ä»¶çš„å¯¹è±¡çš„å†…éƒ¨ç±»
    class BirdKeyListener implements KeyListener {
        // æŒ‰é”®æŒ‰ä¸‹ï¼Œæ ¹æ�®æ¸¸æˆ�å½“å‰�çš„çŠ¶æ€�è°ƒç”¨ä¸�å�Œçš„æ–¹æ³•
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (gameState) {
                case GAME_READY:
                    if (keycode == KeyEvent.VK_SPACE) {
                        // æ¸¸æˆ�å�¯åŠ¨ç•Œé�¢æ—¶æŒ‰ä¸‹ç©ºæ ¼ï¼Œå°�é¸ŸæŒ¯ç¿…ä¸€æ¬¡å¹¶å¼€å§‹å�—é‡�åŠ›å½±å“�
                        bird.birdFlap();
                        bird.birdFall();
                        setGameState(GAME_START); // æ¸¸æˆ�çŠ¶æ€�æ”¹å�˜
                    }
                    break;
                case GAME_START:
                    if (keycode == KeyEvent.VK_SPACE) {
                        //æ¸¸æˆ�è¿‡ç¨‹ä¸­æŒ‰ä¸‹ç©ºæ ¼åˆ™æŒ¯ç¿…ä¸€æ¬¡ï¼Œå¹¶æŒ�ç»­å�—é‡�åŠ›å½±å“�
                        bird.birdFlap();
                        bird.birdFall();
                    }
                    break;
                case STATE_OVER:
                    if (keycode == KeyEvent.VK_SPACE) {
                        //æ¸¸æˆ�ç»“æ�Ÿæ—¶æŒ‰ä¸‹ç©ºæ ¼ï¼Œé‡�æ–°å¼€å§‹æ¸¸æˆ�
                        resetGame();
                    }
                    break;
            }
        }

        // é‡�æ–°å¼€å§‹æ¸¸æˆ�
        private void resetGame() {
            setGameState(GAME_READY);
            gameElement.reset();
            bird.reset();
        }

        // æŒ‰é”®æ�¾å¼€ï¼Œæ›´æ”¹æŒ‰é”®çŠ¶æ€�æ ‡å¿—
        public void keyReleased(KeyEvent e) {
            int keycode = e.getKeyChar();
            if (keycode == KeyEvent.VK_SPACE) {
                bird.keyReleased();
            }
        }

        public void keyTyped(KeyEvent e) {
        }
    }

    // åˆ�å§‹åŒ–æ¸¸æˆ�ä¸­çš„å�„ä¸ªå¯¹è±¡
    private void initGame() {
        background = new GameBackground();
        gameElement = new GameElementLayer();
        foreground = new GameForeground();
        welcomeAnimation = new WelcomeAnimation();
        bird = new Bird();
        setGameState(GAME_READY);

        // å�¯åŠ¨ç”¨äºŽåˆ·æ–°çª—å�£çš„çº¿ç¨‹
        new Thread(() ->{
            while (true) {
                repaint(); // é€šè¿‡è°ƒç”¨repaint(),è®©JVMè°ƒç”¨update()
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // é¡¹ç›®ä¸­å­˜åœ¨ä¸¤ä¸ªçº¿ç¨‹ï¼šç³»ç»Ÿçº¿ç¨‹ï¼Œè‡ªå®šä¹‰çš„çº¿ç¨‹ï¼šè°ƒç”¨repaint()ã€‚
    // ç³»ç»Ÿçº¿ç¨‹ï¼šå±�å¹•å†…å®¹çš„ç»˜åˆ¶ï¼Œçª—å�£äº‹ä»¶çš„ç›‘å�¬ä¸Žå¤„ç�†
    // ä¸¤ä¸ªçº¿ç¨‹ä¼šæŠ¢å¤ºç³»ç»Ÿèµ„æº�ï¼Œå�¯èƒ½ä¼šå‡ºçŽ°ä¸€æ¬¡åˆ·æ–°å‘¨æœŸæ‰€ç»˜åˆ¶çš„å†…å®¹ï¼Œå¹¶æ²¡æœ‰åœ¨ä¸€æ¬¡åˆ·æ–°å‘¨æœŸå†…å®Œæˆ�
    // ï¼ˆå�Œç¼“å†²ï¼‰å�•ç‹¬å®šä¹‰ä¸€å¼ å›¾ç‰‡ï¼Œå°†éœ€è¦�ç»˜åˆ¶çš„å†…å®¹ç»˜åˆ¶åˆ°è¿™å¼ å›¾ç‰‡ï¼Œå†�ä¸€æ¬¡æ€§åœ°å°†å›¾ç‰‡ç»˜åˆ¶åˆ°çª—å�£
    private final BufferedImage bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    /**
     * ç»˜åˆ¶æ¸¸æˆ�å†…å®¹ å½“repaint()æ–¹æ³•è¢«è°ƒç”¨æ—¶ï¼ŒJVMä¼šè°ƒç”¨update()ï¼Œå�‚æ•°gæ˜¯ç³»ç»Ÿæ��ä¾›çš„ç”»ç¬”ï¼Œç”±ç³»ç»Ÿè¿›è¡Œå®žä¾‹åŒ–
     * å�•ç‹¬å�¯åŠ¨ä¸€ä¸ªçº¿ç¨‹ï¼Œä¸�æ–­åœ°å¿«é€Ÿè°ƒç”¨repaint()ï¼Œè®©ç³»ç»Ÿå¯¹æ•´ä¸ªçª—å�£è¿›è¡Œé‡�ç»˜
     */
    public void update(Graphics g) {
        Graphics bufG = bufImg.getGraphics(); // èŽ·å¾—å›¾ç‰‡ç”»ç¬”
        // ä½¿ç”¨å›¾ç‰‡ç”»ç¬”å°†éœ€è¦�ç»˜åˆ¶çš„å†…å®¹ç»˜åˆ¶åˆ°å›¾ç‰‡
        background.draw(bufG, bird); // èƒŒæ™¯å±‚
        foreground.draw(bufG, bird); // å‰�æ™¯å±‚
        if (gameState == GAME_READY) { // æ¸¸æˆ�æœªå¼€å§‹
            welcomeAnimation.draw(bufG);
        } else { // æ¸¸æˆ�ç»“æ�Ÿ
            gameElement.draw(bufG, bird); // æ¸¸æˆ�å…ƒç´ å±‚
        }
        bird.draw(bufG);
        g.drawImage(bufImg, 0, 0, null); // ä¸€æ¬¡æ€§å°†å›¾ç‰‡ç»˜åˆ¶åˆ°å±�å¹•ä¸Š
    }

    public static void setGameState(int gameState) {
        Game.gameState = gameState;
    }

}

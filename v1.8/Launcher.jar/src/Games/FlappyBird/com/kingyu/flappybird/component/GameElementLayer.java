package Games.FlappyBird.com.kingyu.flappybird.component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.GameUtil;

/**
 * æ¸¸æˆ�å…ƒç´ å±‚ï¼Œç›®å‰�ç®¡ç�†æ°´ç®¡çš„ç”Ÿæˆ�é€»è¾‘å¹¶ç»˜åˆ¶å®¹å™¨ä¸­çš„æ°´ç®¡
 *
 * @author Kingyu
 */

public class GameElementLayer {
    private final List<Pipe> pipes; // æ°´ç®¡çš„å®¹å™¨

    // æž„é€ å™¨
    public GameElementLayer() {
        pipes = new ArrayList<>();
    }

    // ç»˜åˆ¶æ–¹æ³•
    public void draw(Graphics g, Bird bird) {
        // é��åŽ†æ°´ç®¡å®¹å™¨ï¼Œå¦‚æžœå�¯è§�åˆ™ç»˜åˆ¶ï¼Œä¸�å�¯è§�åˆ™å½’è¿˜
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            if (pipe.isVisible()) {
                pipe.draw(g, bird);
            } else {
                Pipe remove = pipes.remove(i);
                PipePool.giveBack(remove);
                i--;
            }
        }
        // ç¢°æ’žæ£€æµ‹
        isCollideBird(bird);
        pipeBornLogic(bird);
    }

    /**
     * æ·»åŠ æ°´ç®¡çš„é€»è¾‘ï¼š å½“å®¹å™¨ä¸­æ·»åŠ çš„æœ€å�Žä¸€ä¸ªå…ƒç´ å®Œå…¨æ˜¾ç¤ºåˆ°å±�å¹•å�Žï¼Œæ·»åŠ ä¸‹ä¸€å¯¹ï¼› æ°´ç®¡æˆ�å¯¹åœ°ç›¸å¯¹åœ°å‡ºçŽ°ï¼Œç©ºéš™é«˜åº¦ä¸ºçª—å�£é«˜åº¦çš„1/6ï¼›
     * æ¯�å¯¹æ°´ç®¡çš„é—´éš”è·�ç¦»ä¸ºå±�å¹•é«˜åº¦çš„1/4ï¼› æ°´ç®¡çš„é«˜åº¦çš„å�–å€¼èŒƒå›´ä¸ºçª—å�£çš„[1/8~5/8]
     */
    public static final int VERTICAL_INTERVAL = Constant.FRAME_HEIGHT / 5;
    public static final int HORIZONTAL_INTERVAL = Constant.FRAME_HEIGHT >> 2;
    public static final int MIN_HEIGHT = Constant.FRAME_HEIGHT >> 3;
    public static final int MAX_HEIGHT = ((Constant.FRAME_HEIGHT) >> 3) * 5;

    private void pipeBornLogic(Bird bird) {
        if (bird.isDead()) {
            // é¸Ÿæ­»å�Žä¸�å†�æ·»åŠ æ°´ç®¡
            return;
        }
        if (pipes.size() == 0) {
            // è‹¥å®¹å™¨ä¸ºç©ºï¼Œåˆ™æ·»åŠ ä¸€å¯¹æ°´ç®¡
            int topHeight = GameUtil.getRandomNumber(MIN_HEIGHT, MAX_HEIGHT + 1); // éš�æœºç”Ÿæˆ�æ°´ç®¡é«˜åº¦

            Pipe top = PipePool.get("Pipe");
            top.setAttribute(Constant.FRAME_WIDTH, -Constant.TOP_PIPE_LENGTHENING,
                    topHeight + Constant.TOP_PIPE_LENGTHENING, Pipe.TYPE_TOP_NORMAL, true);

            Pipe bottom = PipePool.get("Pipe");
            bottom.setAttribute(Constant.FRAME_WIDTH, topHeight + VERTICAL_INTERVAL,
                    Constant.FRAME_HEIGHT - topHeight - VERTICAL_INTERVAL, Pipe.TYPE_BOTTOM_NORMAL, true);

            pipes.add(top);
            pipes.add(bottom);
        } else {
            // åˆ¤æ–­æœ€å�Žä¸€å¯¹æ°´ç®¡æ˜¯å�¦å®Œå…¨è¿›å…¥æ¸¸æˆ�çª—å�£ï¼Œè‹¥è¿›å…¥åˆ™æ·»åŠ æ°´ç®¡
            Pipe lastPipe = pipes.get(pipes.size() - 1); // èŽ·å¾—å®¹å™¨ä¸­æœ€å�Žä¸€ä¸ªæ°´ç®¡
            int currentDistance = lastPipe.getX() - bird.getBirdX() + Bird.BIRD_WIDTH / 2; // å°�é¸Ÿå’Œæœ€å�Žä¸€æ ¹æ°´ç®¡çš„è·�ç¦»
            final int SCORE_DISTANCE = Pipe.PIPE_WIDTH * 2 + HORIZONTAL_INTERVAL; // å°�äºŽå¾—åˆ†è·�ç¦»åˆ™å¾—åˆ†
            if (lastPipe.isInFrame()) {
                if (pipes.size() >= PipePool.FULL_PIPE - 2
                        && currentDistance <= SCORE_DISTANCE + Pipe.PIPE_WIDTH * 3 / 2) {
                    ScoreCounter.getInstance().score(bird);
                }
                try {
                    int currentScore = (int) ScoreCounter.getInstance().getCurrentScore() + 1; // èŽ·å�–å½“å‰�åˆ†æ•°
                    // ç§»åŠ¨æ°´ç®¡åˆ·æ–°çš„æ¦‚çŽ‡éš�å½“å‰�åˆ†æ•°é€’å¢žï¼Œå½“å¾—åˆ†å¤§äºŽ19å�Žå…¨éƒ¨åˆ·æ–°ç§»åŠ¨æ°´ç®¡
                    if (GameUtil.isInProbability(currentScore, 20)) {
                        if (GameUtil.isInProbability(1, 4)) // ç”Ÿæˆ�ç§»åŠ¨æ°´ç®¡å’Œç§»åŠ¨æ‚¬æµ®æ°´ç®¡çš„æ¦‚çŽ‡
                            addMovingHoverPipe(lastPipe);
                        else
                            addMovingNormalPipe(lastPipe);
                    } else {
                        if (GameUtil.isInProbability(1, 2)) // ç”Ÿæˆ�é�™æ­¢æ™®é€šæ°´ç®¡å’Œé�™æ­¢æ‚¬æµ®æ°´ç®¡çš„æ¦‚çŽ‡
                            addNormalPipe(lastPipe);
                        else
                            addHoverPipe(lastPipe);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * æ·»åŠ æ™®é€šæ°´ç®¡
     *
     * @param lastPipe ä¼ å…¥æœ€å�Žä¸€æ ¹æ°´ç®¡ä»¥èŽ·å�–xå��æ ‡
     */
    private void addNormalPipe(Pipe lastPipe) {
        int topHeight = GameUtil.getRandomNumber(MIN_HEIGHT, MAX_HEIGHT + 1); // éš�æœºç”Ÿæˆ�æ°´ç®¡é«˜åº¦
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // æ–°æ°´ç®¡çš„xå��æ ‡ = æœ€å�Žä¸€å¯¹æ°´ç®¡çš„xå��æ ‡ + æ°´ç®¡çš„é—´éš”

        Pipe top = PipePool.get("Pipe"); // ä»Žæ°´ç®¡å¯¹è±¡æ± ä¸­èŽ·å�–å¯¹è±¡

        // è®¾ç½®x, y, height, typeå±žæ€§
        top.setAttribute(x, -Constant.TOP_PIPE_LENGTHENING, topHeight + Constant.TOP_PIPE_LENGTHENING,
                Pipe.TYPE_TOP_NORMAL, true);

        Pipe bottom = PipePool.get("Pipe");
        bottom.setAttribute(x, topHeight + VERTICAL_INTERVAL, Constant.FRAME_HEIGHT - topHeight - VERTICAL_INTERVAL,
                Pipe.TYPE_BOTTOM_NORMAL, true);

        pipes.add(top);
        pipes.add(bottom);
    }

    /**
     * æ·»åŠ æ‚¬æµ®æ°´ç®¡
     *
     * @param lastPipe ä¼ å…¥æœ€å�Žä¸€æ ¹æ°´ç®¡ä»¥èŽ·å�–xå��æ ‡
     */
    private void addHoverPipe(Pipe lastPipe) {

        // éš�æœºç”Ÿæˆ�æ°´ç®¡é«˜åº¦,å±�å¹•é«˜åº¦çš„[1/4,1/6]
        int topHoverHeight = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 6, Constant.FRAME_HEIGHT / 4);
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // æ–°æ°´ç®¡çš„xå��æ ‡ = æœ€å�Žä¸€å¯¹æ°´ç®¡çš„xå��æ ‡ + æ°´ç®¡çš„é—´éš”
        int y = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 12, Constant.FRAME_HEIGHT / 6); // éš�æœºæ°´ç®¡çš„yå��æ ‡ï¼Œçª—å�£çš„[1/6,1/12]

        int type = Pipe.TYPE_HOVER_NORMAL;

        // ç”Ÿæˆ�ä¸Šéƒ¨çš„æ‚¬æµ®æ°´ç®¡
        Pipe topHover = PipePool.get("Pipe");
        topHover.setAttribute(x, y, topHoverHeight, type, true);

        // ç”Ÿæˆ�ä¸‹éƒ¨çš„æ‚¬æµ®æ°´ç®¡
        int bottomHoverHeight = Constant.FRAME_HEIGHT - 2 * y - topHoverHeight - VERTICAL_INTERVAL;
        Pipe bottomHover = PipePool.get("Pipe");
        bottomHover.setAttribute(x, y + topHoverHeight + VERTICAL_INTERVAL, bottomHoverHeight, type, true);

        pipes.add(topHover);
        pipes.add(bottomHover);

    }

    /**
     * æ·»åŠ ç§»åŠ¨çš„æ‚¬æµ®æ°´ç®¡
     *
     * @param lastPipe ä¼ å…¥æœ€å�Žä¸€æ ¹æ°´ç®¡ä»¥èŽ·å�–xå��æ ‡
     */
    private void addMovingHoverPipe(Pipe lastPipe) {

        // éš�æœºç”Ÿæˆ�æ°´ç®¡é«˜åº¦,å±�å¹•é«˜åº¦çš„[1/4,1/6]
        int topHoverHeight = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 6, Constant.FRAME_HEIGHT / 4);
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // æ–°æ°´ç®¡çš„xå��æ ‡ = æœ€å�Žä¸€å¯¹æ°´ç®¡çš„xå��æ ‡ + æ°´ç®¡çš„é—´éš”
        int y = GameUtil.getRandomNumber(Constant.FRAME_HEIGHT / 12, Constant.FRAME_HEIGHT / 6); // éš�æœºæ°´ç®¡çš„yå��æ ‡ï¼Œçª—å�£çš„[1/6,1/12]

        int type = Pipe.TYPE_HOVER_HARD;

        // ç”Ÿæˆ�ä¸Šéƒ¨çš„æ‚¬æµ®æ°´ç®¡
        Pipe topHover = PipePool.get("MovingPipe");
        topHover.setAttribute(x, y, topHoverHeight, type, true);

        // ç”Ÿæˆ�ä¸‹éƒ¨çš„æ‚¬æµ®æ°´ç®¡
        int bottomHoverHeight = Constant.FRAME_HEIGHT - 2 * y - topHoverHeight - VERTICAL_INTERVAL;
        Pipe bottomHover = PipePool.get("MovingPipe");
        bottomHover.setAttribute(x, y + topHoverHeight + VERTICAL_INTERVAL, bottomHoverHeight, type, true);

        pipes.add(topHover);
        pipes.add(bottomHover);

    }

    /**
     * æ·»åŠ ç§»åŠ¨çš„æ™®é€šæ°´ç®¡
     *
     * @param lastPipe ä¼ å…¥æœ€å�Žä¸€æ ¹æ°´ç®¡ä»¥èŽ·å�–xå��æ ‡
     */
    private void addMovingNormalPipe(Pipe lastPipe) {
        int topHeight = GameUtil.getRandomNumber(MIN_HEIGHT, MAX_HEIGHT + 1); // éš�æœºç”Ÿæˆ�æ°´ç®¡é«˜åº¦
        int x = lastPipe.getX() + HORIZONTAL_INTERVAL; // æ–°æ°´ç®¡çš„xå��æ ‡ = æœ€å�Žä¸€å¯¹æ°´ç®¡çš„xå��æ ‡ + æ°´ç®¡çš„é—´éš”

        Pipe top = PipePool.get("MovingPipe");
        top.setAttribute(x, -Constant.TOP_PIPE_LENGTHENING, topHeight + Constant.TOP_PIPE_LENGTHENING,
                Pipe.TYPE_TOP_HARD, true);

        Pipe bottom = PipePool.get("MovingPipe");
        bottom.setAttribute(x, topHeight + VERTICAL_INTERVAL, Constant.FRAME_HEIGHT - topHeight - VERTICAL_INTERVAL,
                Pipe.TYPE_BOTTOM_HARD, true);

        pipes.add(top);
        pipes.add(bottom);
    }

    /**
     * åˆ¤æ–­å…ƒç´ å’Œå°�é¸Ÿæ˜¯å�¦å�‘ç”Ÿç¢°æ’ž
     *
     * @param bird ä¼ å…¥å°�é¸Ÿå¯¹è±¡
     */
    public void isCollideBird(Bird bird) {
        // è‹¥é¸Ÿå·²æ­»åˆ™ä¸�å†�åˆ¤æ–­
        if (bird.isDead()) {
            return;
        }
        // é��åŽ†æ°´ç®¡å®¹å™¨
        for (Pipe pipe : pipes) {
            // åˆ¤æ–­ç¢°æ’žçŸ©å½¢æ˜¯å�¦æœ‰äº¤é›†
            if (pipe.getPipeRect().intersects(bird.getBirdCollisionRect())) {
                bird.deadBirdFall();
                return;
            }
        }
    }

    // é‡�ç½®å…ƒç´ å±‚
    public void reset() {
        for (Pipe pipe : pipes) {
            PipePool.giveBack(pipe);
        }
        pipes.clear();
    }
}

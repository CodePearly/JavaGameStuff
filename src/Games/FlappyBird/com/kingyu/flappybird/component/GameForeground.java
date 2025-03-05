package Games.FlappyBird.com.kingyu.flappybird.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.GameUtil;

/**
 * å‰�æ™¯å±‚ï¼Œç›®å‰�ç®¡ç�†äº‘æœµçš„ç”Ÿæˆ�é€»è¾‘å¹¶ç»˜åˆ¶å®¹å™¨ä¸­çš„äº‘æœµ
 *
 * @author Kingyu
 */
public class GameForeground {
    private final List<Cloud> clouds; // äº‘æœµçš„å®¹å™¨
    private final BufferedImage[] cloudImages; // å›¾ç‰‡èµ„æº�
    private long time; // æŽ§åˆ¶äº‘çš„é€»è¾‘è¿�ç®—å‘¨æœŸ
    public static final int CLOUD_INTERVAL = 100; //äº‘æœµåˆ·æ–°çš„é€»è¾‘è¿�ç®—çš„å‘¨æœŸ

    public GameForeground() {
        clouds = new ArrayList<>(); //äº‘æœµçš„å®¹å™¨
        // è¯»å…¥å›¾ç‰‡èµ„æº�
        cloudImages = new BufferedImage[Constant.CLOUD_IMAGE_COUNT];
        for (int i = 0; i < Constant.CLOUD_IMAGE_COUNT; i++) {
            cloudImages[i] = GameUtil.loadBufferedImage(Constant.CLOUDS_IMG_PATH[i]);
        }
        time = System.currentTimeMillis(); // èŽ·å�–å½“å‰�æ—¶é—´ï¼Œç”¨äºŽæŽ§åˆ¶äº‘çš„é€»è¾‘è¿�ç®—å‘¨æœŸ
    }

    // ç»˜åˆ¶æ–¹æ³•
    public void draw(Graphics g, Bird bird) {
        cloudBornLogic();
        for (Cloud cloud : clouds) {
            cloud.draw(g, bird);
        }
    }

    // äº‘æœµçš„æŽ§åˆ¶
    private void cloudBornLogic() {
        // 100msè¿�ç®—ä¸€æ¬¡
        if (System.currentTimeMillis() - time > CLOUD_INTERVAL) {
            time = System.currentTimeMillis(); // é‡�ç½®time
            // å¦‚æžœå±�å¹•çš„äº‘æœµçš„æ•°é‡�å°�äºŽå…�è®¸çš„æœ€å¤§æ•°é‡�ï¼Œæ ¹æ�®ç»™å®šçš„æ¦‚çŽ‡éš�æœºæ·»åŠ äº‘æœµ
            if (clouds.size() < Constant.MAX_CLOUD_COUNT) {
                try {
                    if (GameUtil.isInProbability(Constant.CLOUD_BORN_PERCENT, 100)) { // æ ¹æ�®ç»™å®šçš„æ¦‚çŽ‡æ·»åŠ äº‘æœµ
                        int index = GameUtil.getRandomNumber(0, Constant.CLOUD_IMAGE_COUNT); // éš�æœºé€‰å�–äº‘æœµå›¾ç‰‡

                        // äº‘æœµåˆ·æ–°çš„å��æ ‡
                        int x = Constant.FRAME_WIDTH; // ä»Žå±�å¹•å·¦ä¾§å¼€å§‹åˆ·æ–°
                        // yå��æ ‡éš�æœºåœ¨ä¸Š1/3å±�é€‰å�–
                        int y = GameUtil.getRandomNumber(Constant.TOP_BAR_HEIGHT, Constant.FRAME_HEIGHT / 3);

                        //å�‘å®¹å™¨ä¸­æ·»åŠ äº‘æœµ
                        Cloud cloud = new Cloud(cloudImages[index], x, y);
                        clouds.add(cloud);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // æ·»åŠ äº‘æœµ

            // è‹¥äº‘æœµé£žå‡ºå±�å¹•åˆ™ä»Žå®¹å™¨ä¸­ç§»é™¤
            for (int i = 0; i < clouds.size(); i++) {
                // é��åŽ†å®¹å™¨ä¸­çš„äº‘æœµ
                Cloud tempCloud = clouds.get(i);
                if (tempCloud.isOutFrame()) {
                    clouds.remove(i);
                    i--;
                }
            }
        }
    }
}

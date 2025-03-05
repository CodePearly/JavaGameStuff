package Games.FlappyBird.com.kingyu.flappybird.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.GameUtil;

/**
 * æ¸¸æˆ�èƒŒæ™¯ç±»ï¼Œå®žçŽ°æ¸¸æˆ�èƒŒæ™¯çš„ç»˜åˆ¶
 * 
 * @author Kingyu
 *
 */
public class GameBackground {

	private static final BufferedImage BackgroundImg;// èƒŒæ™¯å›¾ç‰‡

	private final int speed; // èƒŒæ™¯å±‚çš„é€Ÿåº¦
	private int layerX; // èƒŒæ™¯å±‚çš„å��æ ‡

	public static final int GROUND_HEIGHT;

	static {
		BackgroundImg = GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
		assert BackgroundImg != null;
		GROUND_HEIGHT = BackgroundImg.getHeight() / 2;
	}

	// åœ¨æž„é€ å™¨ä¸­åˆ�å§‹åŒ–
	public GameBackground() {
		this.speed = Constant.GAME_SPEED;
		this.layerX = 0;
	}

	// ç»˜åˆ¶æ–¹æ³•
	public void draw(Graphics g, Bird bird) {
		// ç»˜åˆ¶èƒŒæ™¯è‰²
		g.setColor(Constant.BG_COLOR);
		g.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

		// èŽ·å¾—èƒŒæ™¯å›¾ç‰‡çš„å°ºå¯¸
		int imgWidth = BackgroundImg.getWidth();
		int imgHeight = BackgroundImg.getHeight();

		int count = Constant.FRAME_WIDTH / imgWidth + 2; // æ ¹æ�®çª—å�£å®½åº¦å¾—åˆ°å›¾ç‰‡çš„ç»˜åˆ¶æ¬¡æ•°
		for (int i = 0; i < count; i++) {
			g.drawImage(BackgroundImg, imgWidth * i - layerX, Constant.FRAME_HEIGHT - imgHeight, null);
		}
		
		if(bird.isDead()) {  //å°�é¸Ÿæ­»äº¡åˆ™ä¸�å†�ç»˜åˆ¶
			return;
		}
		movement();
	}

	// èƒŒæ™¯å±‚çš„è¿�åŠ¨é€»è¾‘
	private void movement() {
		layerX += speed;
		if (layerX > BackgroundImg.getWidth())
			layerX = 0;
	}
}

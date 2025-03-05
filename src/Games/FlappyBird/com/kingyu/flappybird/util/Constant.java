package Games.FlappyBird.com.kingyu.flappybird.util;

import java.awt.Color;
import java.awt.Font;

/**
 * å¸¸é‡�ç±»
 * 
 * @author Kingyu å�Žç»­ä¼˜åŒ–å�¯å†™å…¥æ•°æ�®åº“æˆ–æ–‡ä»¶ä¸­ï¼Œä¾¿äºŽä¿®æ”¹
 */

public class Constant {
	// çª—å�£å°ºå¯¸
	public static final int FRAME_WIDTH = 420;
	public static final int FRAME_HEIGHT = 640;

	// æ¸¸æˆ�æ ‡é¢˜
	public static final String GAME_TITLE = "Flappy Bird written by Kingyu";

	// çª—å�£ä½�ç½®
	public static final int FRAME_X = 600;
	public static final int FRAME_Y = 100;

	// å›¾åƒ�èµ„æº�è·¯å¾„
	public static final String BG_IMG_PATH = "resources/img/background.png"; // èƒŒæ™¯å›¾ç‰‡

	// å°�é¸Ÿå›¾ç‰‡
	public static final String[][] BIRDS_IMG_PATH = {
			{ "resources/img/0.png", "resources/img/1.png", "resources/img/2.png", "resources/img/3.png",
					"resources/img/4.png", "resources/img/5.png", "resources/img/6.png", "resources/img/7.png" },
			{ "resources/img/up.png", "resources/img/up.png", "resources/img/up.png", "resources/img/up.png",
					"resources/img/up.png", "resources/img/up.png", "resources/img/up.png", "resources/img/up.png" },
			{ "resources/img/down_0.png", "resources/img/down_1.png", "resources/img/down_2.png",
					"resources/img/down_3.png", "resources/img/down_4.png", "resources/img/down_5.png",
					"resources/img/down_6.png", "resources/img/down_7.png" },
			{ "resources/img/dead.png", "resources/img/dead.png", "resources/img/dead.png", "resources/img/dead.png",
					"resources/img/dead.png", "resources/img/dead.png", "resources/img/dead.png",
					"resources/img/dead.png", } };

	// äº‘æœµå›¾ç‰‡
	public static final String[] CLOUDS_IMG_PATH = { "resources/img/cloud_0.png", "resources/img/cloud_1.png" };

	// æ°´ç®¡å›¾ç‰‡
	public static final String[] PIPE_IMG_PATH = { "resources/img/pipe.png", "resources/img/pipe_top.png",
			"resources/img/pipe_bottom.png" };

	public static final String TITLE_IMG_PATH = "resources/img/title.png";
	public static final String NOTICE_IMG_PATH = "resources/img/start.png";
	public static final String SCORE_IMG_PATH = "resources/img/score.png";
	public static final String OVER_IMG_PATH = "resources/img/over.png";
	public static final String AGAIN_IMG_PATH = "resources/img/again.png";

	public static final String SCORE_FILE_PATH = "resources/score"; // åˆ†æ•°æ–‡ä»¶è·¯å¾„

	// æ¸¸æˆ�é€Ÿåº¦ï¼ˆæ°´ç®¡å�ŠèƒŒæ™¯å±‚çš„ç§»åŠ¨é€Ÿåº¦ï¼‰
	public static final int GAME_SPEED = 4;

	// æ¸¸æˆ�èƒŒæ™¯è‰²
	public static final Color BG_COLOR = new Color(0x4bc4cf);

	// æ¸¸æˆ�åˆ·æ–°çŽ‡
	public static final int FPS = 1000 / 30;

	// æ ‡é¢˜æ �é«˜åº¦
	public static final int TOP_BAR_HEIGHT = 20;

	// åœ°é�¢é«˜åº¦
	public static final int GROUND_HEIGHT = 35;

	// ä¸Šæ–¹ç®¡é�“åŠ é•¿
	public static final int TOP_PIPE_LENGTHENING = 100;

	public static final int CLOUD_BORN_PERCENT = 6; // äº‘æœµç”Ÿæˆ�çš„æ¦‚çŽ‡ï¼Œå�•ä½�ä¸ºç™¾åˆ†æ¯”
	public static final int CLOUD_IMAGE_COUNT = 2; // äº‘æœµå›¾ç‰‡çš„ä¸ªæ•°
	public static final int MAX_CLOUD_COUNT = 7; // äº‘æœµçš„æœ€å¤§æ•°é‡�

	public static final Font CURRENT_SCORE_FONT = new Font("å�Žæ–‡ç�¥ç�€", Font.BOLD, 32);// å­—ä½“
	public static final Font SCORE_FONT = new Font("å�Žæ–‡ç�¥ç�€", Font.BOLD, 24);// å­—ä½“

}

package Games.FlappyBird.com.kingyu.flappybird.component;

import java.util.ArrayList;
import java.util.List;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;

/**
 * æ°´ç®¡å¯¹è±¡æ± 
 * ä¸ºäº†é�¿å…�å��å¤�åœ°åˆ›å»ºå’Œé”€æ¯�å¯¹è±¡ï¼Œä½¿ç”¨å¯¹è±¡æ± æ�¥æ��å‰�åˆ›å»ºå¥½ä¸€äº›å¯¹è±¡ï¼Œä½¿ç”¨æ—¶ä»Žå¯¹è±¡æ± ä¸­èŽ·å¾—ï¼Œä½¿ç”¨å®Œå�Žå½’è¿˜
 * 
 * @author Kingyu
 *
 */
public class PipePool {
	private static final List<Pipe> pool = new ArrayList<>(); // æ± ä¸­å¯¹è±¡çš„å®¹å™¨
	private static final List<MovingPipe> movingPool = new ArrayList<>(); // æ± ä¸­å¯¹è±¡çš„å®¹å™¨
	public static final int MAX_PIPE_COUNT = 30; // å¯¹è±¡æ± ä¸­å¯¹è±¡çš„æœ€å¤§ä¸ªæ•°ï¼Œè‡ªè¡Œå®šä¹‰
	public static final int FULL_PIPE = (Constant.FRAME_WIDTH
			/ (Pipe.PIPE_HEAD_WIDTH + GameElementLayer.HORIZONTAL_INTERVAL) + 2) * 2;

	static {
		for (int i = 0; i < PipePool.FULL_PIPE; i++) {
			pool.add(new Pipe());
		}
		for (int i = 0; i < PipePool.FULL_PIPE; i++) {
			movingPool.add(new MovingPipe());
		}
	}

	/**
	 * ä»Žå¯¹è±¡æ± ä¸­èŽ·å�–ä¸€ä¸ªå¯¹è±¡
	 * 
	 * @return ä¼ å…¥å¯¹è±¡çš„ç±»åž‹ï¼Œä»¥åˆ¤æ–­ä»Žå“ªä¸ªå¯¹è±¡æ± ä¸­èŽ·å�–
	 */
	public static Pipe get(String className) {
		if ("Pipe".equals(className)) {
			int size = pool.size();
			if (size > 0) {
				return pool.remove(size - 1); // ç§»é™¤å¹¶è¿”å›žæœ€å�Žä¸€ä¸ª
			} else {
				return new Pipe(); // ç©ºå¯¹è±¡æ± ï¼Œè¿”å›žä¸€ä¸ªæ–°å¯¹è±¡
			}
		} else {
			int size = movingPool.size();
			if (size > 0) {
				return movingPool.remove(size - 1); // ç§»é™¤å¹¶è¿”å›žæœ€å�Žä¸€ä¸ª
			} else {
				return new MovingPipe(); // ç©ºå¯¹è±¡æ± ï¼Œè¿”å›žä¸€ä¸ªæ–°å¯¹è±¡
			}
		}
	}

	/**
	 * å½’è¿˜å¯¹è±¡ç»™å®¹å™¨
	 */
	public static void giveBack(Pipe pipe) {
		//åˆ¤æ–­ç±»çš„ç±»åž‹
		if(pipe.getClass() == Pipe.class) {
			if (pool.size() < MAX_PIPE_COUNT) {
				pool.add(pipe);
			}
		}else {
			if (movingPool.size() < MAX_PIPE_COUNT) {
				movingPool.add((MovingPipe)pipe);
			}
		}
	}
}

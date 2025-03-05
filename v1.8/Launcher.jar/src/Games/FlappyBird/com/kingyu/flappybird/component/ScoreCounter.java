package Games.FlappyBird.com.kingyu.flappybird.component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import Games.FlappyBird.com.kingyu.flappybird.util.Constant;
import Games.FlappyBird.com.kingyu.flappybird.util.MusicUtil;

/**
 * æ¸¸æˆ�è®¡æ—¶å™¨, ä½¿ç”¨é�™æ€�å†…éƒ¨ç±»å®žçŽ°äº†å�•ä¾‹æ¨¡å¼�
 *
 * @author Kingyu
 *
 */
public class ScoreCounter {

	private static class ScoreCounterHolder {
		private static final ScoreCounter scoreCounter = new ScoreCounter();
	}

	public static ScoreCounter getInstance() {
		return ScoreCounterHolder.scoreCounter;
	}

	private long score = 0; // åˆ†æ•°
	private long bestScore; // æœ€é«˜åˆ†æ•°

	private ScoreCounter() {
		bestScore = -1;
		try {
			loadBestScore();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// è£…è½½æœ€é«˜çºªå½•
	private void loadBestScore() throws Exception {
		File file = new File(Constant.SCORE_FILE_PATH);
		if (file.exists()) {
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			bestScore = dis.readLong();
			dis.close();
		}
	}

	public void saveScore() {
		bestScore = Math.max(bestScore, getCurrentScore());
		try {
			File file = new File(Constant.SCORE_FILE_PATH);
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
			dos.writeLong(bestScore);
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void score(Bird bird) {
		if (!bird.isDead()) {
			MusicUtil.playScore();
			score += 1;
		}
	}

	public long getBestScore() {
		return bestScore;
	}

	public long getCurrentScore() {
		return score;
	}

	public void reset() {
		score = 0;
	}

}
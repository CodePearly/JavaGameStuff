package Games.FlappyBird.com.kingyu.flappybird.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * éŸ³ä¹�å·¥å…·ç±»
 *
 * @author Kingyu
 * wavéŸ³é¢‘ï¼šJDKæ��ä¾›çš„ç±»å�¯ç›´æŽ¥è§£ç � mp3éŸ³é¢‘ï¼šJDKæ²¡æœ‰æ��ä¾›æ”¯æŒ�ï¼Œéœ€è¦�ä½¿ç”¨ç¬¬ä¸‰æ–¹çš„å·¥å…·åŒ…
 */
public class MusicUtil {

    private static AudioStream fly;
    private static AudioStream crash;
    private static AudioStream score;

    // wavæ’­æ”¾
    public static void playFly() {
        try {
            // create an AudioStream from the InputStream
            InputStream flyIn = new FileInputStream("resources/wav/fly.wav");
            fly = new AudioStream(flyIn);
        } catch (IOException ignored) {
        }
        AudioPlayer.player.start(fly);
    }

    public static void playCrash() {
        try {
            // create an AudioStream from the InputStream
            InputStream crashIn = new FileInputStream("resources/wav/crash.wav");
            crash = new AudioStream(crashIn);
        } catch (IOException ignored) {
        }
        AudioPlayer.player.start(crash);
    }

    public static void playScore() {
        try {
            // create an AudioStream from the InputStream
            InputStream scoreIn = new FileInputStream("resources/wav/score.wav");
            score = new AudioStream(scoreIn);
        } catch (IOException ignored) {
        }
        AudioPlayer.player.start(score);
    }
}

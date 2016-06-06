import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author O. Kaan Demiröz
 *
 */
@SuppressWarnings("javadoc")
public interface Constants {
	

	static final float SCALE = 1f;
	static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static final int BORDER_WIDTH = 3;
	static final int HEIGHT_TASKBAR = -5;
	static final float WIDTH = (float) ((screenSize.getWidth()-BORDER_WIDTH)*SCALE);
	static final float HEIGHT = (float) ((screenSize.getHeight()-HEIGHT_TASKBAR)*SCALE);
	static final float BLOCK_WIDTH = 0.04f;
	static final float BLOCK_HEIGHT = 0.02f;
	static final float BLOCK_DEPTH = BLOCK_HEIGHT;
	static final float TEPSI_WIDTH = BLOCK_WIDTH * 4;
	static final float TEPSI_HEIGHT = BLOCK_HEIGHT;
	static final float TEPSI_DEPTH = BLOCK_DEPTH * 4;
	static final int BLOCK_COUNT_X = 15;
	static final int BLOCK_COUNT_Y = 8;
	static final int BLOCK_COUNT_Z = 3;
	static final float BALL_RADIUS = BLOCK_HEIGHT * 0.5f;
	static float BALL_SPEED = BALL_RADIUS;
	static float BONUS_SPEED = BALL_SPEED * 0.8f;
	static float BONUS_PROB = 0.07f;
	static final int FROM_BELOW = 1;
	static final int FROM_LEFT = 2;
	static final int FROM_ABOVE = 3;
	static final int FROM_RIGHT = 4;
	static final int FROM_FRONT = 5;
	static final int FROM_BACK = 6;
	static final int TIMER_GAP = 3;
	static final int TEPSI_NUM_SEGMENTS = 9;
}

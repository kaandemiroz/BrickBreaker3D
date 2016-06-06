import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Box;

/**
 * @author OKD
 *
 */
public class Tepsi extends ColorObject{

	private TransparencyAttributes ta;
	private int numSegments;
	
	/**
	 * @param width 
	 * @param height 
	 * @param depth 
	 * @param translation 
	 * @param ap 
	 */
	public Tepsi(float width, float height, float depth, Vector3f translation, Appearance ap){
		super(new Box(width, height, depth, ap), translation);
		setNumSegments(Constants.TEPSI_NUM_SEGMENTS);
		ap.setTransparencyAttributes(ta = new TransparencyAttributes());
		ta.setTransparencyMode(TransparencyAttributes.BLENDED);
		ta.setTransparency(0.3f);
	}

	/**
	 * @param width 
	 * @param height 
	 * @param depth 
	 * @param x 
	 * @param y 
	 * @param z 
	 * @param ap 
	 */
	public Tepsi(float width, float height, float depth, float x, float y, float z, Appearance ap){
		this(width, height, depth, new Vector3f(x, y, z), ap);
	}

	/**
	 * @return the number of segments
	 */
	public int getNumSegments() {
		return numSegments;
	}

	/**
	 * @param numSegments the number of segments to set
	 */
	public void setNumSegments(int numSegments) {
		this.numSegments = numSegments;
	}

}

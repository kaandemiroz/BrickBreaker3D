import javax.media.j3d.*;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;

/**
 * @author OKD
 *
 */
public class Block extends RectangularObject{
	
	/**
	 * @param width 
	 * @param height 
	 * @param depth 
	 * @param translation 
	 * @param ap 
	 */
	public Block(float width, float height, float depth, Vector3f translation, Appearance ap){
		super(new Box(width, height, depth, ap), translation);
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
	public Block(float width, float height, float depth, float x, float y, float z, Appearance ap){
		this(width, height, depth, new Vector3f(x, y, z), ap);
	}
	
}

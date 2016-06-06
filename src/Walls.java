import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.Box;

/**
 * @author OKD
 *
 */
public class Walls extends TransformGroup{

	private Transform3D transform;
	private ColorObject leftWall;
	private ColorObject rightWall;
	private ColorObject frontWall;
	private ColorObject floor;
	private ColorObject ceiling;
	private TransparencyAttributes ta;
	
	/**
	 * @param roomWidth
	 * @param roomHeight
	 * @param roomDepth
	 * @param wallWidth
	 * @param ap 
	 */
	public Walls(float roomWidth, float roomHeight, float roomDepth, float wallWidth, Appearance ap){
		super();
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		ap.setTransparencyAttributes(ta = new TransparencyAttributes());
		ta.setTransparencyMode(TransparencyAttributes.NICEST);
		ta.setTransparency(0.9f);
		leftWall = new ColorObject(new Box(wallWidth, roomHeight, roomDepth, ap), -roomWidth - wallWidth, 0, 0);
		rightWall = new ColorObject(new Box(wallWidth, roomHeight, roomDepth, ap), roomWidth + wallWidth, 0, 0);
		frontWall = new ColorObject(new Box(roomWidth, wallWidth, roomDepth, ap), 0, roomHeight + wallWidth, 0);
		floor = new ColorObject(new Box(roomWidth, roomHeight, wallWidth, ap), 0, 0, -roomDepth - wallWidth);
		ceiling = new ColorObject(new Box(roomWidth, roomHeight, wallWidth, ap), 0, 0, roomDepth + wallWidth);
		transform = new Transform3D();
		setTransform(transform);
		addChild(leftWall);
		addChild(rightWall);
		addChild(frontWall);
		addChild(floor);
		addChild(ceiling);
	}
	
}

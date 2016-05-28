import javax.media.j3d.*;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;

/**
 * @author OKD
 *
 */
public class RectangularObject extends ColorObject{
	
	private Box border;
	private Appearance ap;
	private PolygonAttributes pa;
	private ColoringAttributes ca;
	private LineAttributes la;
	
	/**
	 * @param box
	 * @param translation
	 */
	public RectangularObject(Box box, Vector3f translation){
		super(box, translation);
		
		border = (Box) box.cloneTree();
		border.setAppearance(ap = new Appearance());
		
		ap.setColoringAttributes(ca = new ColoringAttributes());
		ca.setColor(0, 0, 0);
		
		ap.setLineAttributes(la = new LineAttributes());
//		la.setLineAntialiasingEnable(true);
		la.setLineWidth(2f);
		
		ap.setPolygonAttributes(pa = new PolygonAttributes());
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
//		addChild(border);
	}

	/**
	 * @param box
	 * @param x
	 * @param y
	 * @param z
	 */
	public RectangularObject(Box box, float x, float y, float z) {
		this(box, new Vector3f(x, y, z));
	}

}

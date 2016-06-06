import java.util.Enumeration;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;

/**
 * @author OKD
 *
 */
public class Ball extends ColorObject{
	
	private float radius;
	
	/**
	 * @param translation
	 * @param radius
	 * @param ap
	 */
	public Ball(float radius, Vector3f translation, Appearance ap){
		super(new Sphere(radius,ap), translation);
		this.setRadius(radius);
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 * @param ap
	 */
	public Ball(float radius, float x, float y, float z, Appearance ap){
		this(radius, new Vector3f(x, y, z), ap);
	}
	
	/**
	 * @param delta
	 */
	public void move(Vector3f delta){
		Vector3f curPosition = getTranslation();
		Vector3f newPosition = new Vector3f(curPosition.getX() + delta.getX(),
				curPosition.getY() + delta.getY(),
				curPosition.getZ() + delta.getZ());
		setTranslation(newPosition);
	}

	/**
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

}

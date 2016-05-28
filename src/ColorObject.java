import javax.media.j3d.*;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Primitive;

/**
 * @author OKD
 *
 */
public class ColorObject extends TransformGroup{

	private Transform3D transform;
	private Primitive object;
	private Appearance ap;

	/**
	 * @param object 
	 * @param translation 
	 */
	public ColorObject(Primitive object, Vector3f translation){
		super();
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.object = object;
		this.object.setCollidable(true);
		ap = this.object.getAppearance();
		transform = new Transform3D();
		transform.setTranslation(translation);
		setTransform(transform);
		addChild(object);
	}

	/**
	 * @param object 
	 * @param x 
	 * @param y 
	 * @param z 
	 */
	public ColorObject(Primitive object, float x, float y, float z){
		this(object, new Vector3f(x, y, z));
	}
	
	/**
	 * @return the Primitive object
	 */
	public Primitive getObject(){
		return object;
	}

	/**
	 * @return the translation vector of this object 
	 */
	public Vector3f getTranslation(){
		Vector3f translation = new Vector3f();
		transform.get(translation);
		return translation;
	}

	/**
	 * @param vector
	 */
	public void setTranslation(Vector3f vector){
		transform.setTranslation(vector);
		setTransform(transform);
	}

	/**
	 * @return the appearance of this object
	 */
	public Appearance getAppearance(){
		return ap;
	}

	/**
	 * @param ap
	 */
	public void setAppearance(Appearance ap){
		this.ap = ap;
		object.setAppearance(ap);
	}

	/**
	 * @param x
	 */
	public void setX(float x){
		Vector3f translation = getTranslation();
		translation.setX(x);
		setTranslation(translation);
	}

	/**
	 * @param y
	 */
	public void setY(float y){
		Vector3f translation = getTranslation();
		translation.setY(y);
		setTranslation(translation);
	}

	/**
	 * @param z
	 */
	public void setZ(float z){
		Vector3f translation = getTranslation();
		translation.setZ(z);
		setTranslation(translation);
	}  
	
}
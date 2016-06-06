import javax.media.j3d.*;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Primitive;

/**
 * @author OKD
 *
 */
public class ColorObject extends BranchGroup{

	private TransformGroup tg;
	private Transform3D transform;
	private Primitive object;
	private Appearance ap;

	/**
	 * @param object 
	 * @param translation 
	 */
	public ColorObject(Primitive object, Vector3f translation){
		super();
		this.object = object;
		setCollidable(true);
		ap = this.object.getAppearance();
		tg = new TransformGroup();
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transform = new Transform3D();
		transform.setTranslation(translation);
		tg.setTransform(transform);
		tg.addChild(object);
		addChild(tg);
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
		tg.setTransform(transform);
	}
	
	/**
	 * @return the transform group of this branch group
	 */
	public TransformGroup getTransformGroup(){
		return tg;
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
	
	/**
	 * @return the x coordinate of the transform group
	 */
	public float getX(){
		return getTranslation().getX();
	}
	
	/**
	 * @return the y coordinate of the transform group
	 */
	public float getY(){
		return getTranslation().getY();
	}
	
	/**
	 * @return the z coordinate of the transform group
	 */
	public float getZ(){
		return getTranslation().getZ();
	}
	
}

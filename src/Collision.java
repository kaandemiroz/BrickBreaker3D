import java.util.Enumeration;
import javax.media.j3d.*;

import com.sun.j3d.utils.geometry.Primitive;

/**
 * Collision Detection
 * @author sawyera.2016
 */
public class Collision extends Behavior {
	
	/** The separate criteria used to wake up this behavior. */
	protected WakeupCriterion[] theCriteria;

	/** The OR of the separate criteria. */
	protected WakeupOr orCriteria;

	/** The shape that is watched for collision. */
	protected Primitive collidingShape;

	protected CollisionListener collisionListener;
	
	/**
	 * @param theShape
	 *            Shape3D that is to be watched for collisions.
	 * @param theBounds
	 *            Bounds that define the active region for this behavior
	 * @param collisionListener 
	 */
	public Collision(Primitive theShape, Bounds theBounds, CollisionListener collisionListener) {
		collidingShape = theShape;
		this.collisionListener = collisionListener;
		setSchedulingBounds(theBounds);
	}

	/**
	 * This creates an entry, exit and movement collision criteria. These are
	 * then OR'ed together, and the wake up condition set to the result.
	 */
	@Override
	public void initialize() {
		theCriteria = new WakeupCriterion[3];
		theCriteria[0] = new WakeupOnCollisionEntry(collidingShape);
		theCriteria[1] = new WakeupOnCollisionExit(collidingShape);
		theCriteria[2] = new WakeupOnCollisionMovement(collidingShape);
		orCriteria = new WakeupOr(theCriteria);
		wakeupOn(orCriteria);
	}

	/**
	 * Where the work is done in this class. A message is printed out using the
	 * userData of the object collided with. The wake up condition is then set
	 * to the OR'ed criterion again.
	 */
	@Override
	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration criteria) {
		WakeupCriterion theCriterion = (WakeupCriterion) criteria.nextElement();
		if (theCriterion instanceof WakeupOnCollisionEntry) {
			Node theLeaf = ((WakeupOnCollisionEntry) theCriterion)
					.getTriggeringPath().getObject();
//			System.out.println("Collided with " + theLeaf.getUserData());
			collisionListener.onCollisionStart(collidingShape.getBounds(), theLeaf.getBounds());
		} else if (theCriterion instanceof WakeupOnCollisionExit) {
			Node theLeaf = ((WakeupOnCollisionExit) theCriterion)
					.getTriggeringPath().getObject();
//			System.out.println("Stopped colliding with  " + theLeaf.getUserData());
			collisionListener.onCollisionEnd(collidingShape.getBounds(), theLeaf.getBounds());
		} else {
			Node theLeaf = ((WakeupOnCollisionMovement) theCriterion)
					.getTriggeringPath().getObject();
//			System.out.println("Moved whilst colliding with " + theLeaf.getUserData());
			collisionListener.onCollision(collidingShape.getBounds(), theLeaf.getBounds());
		}
		wakeupOn(orCriteria);
	}
	
}
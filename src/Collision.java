import java.util.Enumeration;
import javax.media.j3d.*;

import com.sun.j3d.utils.geometry.Primitive;

/**
 * Collision Detection
 * @author sawyera.2016
 */
public class Collision extends Behavior {

	protected CollisionListener collisionListener;
	private Group group;

	private WakeupOnCollisionEntry wEnter;
	private WakeupOnCollisionExit wExit;
	
	/**
	 * @param cdGroup
	 *            BranchGroup that is to be watched for collisions.
	 * @param schedulingBounds
	 *            Bounds that define the active region for this behavior
	 * @param collisionListener 
	 */
	public Collision(Group cdGroup, Bounds schedulingBounds, CollisionListener collisionListener) {
		group = cdGroup;
		this.collisionListener = collisionListener;
		setSchedulingBounds(schedulingBounds);
	}

	/**
	 * This creates an entry and exit collision criteria. The wake up condition is set to entry.
	 */
	@Override
	public void initialize() {
		wEnter = new WakeupOnCollisionEntry(group);
		wExit = new WakeupOnCollisionExit(group);
		wakeupOn(wEnter);
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
			collisionListener.onCollisionStart(theLeaf.getParent().getParent().getParent(), theLeaf.getBounds());
			wakeupOn(wExit);
		} else {
			Node theLeaf = ((WakeupOnCollisionExit) theCriterion)
					.getTriggeringPath().getObject();
//			System.out.println("Stopped colliding with  " + theLeaf.getUserData());
			collisionListener.onCollisionEnd(theLeaf.getParent().getParent().getParent(), theLeaf.getBounds());
			wakeupOn(wEnter);
		}
	}
	
}
import javax.media.j3d.Bounds;

/**
 * @author OKD
 *
 */
public interface CollisionListener {
	
	/**
	 * @param ballBounds 
	 * @param objectBounds 
	 */
	public void onCollision(Bounds ballBounds, Bounds objectBounds);
	
	/**
	 * @param ballBounds 
	 * @param objectBounds 
	 */
	public void onCollisionStart(Bounds ballBounds, Bounds objectBounds);
	
	/**
	 * @param ballBounds 
	 * @param objectBounds 
	 */
	public void onCollisionEnd(Bounds ballBounds, Bounds objectBounds);
	
}
import javax.media.j3d.Node;

/**
 * @author OKD
 *
 */
public interface CollisionListener {
	
	/**
	 * @param group 
	 * @param shape 
	 */
	public void onCollisionStart(Node group, Node shape);
	
	/**
	 * @param group 
	 * @param shape 
	 */
	public void onCollisionEnd(Node group, Node shape);
	
}
import javax.media.j3d.Bounds;
import javax.media.j3d.Node;

/**
 * @author OKD
 *
 */
public interface CollisionListener {
	
	/**
	 * @param node 
	 * @param bounds 
	 */
	public void onCollisionStart(Node node, Bounds bounds);
	
	/**
	 * @param node 
	 * @param bounds 
	 */
	public void onCollisionEnd(Node node, Bounds bounds);
	
}
import javax.media.j3d.Bounds;

/**
 * @author OKD
 *
 */
public class CollisionEvent {
	
	private Bounds bounds1;
	private Bounds bounds2;
	
	/**
	 * @param bounds1
	 * @param bounds2
	 */
	public CollisionEvent(Bounds bounds1, Bounds bounds2){
		this.bounds1 = bounds1;
		this.bounds2 = bounds2;
	}

	/**
	 * @return the bounds1
	 */
	public Bounds getBounds1() {
		return bounds1;
	}

	/**
	 * @param bounds1 the bounds1 to set
	 */
	public void setBounds1(Bounds bounds1) {
		this.bounds1 = bounds1;
	}

	/**
	 * @return the bounds2
	 */
	public Bounds getBounds2() {
		return bounds2;
	}

	/**
	 * @param bounds2 the bounds2 to set
	 */
	public void setBounds2(Bounds bounds2) {
		this.bounds2 = bounds2;
	}
	
}

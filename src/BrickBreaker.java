import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import java.util.Random;

/**
 * @author OKD
 *
 */
public class BrickBreaker extends Applet implements KeyListener, MouseMotionListener, MouseListener, CollisionListener {

	private static final long serialVersionUID = 1L;
	private MainFrame frame;
	private Canvas3D canvas;
	private SimpleUniverse universe;
	private BranchGroup group = new BranchGroup();
	private Timer timer;
	private Walls walls;
	private BlockMatrix blockMatrix;
	private Tepsi tepsi;
	private Ball ball;
	private boolean mode = false;
	private Vector3f ballDelta;

	/**
	 * @param group
	 */
	public void addLights(){
		// Create lights
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Color3f light1Color = new Color3f(1f, 1f, 1f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);

		AmbientLight light2 = new AmbientLight(new Color3f(.5f,.5f,.5f));
		light2.setInfluencingBounds(bounds);
		group.addChild(light2);
	}

	/**
	 * @return the scene elements
	 */
	public BranchGroup getScene(){
		addLights();
		// Set up colors
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f gray = new Color3f(0.6f, 0.6f, 0.6f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f blue = new Color3f(.15f, .15f, 0.7f);
		Color3f red = new Color3f(0.7f, .15f, .15f);

		Appearance wap = new Appearance();
		wap.setMaterial(new Material(blue, blue, blue, gray, 100.0f));

		Appearance tap = new Appearance();
		tap.setMaterial(new Material(red, black, red, white, 100.0f));

		Appearance bap = new Appearance();
		bap.setMaterial(new Material(gray, black, gray, white, 30.0f));

		walls = new Walls(1.0f, 1.0f, 0.2f, 0.05f, wap);

		blockMatrix = new BlockMatrix(	Constants.BLOCK_COUNT_X,
				Constants.BLOCK_COUNT_Y,
				Constants.BLOCK_COUNT_Z,
				0.0f, 0.4f, 0.0f);

		tepsi = new Tepsi(	Constants.TEPSI_WIDTH,
				Constants.TEPSI_HEIGHT,
				Constants.TEPSI_DEPTH,
				0.0f, -0.4f, 0.0f,
				tap	);

		ball = new Ball(	Constants.BALL_RADIUS,
				0.0f, -0.25f, 0.0f,
				bap	);

		group.addChild(walls);
		group.addChild(blockMatrix);
		group.addChild(tepsi);
		group.addChild(ball);

		//		CollisionDetectorGroup cdGroup = new CollisionDetectorGroup(ball);
		//		cdGroup.setSchedulingBounds(ball.getBounds());//new BoundingSphere(new Point3d(), 10000.0));
		//		ball.addChild(cdGroup);

		Collision collision = new Collision(ball, ball.getBounds(), this);

		group.addChild(collision);

		return group;
	}

	/**
	 * 
	 */
	public void positionViewer() {
		ViewingPlatform vp = universe.getViewingPlatform();
		TransformGroup tg = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		tg.getTransform(t3d);
		vp.setNominalViewingTransform();
	}

	/**
	 * @param point
	 * @return the vector that looks at the origin from the given point
	 */
	public Transform3D lookTowardsOriginFrom(Point3d point){
		Transform3D lookAt = new Transform3D();
		Vector3d up = new Vector3d(point.x, point.y + 1, point.z);
		lookAt.lookAt(point, new Point3d(0.0, 0.0, 0.0), up);
		lookAt.invert();
		return lookAt;
	}

	public void init(){
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(config);
		universe = new SimpleUniverse(canvas);
		add("Center",canvas);
		positionViewer();
		BranchGroup content = getScene();
		content.compile();

		frame.addKeyListener(this);
		canvas.addKeyListener(this);
		frame.addMouseMotionListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseListener(this);	

		//		Random rand = new Random();
		//		ballDelta = new Vector3f(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		ballDelta = new Vector3f(0.0005f,0.0025f,0.0001f);
		timer = new Timer(Constants.TIMER_GAP, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//				ball.rotate(0.1);
				ball.move(ballDelta);
			}
		});

		ViewingPlatform viewPlatform = universe.getViewingPlatform();
		BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0f, 0f, 0f), 100f);
		// Add a behavior to viewPlatform, allowing the user to rotate, zoom, and straff the scene
		OrbitBehavior orbitBehaviour = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL | OrbitBehavior.STOP_ZOOM);
		orbitBehaviour.setSchedulingBounds(boundingSphere);
		viewPlatform.setViewPlatformBehavior(orbitBehaviour);
		Transform3D move = lookTowardsOriginFrom(new Point3d(0, -2, 1));
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(move);
		universe.addBranchGraph(group);
	}

	/**
	 * Starts/stops the game according to the value of m
	 * @param m
	 */
	public void setMode(boolean m){
		if(mode == m) return;
		mode = m;
		if (mode){
			timer.start();
		}else{
			timer.stop();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		tepsi.setX((2*e.getX()/Constants.WIDTH - 1f) * 0.8f);
		tepsi.setZ((0.5f - e.getY()/Constants.HEIGHT) * 0.5f);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		tepsi.setX((2*e.getX()/Constants.WIDTH - 1f) * 0.8f);
		tepsi.setZ((0.5f - e.getY()/Constants.HEIGHT) * 0.5f);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()){
		case 'i':
		case 'I':
		case 'ı':
		case 'İ':
			Transform3D move = lookTowardsOriginFrom(new Point3d(0, -2, 1));
			universe.getViewingPlatform().getViewPlatformTransform().setTransform(move);
			break;
		case 'p':
		case 'P':
			setMode(!mode);
			break;
		case 'q':
		case 'Q':
			System.exit(0);
			break;
		default:
			break;
		}
		if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {    
			//if the JFrame has Title bar
			if (frame.isUndecorated()) {
				//this will dispose your JFrame
				frame.dispose();
				//here to set it with no Title bar
				frame.setUndecorated(false);
				frame.pack();
				frame.setLocationRelativeTo(null);
				//as you dispose your JFrame, you have to re-make it Visible..
				frame.setVisible(true);
			} else {
				frame.dispose();
				frame.setUndecorated(true);
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		setMode(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) {	}

	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) {	}


	private int getCollisionDirection(ColorObject block){
		float radius = ball.getRadius();
		if(ball.getY() <= block.getY() + Constants.BLOCK_HEIGHT
				&& getX() <= block.getX() + Constants.BLOCK_WIDTH 
				&& getY() >= block.getY() + Constants.BLOCK_HEIGHT// - delta
				&& getX()+2*radius >= block.getX())
			return Constants.FROM_FRONT;
		
		else if(ball.getY() <= block.getY() + Constants.BLOCK_HEIGHT 
				&& ball.getX()+2*radius <= block.getX()// + delta 
				&& ball.getY()+2*radius >= block.getY()
				&& ball.getX()+2*radius >= block.getX())
			return Constants.FROM_LEFT;
		
		else if(ball.getY()+2*radius <= block.getY()// + delta
				&& ball.getX() <= block.getX()+Constants.BLOCK_WIDTH 
				&& ball.getY()+2*radius >= block.getY()
				&& ball.getX()+2*radius >= block.getX())
			return Constants.FROM_BACK;
		
		else if(ball.getY() <= block.getY() + Constants.BLOCK_HEIGHT 
				&& ball.getX() <= block.getX() + Constants.BLOCK_WIDTH 
				&& ball.getY()+2*radius >= block.getY()
				&& ball.getX() >= block.getX() + Constants.BLOCK_WIDTH)// - delta){
			return Constants.FROM_RIGHT;
		
		else if(ball.getY() <= block.getY() + Constants.BLOCK_HEIGHT 
				&& ball.getX() <= block.getX() + Constants.BLOCK_WIDTH 
				&& ball.getY()+2*radius >= block.getY()
				&& ball.getX() >= block.getX() + Constants.BLOCK_WIDTH)// - delta){
			return Constants.FROM_ABOVE;
		
		else if(ball.getY() <= block.getY() + Constants.BLOCK_HEIGHT 
				&& ball.getX() <= block.getX() + Constants.BLOCK_WIDTH 
				&& ball.getY()+2*radius >= block.getY()
				&& ball.getX() >= block.getX() + Constants.BLOCK_WIDTH)// - delta){
			return Constants.FROM_BELOW;
		
		return 0;
	}

	/**
	 * Initialize Application Window
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");
		BrickBreaker object = new BrickBreaker();
		object.frame = new MainFrame(object, args, (int)Constants.WIDTH, (int)Constants.HEIGHT);
		object.frame.setAlwaysOnTop(true);
		object.frame.setLocation(-Constants.BORDER_WIDTH,-Constants.BORDER_WIDTH);
		//		object.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		object.frame.setResizable(false);
		object.validate();

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		object.frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor"));
	}

	@Override
	public void onCollisionStart(Node node, Bounds bounds) {
		System.out.println("Collided");
		System.out.println("ballBounds: " + ball.getBounds());
		System.out.println("objectBounds: " + bounds);
		switch(getCollisionDirection((ColorObject) node)){
			case Constants.FROM_ABOVE:
			case Constants.FROM_BELOW:
				ballDelta.setZ(-ballDelta.getZ());
				break;
			case Constants.FROM_BACK:
			case Constants.FROM_FRONT:
				ballDelta.setY(-ballDelta.getY());
				break;
			case Constants.FROM_LEFT:
			case Constants.FROM_RIGHT:
				ballDelta.setX(-ballDelta.getX());
				break;
		}
		if(node instanceof Block) blockMatrix.destroyBlock((Block) node);
	}

	@Override
	public void onCollisionEnd(Node node, Bounds bounds) {
		System.out.println("Stopped colliding");
		System.out.println("ballBounds: " + ball.getBounds());
	}

}
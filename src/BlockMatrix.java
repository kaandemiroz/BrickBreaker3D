import java.util.Random;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 * @author O. Kaan Demiröz
 *
 */
public class BlockMatrix extends TransformGroup{

	private Transform3D transform;
	private Block[][][] blockMatrix;
	private int matrixX;
	private int matrixY;
	private int matrixZ;
	
	/**
	 * @param matrixX
	 * @param matrixY
	 * @param matrixZ 
	 */
	public BlockMatrix(int matrixX, int matrixY, int matrixZ, float x, float y, float z){
		super();
		transform = new Transform3D();
		transform.setTranslation(new Vector3f(	x - matrixX * Constants.BLOCK_WIDTH,
												y + matrixY * Constants.BLOCK_HEIGHT,
												z - matrixZ * Constants.BLOCK_DEPTH	));
		blockMatrix = new Block[matrixZ][matrixY][matrixX];
		this.matrixX = matrixX;
		this.matrixY = matrixY;
		this.matrixZ = matrixZ;
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Random rand = new Random();
		for(int k=0; k<matrixZ; k++){
			for(int j=0; j<matrixY; j++){
				for(int i=0; i<matrixX; i++){
					Color3f randColor = new Color3f(rand.nextFloat()/2, rand.nextFloat()/2, rand.nextFloat()/2);
					Appearance ap = new Appearance();
					ap.setMaterial(new Material(randColor, black, randColor, white, 30.0f));
					blockMatrix[k][j][i] = new Block(	Constants.BLOCK_WIDTH,
													Constants.BLOCK_HEIGHT,
													Constants.BLOCK_HEIGHT,
													i * 2 * Constants.BLOCK_WIDTH,
													-j * 2 * Constants.BLOCK_HEIGHT,
													k * 2 * Constants.BLOCK_DEPTH,
													ap);
					addChild(blockMatrix[k][j][i]);
				}
			}
		}
		setTransform(transform);
	}
	
	
	
}

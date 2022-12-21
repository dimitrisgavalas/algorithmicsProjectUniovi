package algstudent.s7;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import algstudent.s6.Image;
import algstudent.s7.pyramid.utils.BranchAndBound;
import algstudent.s7.pyramid.utils.Node;


/**s
 * Constructor for Image objects
 * @param board Representation image
 */
public class ImageAveragerBnB extends BranchAndBound{
	//constructor that passes board as the initial node
	public ImageAveragerBnB(ImageBoard board) {
	    rootNode = board; //we create the image. as we add board at the initial node
	}
}
/***************************************************/


/***************************************************/
class ImageBoard extends Node {

	private Image real_img, bad_img; //to store the main good and main bad image
	private int width, height; //to store the width and height of the image
	private Image[] dataset; //dataset to store all the images (good and bad ones)
	private Image leftImageSet, rightImageSet;
	private Image result;
	private double zncc;
	private static int n;
	private int counter;
	private double s_noise = 5.0;
	
	/**
	 * Constructor(root node)
	 * @param n Size of images
	 */
	public ImageBoard(int n) {
		ImageBoard.n = n;		
		String path = Paths.get("").toAbsolutePath().toString() + "/algstudent/s6/einstein_1_256.png";
		
		int n_bad = (int) ((25/100.)*n);//set 25% how bad
		int n_real = n - n_bad;
		assert (n_real >= 1) && (n_bad < n_real);
		
		//load rference with image
		this.real_img = new Image(path);
		this.bad_img = new Image(path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		this.zncc = Integer.MAX_VALUE;
		
		//create the dataset as an array of
		//unordered randomly chosen real and bad images
		int total_imgs = n_real + n_bad; 
		this.dataset = new Image[total_imgs]; //the data set for the total of images
		int[] rand_index = this.randomIndexes(total_imgs); //random array of positions to mix images
		
		Image hold_img; //temp images
		int region = 0; 

		for (int i=0; i<n_real; i++) 
		{ 
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.real_img); //save the image
			hold_img.suppressRegion(region); //a half part of the image is deleted
			hold_img.addNoise(s_noise ); //add some noise 
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}		
		region = 0;		
		for (int i=n_real; i<n_real+n_bad; i++) { //to save bad images
			hold_img = new Image(this.width, this.height); //generate image of width and height
			hold_img.addSignal(this.bad_img); //save the image. insert einstein picture
			hold_img.invertSignal(); //corrupt the image	//ex if pic is 3 i put -3
			hold_img.suppressRegion(region); //the fourth part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise.	why do we add noise
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
		
		this.leftImageSet = new Image(this.width, this.height); 
		this.rightImageSet = new Image(this.width, this.height); 
	}
	
	/**
	 * To generate a random array of positions
	 * @param n Length of the array
	 * @return 
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * Store resulting images for testing
	 * @out_dir directory save the output images
	 */
	public void saveResults(String out_dir) {
		this.result.save(out_dir + "/result.png");
	}

	/**
	 * @return the number of steps carried out by the algorithm to solve the problem
	 */
	public int getCounter() {
		return counter;
	}

	 /**
     * Counts the number of blanks that are not yet filled
     */
    @Override
    public void calculateHeuristicValue() {    	
    	if (prune()){
    		heuristicValue = Integer.MAX_VALUE;
    	}
    	else {
    		// priority sorting in BnB relies on a Heap for the shake of efficiency, for this reason we need 
    		//to convert a maximization problem into a minimization one. That can be easily achieved by multiplying ZNCC 
    		//by -1 and changing the sing of the slopes in Fig. 1    		
    		heuristicValue = (int) (zncc() * -1);
    	}
    }
    
    /** Computes the ZNCC between both image dataset halves
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.leftImageSet.zncc(this.rightImageSet);
	}


	/**
	 * Checks if we should prune when the conditions are not longer met
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() {
		return (zncc() * -1) > zncc;
	}
	
	/**
	 *  if the depth of bnb tree = position n true
	 */
	@Override
	public boolean isSolution() {
		if (this.depth == n)
		{
			this.result = new Image(this.width, this.height); 
			result.addSignal(leftImageSet);
			result.addSignal(rightImageSet);
			saveResults(Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s7/out_bb/");
			return true;
		}
		return false;
	}

	/**
	 * To get the children of the current node. They 
     * point to their parent through the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();	
		Image leftCopy = leftImageSet.copy();
		Image rightCopy = rightImageSet.copy();
		ImageBoard temp;
		double previousZncc;
		
		if (depth != 0) {
			previousZncc = zncc();
		}else {
			previousZncc = Integer.MAX_VALUE;
		}
		//we now have the position of the asteriustic
		for (int k=0; k<3; k++)
		{
			if (k == 1){
				leftCopy.addSignal(this.dataset[depth]);
			}
			if (k == 2){
				rightCopy.addSignal(this.dataset[depth]);
			}
			temp = new ImageBoard(depth + 1, leftCopy, rightCopy, this.getID(), previousZncc, this.dataset, this.width, this.height);
			result.add(temp);
			leftCopy = leftImageSet.copy();
			rightCopy = rightImageSet.copy();
		}
		
		return result;
	}
	
	/**
	 * Constructor (children of the root node)
	 * @param board
	 * @param depth
	 * @param parentID
	 */
	public ImageBoard(int depth, Image leftCopy, Image rightCopy, UUID parentID, double zncc, Image[] dataset, int width, int height) {
    	this.depth = depth;
    	this.parentID = parentID;
    	this.leftImageSet = leftCopy;
    	this.rightImageSet = rightCopy;
    	this.zncc = zncc;
    	this.dataset = dataset;
    	this.width = width;
    	this.height = height;
    	calculateHeuristicValue();
	}

	
} 

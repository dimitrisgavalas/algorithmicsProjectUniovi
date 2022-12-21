package algstudent.s6;
/*
 * formula that calculates similarity between pics
 * already implemented in image class
 * when = 1 exactly the same
 * when = -1 oposite
 * when = 0 all values are 0
 * 
 * we need to add
 * greedy algorithm = splitSubsetsGreedy = 
 * for loop(1 to number of pics to generate){
 * generate random number r
 * if(r = 1 ) pic to group == 1
 * if r=2 pic to group 2
 * if r=3 ignore the picture } 
 * after loop we calculate similarity between group 1 and group 2 with image averaging from image class
 * N_trie_greedy = number of tries
 * in the end we choose the one closer to 1. (max)
 * 
 * 
 * backtracking algorithm
 * splitSubsetsBacktracking faster than greedy
 * 
 * complexity of picture ... 0(3^n)
 * 
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageAverager {
	
	private Image real_img, bad_img; //to store the main good and main bad image
	private Image avg_img, half1_img, half2_img; //to store the final tests to see if we improve the previous results
	private Image[] dataset; //dataset to store all the images (good and bad ones)
	private int[] sol; //to store the partial results (where I am putting the pictures? 0->not assigned, 1->first half, 2->second half
	private int[] bestSol; //to store the best solution
	private int width, height; //to store the width and height of the image
	//backtracking variables
	private int counter; //to store the number of times we assign an image to half1, half2 or no group
	private double max_zncc; //to store the best ZNCC
	
	/** Constructor
	* @real_path  path to the real image (pattern to find) on disk
	* @bad_path  path to the bad image on disk
	* @n_real  number of real images in the dataset (>= 1)
	* @n_bad  number of bad images in the dataset 
	* @s_noise  standard deviation for noise 
	*/
	public ImageAverager(String real_path, String bad_path, int n_real, int n_bad, double s_noise) { //"","",10,3,5.0
		assert (n_real >= 1) && (n_bad < n_real);//assert at least one reference image //enables you to test your assumptions about your program. 
		
		//load reference and bad images
		this.real_img = new Image(real_path);
		this.bad_img = new Image(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		
		//create the dataset as an array of unordered randomly chosen real and bad images
		int total_imgs = n_real + n_bad; //the total number of images are the good + the bad ones
		this.dataset = new Image[total_imgs]; //the data set for the total of images
		this.sol = new int[total_imgs]; //we will use this variable during the process 0->not assigned, 1->first half, 2->second half 
		this.bestSol = new int[total_imgs]; //we will use this variable to store the best results
		int[] rand_index = this.randomIndexes(total_imgs); //random array of positions to mix images
		Image hold_img; //temp images
		int region = 0; // 0-up, 1-down, 2-left, 3-right
		for (int i=0; i<n_real; i++) { //to save good images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.real_img); //save the image
			hold_img.suppressRegion(region); //a half part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise 
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
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for(int i=0; i<this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}
	
	/**
	 * @return the number of steps carried out by the algorithm to solve the problem
	 */
	public int getCounter() {
		return counter;
	}
	
	/** Computes the ZNCC between both image dataset halves
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}
		
	/**
	 * Greedy algorithm: random instances for each half, the best one is the final solution    
	 * @n_tries number of random tries     
	 */
	 public void splitSubsetsGreedy(int n_tries) {   

         assert n_tries > 0;//assert at least one try //enables you to test your assumptions about your program.
         double hold_zncc;
         this.max_zncc = -1; //a max so we can compare later
         Image half1_img, half2_img; //group1 and group2      

         //greedy loop
         double rand; //random number
         this.counter = 0; //counts the times a picture is added in a group

         for (int i=0; i<n_tries; i++) {

              half1_img = new Image(this.width, this.height); //generate image
              half2_img = new Image(this.width, this.height); //generate image          

              // Loop for generating a random solution	(from 0 to the number weve added as N_IMGS at ImageAveragerBench class. here it is 13)
             //each loop the the for adds randomly the 13 pictures of the dataset array in the two groups 
              for (int j=0; j<this.dataset.length; j++) {

                   rand = Math.random(); //generate random number
                   if (rand < 0.3333) {//group1 pictures
                	   
                	   half1_img.addSignal(this.dataset[j]);  //save the image from dataset in g1
                       this.sol[j] = 1; //add in sol array that this photo is in g1

                   } else if (rand < 0.6667) {//group2 pictures

                	   half2_img.addSignal(this.dataset[j]);  //save the image in g2
                       this.sol[j] = 2; //add in sol array that this photo is in g2

                   }

                   this.counter++; 
              }             

              //update best solution
              hold_zncc = half1_img.zncc(half2_img); // Current ZNCC between halves averages

              if (hold_zncc > this.max_zncc) {

                   this.max_zncc = hold_zncc;

                   // Update resulting halves averages
                   this.half1_img = half1_img.copy();//add in half1 field g1 halves
                   this.half2_img = half2_img.copy();

                   // Update global average image, the sum of the two halves
                   this.avg_img = new Image(this.width, this.height);

                   this.avg_img.addSignal(this.half1_img);
                   this.avg_img.addSignal(this.half2_img);

                   // Update solution array
                   this.bestSol = this.sol.clone(); //we clone in bestSol sol

              }
         }
     }		
	
	/**
	 * Backtracking algorithm 
	 * @max_unbalancing: (pruning condition) determines the maximum difference between 
	 * the number of images on each half set   //diafora eikonwn se ka8e group         
	 */
	public void splitSubsetsBacktracking(int level,int max_unbalancing, int h1Counter, int h2Counter) {
		double hold_zncc;
        this.max_zncc = -1; //a max so we can compare later
        Image half1_img, half2_img; //group1 and group2    
        half1_img = new Image(this.width, this.height); //generate image
        half2_img = new Image(this.width, this.height); //generate image
        
        //we check if the level is equal to the dataset array. its 13 for this ex
        //when we have gone through the whole dataset array and sent all 13 images to the groups we can find optimal result comparing the 2 groups
		if(level == this.dataset.length) {
			
			 //update best solution
            hold_zncc = half1_img.zncc(half2_img); // Current ZNCC between halves averages

            if (hold_zncc > this.max_zncc) {
                 this.max_zncc = hold_zncc;

                 // Update resulting halves averages
                 this.half1_img = half1_img.copy();//add in half1 field g1 halves
                 this.half2_img = half2_img.copy();

                 // Update global average image, the sum of the two halves
                 this.avg_img = new Image(this.width, this.height);

                 this.avg_img.addSignal(this.half1_img);
                 this.avg_img.addSignal(this.half2_img);

                 // Update solution array
                 this.bestSol = this.sol.clone(); //we clone in bestSol sol
            }
			
		}else{//we add the images to the groups depending on the value of max_unbalancing. 
			//if it is 1 and half1 has 1 image less we add an image into half1. 
			//if half2 has one image less we add an image there.
			//if the images are equal in both groups we can added anywhere between the 3 groups(0,1,2)
			
			if(h2Counter - h1Counter <= max_unbalancing || h2Counter == h1Counter) {
				sol[level] = 1;//the image of dataset position level in g1
				h1Counter++;   //images in g1 +1
				half1_img.addSignal(this.dataset[level]); //add image in g1
				splitSubsetsBacktracking(level+1,max_unbalancing, h1Counter, h2Counter);
				h1Counter--;
			}
		
			if(h1Counter - h2Counter <= max_unbalancing || h2Counter == h1Counter) {
				sol[level] = 2;
				h2Counter++;
				half2_img.addSignal(this.dataset[level]);
				splitSubsetsBacktracking(level+1,max_unbalancing, h1Counter, h2Counter);
				h2Counter--;
			}
			
			sol[level] = 0;
			splitSubsetsBacktracking(level+1,max_unbalancing, h1Counter, h2Counter);
		}
		this.counter++;//
	}
		
		
	/**
	 * Backtracking algorithm without balancing. Using a larger than the number of images in the dataset ensures no prunning          
	 */
	public void splitSubsetsBacktracking(int level) {
		// TODO
		double hold_zncc;
        this.max_zncc = -1; //a max so we can compare later
        Image half1_img, half2_img; //group1 and group2    
        half1_img = new Image(this.width, this.height); //generate image
        half2_img = new Image(this.width, this.height); //generate image
        
        //we check if the level is equal to the dataset array. its 13 for this ex
        //when we have gone through the whole dataset array and sent all 13 images to the groups we can find optimal result comparing the 2 groups
		if(level == this.dataset.length) {
			
			 //update best solution
            hold_zncc = half1_img.zncc(half2_img); // Current ZNCC between halves averages

            if (hold_zncc > this.max_zncc) {
                 this.max_zncc = hold_zncc;

                 // Update resulting halves averages
                 this.half1_img = half1_img.copy();//add in half1 field g1 halves
                 this.half2_img = half2_img.copy();

                 // Update global average image, the sum of the two halves
                 this.avg_img = new Image(this.width, this.height);

                 this.avg_img.addSignal(this.half1_img);
                 this.avg_img.addSignal(this.half2_img);

                 // Update solution array
                 this.bestSol = this.sol.clone(); //we clone in bestSol sol
            }
			
		}else{//we add the images to the groups depending on the value of max_unbalancing. 
			//if it is 1 and half1 has 1 image less we add an image into half1. 
			//if half2 has one image less we add an image there.
			//if the images are equal in both groups we can added anywhere between the 3 groups(0,1,2)			
			
				sol[level] = 1;
				half1_img.addSignal(this.dataset[level]);
				splitSubsetsBacktracking(level+1);
				
		
			
				sol[level] = 2;
				half2_img.addSignal(this.dataset[level]);
				splitSubsetsBacktracking(level+1);
				
			
				sol[level] = 0;
				splitSubsetsBacktracking(level+1);
		}
		this.counter++;//
	}

}

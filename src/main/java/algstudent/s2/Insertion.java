package algstudent.s2;

//import algstudent.s2.Vector;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {	
	public Insertion(int nElements) {
		super(nElements);
	}
	
	//BEST CASE: O(n)
	//WORST CASE: O(n^2)
	//MEID CASE: O(n^2) 

	@Override
	public void sort(){
		// TODO: Implement this algorithm
		for(int i = 1; i < this.elements.length; i++) {
			int x = this.elements[i];
			int j = i - 1;
			while (j >= 0 && x < this.elements[j]) {
				this.elements[j + 1] = this.elements[j];
				j=j-1;
			}
			this.elements[j+1]=x; 
		}
	}
 
	
	@Override
	public String getName() {
		return "Insertion";
	} 
} 

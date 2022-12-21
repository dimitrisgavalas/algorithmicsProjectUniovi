package algstudent.s2;

//import algstudent.s2.Vector;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble extends Vector {
	public Bubble(int nElements) {
		super(nElements);
	}
	
	// BEST CASE: O(n^2)
	// WORST CASE: O(n^2)
	// MEID CASE: O(n^2) 
	@Override
	public void sort() {
		// TODO: Implement this algorithm
		int n = this.elements.length;
		for(int i = 0; i <= n - 2; i++) {
			for(int j = n - 1; j > i; j--) {
				if(this.elements[j - 1] > this.elements[j]) {
					// swap elements[j-1] and elements[j]
					interchange(j - 1, j);
				}
			}
		}
	}  
	
	@Override
	public String getName() {
		return "Bubble";
	} 
} 


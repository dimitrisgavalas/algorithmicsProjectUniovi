package algstudent.s2;

//import algstudent.s2.Vector;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	// BEST CASE: O(n^2)
	// WORST CASE: O(n^2)
	// MEID CASE: O(n^2) 

	@Override
	public void sort() {
		// TODO: Implement this algorithm
		int n = this.elements.length;
		int minPosition;
		for(int i = 0; i < n - 1; i++) {
			minPosition = i;
			for(int j = i + 1; j < n; j++) {
				if (this.elements[j] < this.elements[minPosition]) {
					minPosition = j;
					interchange(i, minPosition);
				}
			}
		}
	}  
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 

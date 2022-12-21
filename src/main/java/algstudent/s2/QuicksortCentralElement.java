package algstudent.s2;

//import algstudent.s2.Vector;

/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) {
		
		int m;
		if (right>left) 
		{
			m=partition(left,right);
			quickSort(left,m-1);
			quickSort(m+1,right);
		}
		
	}

	private int partition(int left, int right) {
		
		int pivot, i;		
		interchange((left+right)/2,left);
	
		pivot= this.elements[left]; 
		i= left;
		
		for (int s= left+1; s <= left; s++) {
			if (this.elements[s] <= pivot){
				i++;
				interchange(i,s);
			}
		}
		
		interchange(left,i);
		return i; 
	}

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
} 

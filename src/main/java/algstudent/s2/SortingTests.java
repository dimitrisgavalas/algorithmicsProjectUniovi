package algstudent.s2;

//import algstudent.s2.Bubble;
//import labs.en._20.lab2sorting.Insertion;
//import labs.en._20.lab2sorting.QuicksortCentralElement;
//import labs.en._20.lab2sorting.QuicksortFateful;
//import labs.en._20.lab2sorting.QuicksortMedianOfThree;
//import labs.en._20.lab2sorting.Selection;
//import labs.en._20.lab2sorting.Vector;

public class SortingTests {

	/** This program is used to check that sorting algorithms are working
	 * */
	public static void main(String arg []) {
		int n = 10;  //problem size
		
		testSortingAlgorithm(new Insertion(n));		
		testSortingAlgorithm(new Selection(n));		
		testSortingAlgorithm(new Bubble(n));		
		testSortingAlgorithm(new QuicksortFateful(n));
		testSortingAlgorithm(new QuicksortCentralElement(n));
		testSortingAlgorithm(new QuicksortMedianOfThree(n));
	}
	
	public static void testSortingAlgorithm(Vector v) {
		System.out.println("\n\nSorting test: "+ v.getName());
		
		System.out.println("\nSorting an already-sorted vector");
		v.directlySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		v.sort();
		System.out.println("Sorted vector");
		v.write(System.out);

		System.out.println("\nSorting an inverse vector");
		v.inverselySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		v.sort();
		System.out.println("Sorted vector");
		v.write(System.out);

		System.out.println("\nSorting a random vector");
		v.randomlySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		v.sort();
		System.out.println("Sorted vector");
		v.write(System.out);
	}
	
}

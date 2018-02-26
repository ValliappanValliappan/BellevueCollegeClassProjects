package bogo;
import java.util.Arrays;
import java.util.Iterator;
// Header: Put your name, class,..etc here
//Name:Valliappan Valliappan
//Class:CS 211
//Date:November 3rd,2017
public class BogoSort implements Iterable<BogoSort>,Iterator<BogoSort>{
	public static void main(String[] args) {
		long startingTime=System.currentTimeMillis();//UseD TO GET DURATION OF BOG SORT
		int[] myArray = {42, 13, 1990, 19, 87,72, 8633, 137,8,-2131};
		bogoSort(myArray);
		System.out.println("Time taken:"+(System.currentTimeMillis()-startingTime));//Printing Bogo sort duration
		printArray(myArray);
	}

	// Places the elements of a into sorted order.
	public static void bogoSort(int[] a) {
		// **********Write code here******************
		while(!isSorted(a)){//if not sorted
			shuffle(a);//shuffle and check again
		}
	}

	// Returns true if a's elements are in sorted order.
	public static boolean isSorted(int[] a) {
		// **********Write code here******************
		int[] anotherArray=a.clone();//making a copy of array a
		Arrays.sort(anotherArray);//sorting it correctly
		for(int i=0;i<a.length;i++){//iterating to check if it's sorted just like anotherArray
			if(anotherArray[i]!=a[i]){
				return false;//If an element of an index in array a doesn't match an element of array anotherArray of the same index,return false
			}
		}
		return true;//if it reaches her, it is sorted and will return true
	}

	// Shuffles an array of ints by randomly swapping each
	// element with an element ahead of it in the array.
	public static void shuffle(int[] a) {
		// **********Write code here******************
		int i=0;
		while(i<a.length){//shuffling each and every element in array a by swapping with another element of a random index
			int random=(int)(Math.random()*a.length);//This is the random index mentioned in line 44
			swap(a,random,i);
			i++;
		}
	}

	// Swaps a[i] with a[j].
	public static void swap(int[] a, int i, int j) {
		// Storing a[j] so its not lost and swapping a[i] with a[j]
		int intAtIndexJ=a[j];
		a[j]=a[i];
		a[i]=intAtIndexJ;
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BogoSort next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<BogoSort> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}// end of BogoSort class
package dsa.model;

import dsa.data.Patient;
import java.util.Arrays;

public class Sorts
{
//    private static void swap(int[] A, int i, int j) {
//      int temp = A[i];
//      A[i] = A[j];
//      A[j] = temp;
//    }
    
    private static void swap(int[] A, int i, int j) {
      int temp = A[i];
      A[i] = A[j];
      A[j] = temp;
    }
    
    private static void swap(Patient[] A, int i, int j) {
      Patient temp = A[i];
      A[i] = A[j];
      A[j] = temp;
    }

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A) {
      mergeSortRecurse(A, 0, A.length - 1, 0);
    }//mergeSort()

    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx, int depth) {
      int mid = (leftIdx + rightIdx) / 2;

        if(leftIdx < rightIdx) {
          mergeSortRecurse(A, leftIdx, mid, depth+1);     // # reursive left split
          mergeSortRecurse(A, mid+1, rightIdx, depth+1);  // # recursive right split

          // # after walk down, walk back up with merges
          merge(A, leftIdx, mid, rightIdx, depth);        // # final merge recursivey up
        }
    }// end mergeSortRecurse()

    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx, int depth) {
      // Create slices (copies of the left and right subarrays)
      int n1 = midIdx - leftIdx + 1;  // size of left part
      int n2 = rightIdx - midIdx;     // size of right part

      int[] L = new int[n1];
      int[] R = new int[n2];

      // Copy data into temp arrays
      for (int i = 0; i < n1; i++) {
          L[i] = A[leftIdx + i];
      }
      for (int j = 0; j < n2; j++) {
          R[j] = A[midIdx + 1 + j];
      }

      // Initial indices
      int i = leftIdx;  // index for main array
      int j = 0;        // index for L
      int k = 0;        // index for R

      // Merge L[] and R[] back into A[]
      while (j < n1 && k < n2) {
          if (L[j] < R[k]) {
              A[i] = L[j];
              j++;
          } else {
              A[i] = R[k];
              k++;
          }
          i++;
      }

      // Copy remaining elements of L[], if any
      while (j < n1) {
          A[i] = L[j];
          i++;
          j++;
      }

      // Copy remaining elements of R[], if any
      while (k < n2) {
          A[i] = R[k];
          i++;
          k++;
        }
    } // end merge()

    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(Patient[] A, String type) {
        quickSortRecurse(A, 0, A.length-1, type);
    }//quickSort()
    
    // 
    private static void quickSortRecurse(Patient[] A, int leftIdx, int rightIdx, String type) {
        int pivotIdx = (leftIdx+rightIdx)/2; // default middle index, not used

      // stop when left = right
      if( leftIdx < rightIdx) {
        pivotIdx = (rightIdx + leftIdx) / 2; // use middle for pivot;
        int p = doPartitioning(A, leftIdx, rightIdx, pivotIdx ); // index returned
         quickSortRecurse(A, leftIdx, p - 1, type);  // sort left of pivot
         quickSortRecurse(A, p + 1, rightIdx, type);
      }
    }//quickSortRecurse()

    private static int doPartitioning(Patient[] A, int low, int high, int pivotIdx) {
      Patient pivotVal = A[pivotIdx];
      
      swap(A, pivotIdx, high); // swap pivot to end
      int i = low - 1; // start at index of small

      // traverse low->high moving smaller to left
      // elements from low to i are smaller after every iteration
      for (int j = low; j < high; j++) {
//          if (A[j] < pivotVal) {        // implement compareTo on Patient
          if ( A[j].compareTo(pivotVal) < 0) {        // A is smaller than value
              i++;
              swap(A, i, j);
          }
      }

      swap(A, i + 1, high);  // return pivot to its position
      return i+1;
    } //doPartitioning

    private static int[] get_Array(Integer n) {
      int RANDOM_TIMES = 50;
      int[] R = new int[n];

      // Create initial ascending-order array
      for(int i = 0 ; i < n ; i++)
          R[i] = i+1;

      for(int i = 0 ; i < RANDOM_TIMES*n ; i++) {
          int x = (int)Math.floor(Math.random()*(n-1));
          int y = (int)Math.floor(Math.random()*(n-1));
          swap(R, x, y);
      }
      return R;
    }

    public static void main(String[] args) {
//      int[] A;
      
//     System.out.println( "merge: " );
//     // A = new int[] { 1,2,4,2, 5, 6, 2, 1 };
//     A = Sorts.get_Array(50);
//     System.out.println( Arrays.toString(A) );
//     Sorts.mergeSort( A );
//     System.out.println( Arrays.toString(A) );
      
     Patient[] B = { new Patient("joe"), new Patient("greg"), new Patient("yowie"), new Patient("homer"),
     new Patient("joe"), new Patient("greg"), new Patient("yowie"), new Patient("homer"),
     };
     
      System.out.println( "middle quick: " );
      System.out.println( Arrays.toString(B) );
      Sorts.quickSort(B, "middle");
      System.out.println( Arrays.toString(B) );
      
    }

}//end Sorts calss


public class Selection 
{
    public static void sort(Comparable[] a) 
    {
        int N = a.length; 
        
        // outer loop
        for (int i = 0; i < N; i++) {
            
            // index of inner loop minumum
            int min = i; 

            // inner loop 
            for (int j = i + 1; j < N; j++) {
                if (less(a[j]), a[min]) {
                    min = j;
                }
            exch(a, i, min);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) 
    {
     
    }
}
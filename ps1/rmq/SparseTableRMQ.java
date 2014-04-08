package rmq;

/**
 * An &lt;O(n log n), O(1)&gt; implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 * <p/>
 * You will implement this class for problem 3.ii of Problem Set One.
 */
public class SparseTableRMQ implements RMQ {
    
    int[][] sparseTable;
    int[] kArray;

    /*
        Fills the sparse table. 
    */ 
    private void fillSparseTable(float[] elems) {
        int length_eval = 1; 
        int elem_length = sparseTable.length; 
        int log_length = sparseTable[0].length; 

        for(int i=0; i < sparseTable.length; i ++) {
            elems[i][length_eval] = i; 
        }
    }

    /* kArray[i] =
    // X 0 1 1 2 2 2 2 3 3 3 3 3 3 3 3 4
     */
    private void precomputeKArray(int arraySize) {
        kArray = new int[arraySize+1];
        kArray[1] = 0;
        int count = 0;
        int nextPowerOfTwo = 2;
        for (int i = 2; i < arraySize; i++) {
            if (i == nextPowerOfTwo) {
                nextPowerOfTwo *= 2;
                count++;
            }
            kArray[i] = count;
        }
    }

    private int getHighestKUpToNumber(int number) {
        return kArray[number - 1];
    }

    /**
     * Creates a new SparseTableRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public SparseTableRMQ(float[] elems) {
       int elems_length = elems.length;
       precomputeKArray(elems_length);
       int log_length = getHighestKUpToNumber(elems.length);
       sparseTable = new int[elems_length][log_length];
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        // TODO: Implement this!
        System.out.println(getHighestKUpToNumber(10));
        return -1;
    }
}

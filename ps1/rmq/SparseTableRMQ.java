package rmq;

/**
 * An &lt;O(n log n), O(1)&gt; implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 * <p/>
 * You will implement this class for problem 3.ii of Problem Set One.
 */
public class SparseTableRMQ implements RMQ {
    
    int[][] sparseTable; 

    /*
        Fills the sparse table. 
    */ 
    private filleSparseTable(float[] elems) {
        int length_eval = 1; 
        int elem_length = sparseTable.length; 
        int log_length = sparseTable[0].length; 

        for(int i=0; i < sparseTable.length; i ++) {
            elems[i][length_eval] = i; 
        }
    }

    /**
     * Creates a new SparseTableRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public SparseTableRMQ(float[] elems) {
       int elems_length = elems.length; 
       int log_length = getHighestKUptoNumber(elems.length);  
       sparseTable = new int[elem_length][log_length]; 
       

    }

    private int getHighestKUpToNumber(int number) {
        if (number == 1) return 0;
        int ret = 1;
        while (true) {
            if (Math.pow(2, ret) > number)
                return ret - 1;
            ret++;
        }
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

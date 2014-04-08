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
    private void fillSparseTable(float[] elems) {
        int pow_of_two = 0; 
        int elem_length = sparseTable.length; 
        int log_length = sparseTable[0].length; 

        for(int i=0; i < sparseTable.length; i ++) {
            elems[i][pow_of_two] = i; 
        }
        pow_of_two++; 
        while(pow_of_two < log_length) {
        	int two_power = Math.pow(2, pow_of_two - 1); 
        	for(int i = 0; i < elem_length; i++) {
        		if(i + Math.pow(2, pow_of_two) - 1 >= elem_length) 
        			break; 
        		float comp1 = elems[sparseTable[i][pow_of_two-1]]; 
        		float comp2 = elems[sparseTable[i+two_power][pow_of_two-1]]; 
        		sparseTable[i][pow_of_two] = comp1 > comp2 ? sparseTable[i][pow_of_two-1] : sparseTable[i+two_power][pow_of_two-1]; 
        	}
        	pow_of_two ++; 
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
       sparseTable = new int[elems_length][log_length+1]; 
       fillSparseTable(elems); 
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

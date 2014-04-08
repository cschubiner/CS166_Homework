package rmq;

/**
 * An &lt;O(n), O(log n)&gt; implementation of the RMQ as a hybrid between
 * the sparse table (on top) and no-precomputation structure (on bottom)
 * <p/>
 * You will implement this class for problem 3.iii of Problem Set One.
 */
public class HybridRMQ implements RMQ {
    //block size
    int b;

    SparseTableRMQ topLayerRMQ;
    int[] topLayerBlocks;
    float[] original_array; 

    /**
     * Creates a new HybridRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public HybridRMQ(float[] elems) {
        original_array = elems; 
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */

    private int getBlockStart(int i) {
        int k = i/b; 
        if(i % b == 0) {
            return k; 
        }
        return k + 1; 
    }

    private int getBlockEnd(int j) {
        int k = i/b; 
        if(i % b == b-1) {
            return k; 
        }
        return k - 1; 
    }

    private int linearGetMin(int i, int j) {
        int min_index = i; 
        int min_value = original_array[i]; 

        for(int k = i + 1; k <= j; k ++) {
            if(original_array[k]  < min_value) {
                min_value = original_array[k]; 
                min_index = k; 
            }
        }

        return k; 
    }

    @Override
    public int rmq(int i, int j) {
        int block_start = getBlockStart(i); 
        int block_end = getBlockEnd(j); 
        if(block_start <= block_end) {
            int block_max = topLayerRMQ.rmq(block_start, block_end);
            int linear_end_first = block_start * b - 1; 
            int linear_start_second = (block_end + 1) * b; 

            int top_linear_index; 
            if(linear_end_first <i && linear_start_second > j) {
                return block_max; 
            } else if( linear_end_first < i && linear_start_second <= j ) {
                top_linear_index = linearGetMin(linear_start_second, j); 
            } else if( linear_end_first >= i && linear_start_second > j ) {
                top_linear_index = linearGetMin(i, linear_end_first); 
            } else {
                int first = linearGetMin(i, linear_end_first);
                int second = linearGetMin(linear_start_second, j);
                top_linear_index = original_array[first] < original_array[second] ? first : second;  
            }
            
            return block_max < original_array[top_linear_index] ? block_max : top_linear_index; 

        } else {
            return linearGetMin(i, j); 
        }
    }
}

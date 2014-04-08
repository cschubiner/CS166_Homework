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
    float[] topLayerBlocks;
    float[] original_array;


    private void createTopLayer(float[] elems) {
        float minVal = Float.MAX_VALUE;
        int numBlocks = elems.length / b;
        if (elems.length % b > 0) numBlocks++;
        topLayerBlocks = new float[numBlocks];
        int blockIndex = 0;
        for (int i = 0; i < elems.length; i++) {
            if (i % b == 0 && i > 0) {
                blockIndex++;
                minVal = Float.MAX_VALUE;
            }
            if (elems[i] < minVal) {
                minVal = elems[i];
                topLayerBlocks[blockIndex] = minVal;
            }
        }

        //Now that topLayerBlocks is filled, we'll create the sparse table that sits atop it
        topLayerRMQ = new SparseTableRMQ(topLayerBlocks);
    }

    /**
     * Creates a new HybridRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public HybridRMQ(float[] elems) {
//        elems = new float[]{31,41,59,26,53,58,97,93,23,84,62,64,33,27};
        int elems_length = elems.length;
        if (elems_length <= 1) return;
        original_array = elems;
        b = (int)(Math.log(elems_length)/Math.log(2));
        createTopLayer(elems);
    }



    private int getBlockStart(int i) {
        int k = i/b; 
        if(i % b == 0) {
            return k; 
        }
        return k + 1; 
    }

    private int getBlockEnd(int j) {
        int k = j/b;
        if(j % b == b-1) {
            return k; 
        }
        return k - 1; 
    }

    private int linearGetMin(int i, int j) {
        int min_index = i; 
        float min_value = original_array[i];

        for(int k = i + 1; k <= j; k ++) {
            if(original_array[k] < min_value) {
                min_value = original_array[k]; 
                min_index = k; 
            }
        }

        return min_index;
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        if (i == j) return i;
        int block_start = getBlockStart(i);
        int block_end = getBlockEnd(j); 
        if(block_start <= block_end) {
            int bcm = topLayerRMQ.rmq(block_start, block_end);
            int block_max = linearGetMin(bcm* b, (bcm+1)*b -1);
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
            
            return original_array[block_max] < original_array[top_linear_index] ? block_max : top_linear_index;
        } else {
            return linearGetMin(i, j); 
        }
    }
}

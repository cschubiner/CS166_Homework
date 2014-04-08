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
        elems = new float[]{31,41,59,26,53,58,97,93,23,84,62,64,33,27};
        int elems_length = elems.length;
        b = (int)(Math.log(elems_length)/Math.log(2));
        createTopLayer(elems);
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        // TODO: Implement this!
        return -1;
    }
}

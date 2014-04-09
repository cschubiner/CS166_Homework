package rmq;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Stack;

/**
 * An &lt;O(n), O(1)&gt; implementation of the Fischer-Heun RMQ data structure.
 * <p/>
 * You will implement this class for problem 3.iv of Problem Set One.
 */
public class FischerHeunRMQ implements RMQ {
    //block size
    int b;
    PrecomputedRMQ[] cartesianTreeNumbers;
    SparseTableRMQ topLayerRMQ;
    float[] topLayerBlocks;
    float[] original_array;

    /**
     * Creates a new FischerHeunRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public FischerHeunRMQ(float[] elems) {
        int elems_length = elems.length;
        if (elems_length <= 1) return;
        original_array = elems;
        b = (int) (Math.max(Math.log(elems_length) / (Math.log(2) * 4), 1));
        createTopLayer();
        computeRMQForBlocks();
    }

    // This method is from http://is.gd/ThoqbS
    private static int bitSetToInt(BitSet bitSet) {
        int bitInteger = 0;
        for (int i = 0; i < 32; i++)
            if (bitSet.get(i))
                bitInteger |= (1 << i);
        return bitInteger;
    }

    // Creates the top layer of the hybrid structure. The top layer is a sparse top atop blocks of size b.
    private void createTopLayer() {
        float minVal = Float.MAX_VALUE;
        int numBlocks = original_array.length / b;
        if (original_array.length % b > 0) numBlocks++;
        topLayerBlocks = new float[numBlocks];
        int blockIndex = 0;
        for (int i = 0; i < original_array.length; i++) {
            if (i % b == 0 && i > 0) {
                blockIndex++;
                minVal = Float.MAX_VALUE;
            }
            if (original_array[i] < minVal) {
                minVal = original_array[i];
                topLayerBlocks[blockIndex] = minVal;
            }
        }

        //Now that topLayerBlocks is filled, we'll create the sparse table that sits atop it
        topLayerRMQ = new SparseTableRMQ(topLayerBlocks);
    }

    /* Gets the cartesian tree number for a block */
    int getCartesianTreeNumber(int startIndex, int endIndex) {
        BitSet bs = new BitSet();
        int bsIndex = 0;
        Stack<Float> stack = new Stack<Float>();
        for (int i = startIndex; i < Math.min(endIndex, original_array.length); i++) {
            while (!stack.isEmpty()) {
                if (stack.peek() <= original_array[i])
                    break;
                stack.pop();
                bs.set(bsIndex, false);
                bsIndex++;
            }
            stack.push(original_array[i]);
            bs.set(bsIndex, true);
            bsIndex++;
        }
        return bitSetToInt(bs);
    }

    private void computeRMQForBlock(int i, int j) {
        int cTreeNum = getCartesianTreeNumber(i, j);
        if (cartesianTreeNumbers[cTreeNum] != null)
            return;
        cartesianTreeNumbers[cTreeNum] = new PrecomputedRMQ(Arrays.copyOfRange(original_array, i, j));
    }

    private void computeRMQForBlocks() {
        cartesianTreeNumbers = new PrecomputedRMQ[(int) Math.pow(4, b)];
        for (int i = 0; i < original_array.length; i += b) {
            computeRMQForBlock(i, i + b);
        }
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        if (i == j) return i;

        int startingBlock = i / b;
        int endingBlock = j / b;
        if (startingBlock == endingBlock)
            return getMinimumUsingPrecomputedRMQ(i, j);

        int ret = getMinimumUsingPrecomputedRMQ(i, i - i % b + b - 1);
        int upperMin = getMinimumUsingPrecomputedRMQ(j - j % b, j);
        if (original_array[upperMin] < original_array[ret])
            ret = upperMin;

        if (startingBlock + 1 < endingBlock) {
            int bcm = topLayerRMQ.rmq(getBlockStart(i), getBlockEnd(j));
            int indexOfBCM = getMinimumUsingPrecomputedRMQ(bcm * b, Math.min((bcm + 1) * b - 1, original_array.length - 1));
            if (original_array[indexOfBCM] < original_array[ret])
                ret = indexOfBCM;
        }
        return ret;
    }

    /* Returns the first block that we should use when using the top layer's RMQ structure  */
    private int getBlockStart(int i) {
        int k = i / b;
        if (i % b == 0) {
            return k;
        }
        return k + 1;
    }

    /* Returns the last block that we should use when using the top layer's RMQ structure  */
    private int getBlockEnd(int j) {
        int k = j / b;
        if (j % b == b - 1) {
            return k;
        }
        return k - 1;
    }

    /* Gets the minimum element between i and j using the the precomputed RMQ strategy*/
    private int getMinimumUsingPrecomputedRMQ(int i, int j) {
        if (i == j) return i;
        int startIndexInBlock = i % b;
        if (j < i)
            j = i + b - 1;
        int endIndexInBlock = j % b;
        int blockStartIndex = i - startIndexInBlock;
        int cTreeNum = getCartesianTreeNumber(blockStartIndex, Math.min(blockStartIndex + b, original_array.length));
        return blockStartIndex + cartesianTreeNumbers[cTreeNum].rmq(startIndexInBlock, endIndexInBlock);
    }
}

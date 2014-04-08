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
    CartesianTree[] cartesianTreeNumbers;

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

    SparseTableRMQ topLayerRMQ;
    float[] topLayerBlocks;
    float[] original_array;

    // This method is from http://is.gd/ThoqbS
    private static int bitSetToInt(BitSet bitSet)
    {
        int bitInteger = 0;
        for(int i = 0 ; i < 32; i++)
            if(bitSet.get(i))
                bitInteger |= (1 << i);
        return bitInteger;
    }

    /* Gets the cartesian tree number for a block */
    int getCartesianTreeNumber(float[] block) {
        BitSet bs = new BitSet();
        int bsIndex = 0;
        Stack<Float> stack = new Stack<Float>();
        for (int i = 0; i < block.length; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek() < block[i])
                    break;
                stack.pop();
                bs.set(bsIndex, false);
                bsIndex++;
            }
            stack.push(block[i]);
            bs.set(bsIndex, true);
            bsIndex++;
        }
        // pop remainder of elements off stack
        while (!stack.isEmpty()) {
            stack.pop();
            bs.set(bsIndex, false);
            bsIndex++;
        }
        return bitSetToInt(bs);
    }

    /**
     * Creates a new FischerHeunRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public FischerHeunRMQ(float[] elems) {
//        elems = new float[]{32,45,16,18,9,33};
        int elems_length = elems.length;
        if (elems_length <= 1) return;
        original_array = elems;
        b = (int) (Math.log(elems_length) / Math.log(2));
        cartesianTreeNumbers = new CartesianTree[(int)Math.pow(4, b)];
        createTopLayer(elems);
    }

    private void computeRMQForBlock(float[] block) {
        int cTreeNum = getCartesianTreeNumber(block);
        if (cartesianTreeNumbers[cTreeNum] != null)
            return;

        cartesianTreeNumbers[cTreeNum] = new CartesianTree(block);

    }

    private void computeRMQForBlocks() {
        for (int i = 0; i < original_array.length; i+= b) {
            computeRMQForBlock(Arrays.copyOfRange(original_array, i, i+b));
        }
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
        if (block_start <= block_end) {
            int bcm = topLayerRMQ.rmq(block_start, block_end);
            int block_max = linearGetMin(bcm * b, (bcm + 1) * b - 1);
            int linear_end_first = block_start * b - 1;
            int linear_start_second = (block_end + 1) * b;

            int top_linear_index;
            if (linear_end_first < i && linear_start_second > j) {
                return block_max;
            } else if (linear_end_first < i && linear_start_second <= j) {
                top_linear_index = linearGetMin(linear_start_second, j);
            } else if (linear_end_first >= i && linear_start_second > j) {
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

    private int getBlockStart(int i) {
        int k = i / b;
        if (i % b == 0) {
            return k;
        }
        return k + 1;
    }

    private int getBlockEnd(int j) {
        int k = j / b;
        if (j % b == b - 1) {
            return k;
        }
        return k - 1;
    }

    private int linearGetMin(int i, int j) {
        int min_index = i;
        float min_value = original_array[i];

        for (int k = i + 1; k <= j; k++) {
            if (original_array[k] < min_value) {
                min_value = original_array[k];
                min_index = k;
            }
        }

        return min_index;
    }

    private class CartesianTree {
        private Node root;

        public CartesianTree(float[] block) {
            Stack<Float> stack = new Stack<Float>();
            for (int i = 0; i < block.length; i++) {
                while (!stack.isEmpty()) {
                    if (stack.peek() < block[i])
                        break;
                    stack.pop();
                }
                stack.push(block[i]);
            }
            // pop remainder of elements off stack
            while (!stack.isEmpty()) {
                stack.pop();
            }
        }

        private class Node {
            public float value;
        }
    }
}

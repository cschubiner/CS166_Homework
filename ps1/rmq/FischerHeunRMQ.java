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
        for (int i = 0; i < 32; i++)
            if(bitSet.get(i))
                bitInteger |= (1 << i);
        return bitInteger;
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
//                System.out.print("0");
                bs.set(bsIndex, false);
                bsIndex++;
            }
            stack.push(original_array[i]);
//            System.out.print("1");
            bs.set(bsIndex, true);
            bsIndex++;
        }
        // pop remainder of elements off stack
        while (!stack.isEmpty()) {
            stack.pop();
//            System.out.print("0");
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
//        elems = new float[]{27,18,28,18,28,45,90,45,23,53,60,28,74,71,35};
        int elems_length = elems.length;
        if (elems_length <= 1) return;
        original_array = elems;
        b = (int) (Math.max(Math.log(elems_length) / (Math.log(2)*4),1));
        getCartesianTreeNumber(0,elems.length);
        createTopLayer(elems);
        computeRMQForBlocks();
    }

    private void computeRMQForBlock(int i, int j) {
        int cTreeNum = getCartesianTreeNumber(i, j);
        if (cartesianTreeNumbers[cTreeNum] != null)
            return;
        cartesianTreeNumbers[cTreeNum] = new PrecomputedRMQ(Arrays.copyOfRange(original_array, i, j));
    }

    private void computeRMQForBlocks() {
        cartesianTreeNumbers = new PrecomputedRMQ[(int)Math.pow(4, b)];
        for (int i = 0; i < original_array.length; i+= b) {
            computeRMQForBlock(i, i+b);
        }
    }

    private static int testNum = 0;
    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        if (i == j) return i;
        testNum++;
//        if (testNum >= 6340)
//        System.out.println(testNum);

        int startingBlock = i/b;
        int endingBlock = j/b;
        if (startingBlock == endingBlock)
            return linearGetMin(i, j);

        int lowerMin = linearGetMin(i,i - i%b + b - 1);
        int ret = lowerMin;
        int upperMin = linearGetMin(j-j%b,j);
        if (original_array[upperMin] < original_array[ret])
            ret = upperMin;

        if (startingBlock + 1 < endingBlock) {
            int bcm = topLayerRMQ.rmq(getBlockStart(i), getBlockEnd(j));
            int indexOfBCM = linearGetMin(bcm * b, Math.min((bcm+1) * b - 1, original_array.length - 1));
            if (original_array[indexOfBCM] < original_array[ret])
                ret = indexOfBCM;
        }
        return ret;
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
        if (i == j) return i;
        int startIndexInBlock = i % b;
        int endIndexInBlock = j % b;
        if (j < i) j = i + b - 1;

        int blockStartIndex = i-startIndexInBlock;
        int cTreeNum = getCartesianTreeNumber(blockStartIndex, Math.min(blockStartIndex + b, original_array.length));
//        System.out.println(j-i);
        return blockStartIndex + cartesianTreeNumbers[cTreeNum].rmq(startIndexInBlock, endIndexInBlock);
    }
  }

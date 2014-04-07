package rmq;

/**
 * An &lt;O(n log n), O(1)&gt; implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 * <p/>
 * You will implement this class for problem 3.ii of Problem Set One.
 */
public class SparseTableRMQ implements RMQ {
    /**
     * Creates a new SparseTableRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public SparseTableRMQ(float[] elems) {
        // TODO: Implement this!
    }

    private int getHighestKUpToNumber(int number) {
        if (number == 1) return 0;
        int ret = 1;
        while (true) {
            if (Math.pow(2, ret) > number)
                return (int)Math.pow(2,ret - 1);
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

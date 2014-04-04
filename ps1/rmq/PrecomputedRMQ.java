package rmq;

/**
 * An &lt;O(n<sup>2</sup>), O(1)&gt; implementation of RMQ that precomputes the
 * value of RMQ_A(i, j) for all possible i and j.
 * <p/>
 * You will implement this class for problem 3.i of Problem Set One.
 */
public class PrecomputedRMQ implements RMQ {

    int[][] table;

    private int calculateRMQ(float[] elems, int i, int j) {
        int min = i;
        for (int index = i + 1; index <= j; index++) {
            if (elems[index] < elems[min])
                min = index;
        }
        return min;
    }

    /**
     * Creates a new PrecomputedRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public PrecomputedRMQ(float[] elems) {
        table = new int[elems.length][elems.length];
        for (int i = 0; i < elems.length; i++) {
            for (int j = 0; j < elems.length - i; j++) {
                if (i == 0) {
                    //fill in initial diagonal
                    table[j][j] = j;
                } else {
                    if (elems[table[j][j + i - 1]] < elems[table[j + 1][j + i]]) {
                        table[j][j + i] = table[j][j + i - 1];
                    } else {
                        table[j][j + i] = table[j + 1][j + i];
                    }
                }
            }
        }
    }

    private void print2dArray(int[][] arr) {
        for (int i1 = 0; i1 < arr.length; i1++) {
            for (int j1 = 0; j1 < arr[0].length; j1++)
                System.out.print(arr[i1][j1]);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        return table[i][j];
    }
}

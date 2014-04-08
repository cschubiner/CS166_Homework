package rmq;

/**
 * An &lt;O(n log n), O(1)&gt; implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 * <p/>
 * You will implement this class for problem 3.ii of Problem Set One.
 */
public class SparseTableRMQ implements RMQ {

    int[][] sparseTable;
    int[] kArray;
    int[] kPowArray;
    float[] original_array;

    /*
        Fills the sparse table. 
    */
    private void fillSparseTable(float[] elems) {
        int pow_of_two = 0;
        int elem_length = sparseTable.length;
        int log_length = sparseTable[0].length;

        for (int i = 0; i < sparseTable.length; i++) {
            sparseTable[i][pow_of_two] = i;
        }
        pow_of_two++;
        while (pow_of_two < log_length) {
            int two_power = (int) Math.pow(2, pow_of_two - 1);
            for (int i = 0; i < elem_length; i++) {
                if (i + Math.pow(2, pow_of_two) - 1 >= elem_length)
                    break;
                float comp1 = elems[sparseTable[i][pow_of_two - 1]];
                float comp2 = elems[sparseTable[i + two_power][pow_of_two - 1]];
                sparseTable[i][pow_of_two] = comp1 < comp2 ? sparseTable[i][pow_of_two - 1] : sparseTable[i + two_power][pow_of_two - 1];
            }
            pow_of_two++;
        }
    }

    /* kArray[i] = highest 2^i that is less than or equal to i.
     For example, kArray could be:
     0 0 1 1 2 2 2 2 3 3 3 3 3 3 3 3 4
     kPowArray = 2^kArray[i]
     0 2 2 2 4 4 4 4 8 8 8 8 8 8 8 8 16
     Note: The zeroth element in kArray and kPowArray is irrelevant.
     */
    private void precomputeKArray(int arraySize) {
        kArray = new int[arraySize + 1];
        int count = 0;
        int nextPowerOfTwo = 2;
        for (int i = 2; i <= arraySize; i++) {
            if (i == nextPowerOfTwo) {
                nextPowerOfTwo *= 2;
                count++;
            }
            kArray[i] = count;
        }

        int powCount = 1;
        kPowArray = new int[arraySize + 1];
        for (int i = 1; i <= arraySize; i++){
            powCount *= 2;
            kPowArray[i] = powCount;
        }
    }

    private int getHighestKUpToNumber(int number) {
        return kArray[number];
    }

    private int getNumberToThePowerOfTwo(int number) {
        return kPowArray[number];
    }

    /**
     * Creates a new SparseTableRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public SparseTableRMQ(float[] elems) {
        original_array = elems;
        int elems_length = elems.length;
        if (elems_length == 0) return;
        precomputeKArray(elems_length);
        int log_length = getHighestKUpToNumber(elems.length);
        sparseTable = new int[elems_length][log_length + 1];
        fillSparseTable(elems);
        if (elems_length == 5)
            print2dArray(sparseTable);
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
        if (i == j) return i;
        int sparseTableColumn = getHighestKUpToNumber(j - i + 1);
        int sparseTableRange = getNumberToThePowerOfTwo(sparseTableColumn);

        int second_to_comp = j - sparseTableRange + 1;

        if (i == second_to_comp) {
            return sparseTable[i][sparseTableColumn];
        }

        int index1 = sparseTable[i][sparseTableColumn];
        int index2 = sparseTable[second_to_comp][sparseTableColumn];

        if (original_array[index1] < original_array[index2])
            return index1;
        return index2;
    }
}

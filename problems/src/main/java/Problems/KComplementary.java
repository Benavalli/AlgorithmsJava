package Problems;

import java.util.*;

public class KComplementary {

    /**
     * This method is used to print the K-complementaries in array.
     * This points are stored in Pair object.
     * The complexity is O(N)
     * @param k
     * @param array
     */
    public List<Pair> findKComplementar(int k, int[] array) {
        List<Pair> pairs = new ArrayList<>();
        if(array == null || array.length < 2) {
            System.out.println("no k complementar");
            return pairs;
        }

        Set<Integer> set = getIntegers(array);
        pairs = findPair(k, set);
        return pairs;

    }

    /**
     * This method is used to print the K-complementaries in array.
     * This points are stored in Pair object.
     * The complexity is O(1)
     * @param pairs
     */
    public void printPairs(List<Pair> pairs) {
        for(Pair pair : pairs) {
            System.out.println(pair.toString());
        }
    }

    /**
     * This method is used to find K-complementaries in array.
     * If set contains the diff the other indice will be the pair.
     * The complexity is O(N)
     * @param k
     * @param set
     * @return List<Pair>
     */
    protected List<Pair> findPair(int k, Set<Integer> set) {
        ArrayList<Pair> kPairs = new ArrayList<>();
        for(int i : set) {
            if(i > k) {
                continue;
            }

            int diff = k - i;

            if(set.contains(diff)) {
                kPairs.add(new Pair(i, diff));
            }
        }

        return kPairs;
    }

    /**
     * This method is used to init a hashSet with values in array.
     * This points are stored in Pair object.
     * The complexity is O(N)
     * @param array
     * @return Set<Integer>
     */
    protected Set<Integer> getIntegers(int[] array) {
        final Set<Integer> set = new HashSet<>();
        for(int i : array) {
            set.add(i);
        }
        return set;
    }
}

class Pair {

    private static final String PAIR_MASK = "K = A[i] + A[j] => i = %d , j = %d";

    private int leftIndice;
    private int rightIndice;

    public int getLeftIndice() {
        return leftIndice;
    }

    public int getRightIndice() {
        return rightIndice;
    }



    public Pair(int leftIndice, int rightIndice) {
        this.leftIndice = leftIndice;
        this.rightIndice = rightIndice;
    }

    @Override
    public String toString() {
        return String.format(PAIR_MASK, leftIndice, rightIndice);
    }

    @Override
    public boolean equals(Object obj) {
        Pair other = (Pair) obj;
        return this.getLeftIndice() == other.getLeftIndice()
                && this.getRightIndice() == other.getRightIndice();
    }
}


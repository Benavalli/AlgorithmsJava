package Problems;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class KComplementaryTest {

    KComplementary kComplementary;
    KComplementaryMock kComplementaryMock;

    @Before
    public void setup() {
        kComplementary = new KComplementary();
        kComplementaryMock = new KComplementaryMock();
    }

    @Test
    public void test1Pair() {
        List<Pair> pairs = kComplementary.findKComplementar(1, new int[] {1, 3, 10, -2});
        Pair pairsTobeChecked = new Pair(-2, 3);
        assertTrue(pairs.contains(pairsTobeChecked));
        assertEquals(pairs.size(), 1);
    }

    @Test
    public void test2Pair() {
        List<Pair> pairs = kComplementary.findKComplementar(6, new int[] {1, 5, 9});
        Pair pairsTobeChecked = new Pair(1, 5);
        assertTrue(pairs.contains(pairsTobeChecked));
        assertEquals(pairs.size(), 2);
    }

    @Test
    public void test3Pair() {
        List<Pair> pairs = kComplementary.findKComplementar(6, new int[] {8, 1, -3, 0, 1, 3, -2, 4});
        Pair pairsTobeChecked = new Pair(3, 3);
        assertTrue(pairs.contains(pairsTobeChecked));
        assertEquals(pairs.size(), 2);
    }

    @Test
    public void testInitialError() {
        List<Pair> pairs = kComplementary.findKComplementar(6, new int[] {});
        assertEquals(pairs.size(), 0);
        pairs = kComplementary.findKComplementar(6, new int[] {1});
        assertEquals(pairs.size(), 0);
        pairs = kComplementary.findKComplementar(6, null);
        assertEquals(pairs.size(), 0);
    }

    @Test
    public void allMethodsCalled() {
        kComplementaryMock.findKComplementar(6, new int[] {8, 1, -3, 0, 1, 3, -2, 4});
        assertTrue(kComplementaryMock.findPairCalled);
        assertTrue(kComplementaryMock.getIntegersCalled);
    }

    @Test
    public void MethodsNotCalled() {
        kComplementaryMock.findKComplementar(6, null);
        assertFalse(kComplementaryMock.findPairCalled);
        assertFalse(kComplementaryMock.getIntegersCalled);
    }


    public class KComplementaryMock extends KComplementary {

        public boolean getIntegersCalled = false;
        public boolean findPairCalled = false;

        @Override
        protected Set<Integer> getIntegers(int[] array) {
            getIntegersCalled = true;
            return super.getIntegers(array);
        }

        @Override
        protected List<Pair> findPair(int k, Set<Integer> set) {
            findPairCalled = true;
            return super.findPair(k, set);
        }
    }


}
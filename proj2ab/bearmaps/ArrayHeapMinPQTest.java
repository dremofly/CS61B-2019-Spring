package bearmaps;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.NavigableMap;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1,1);
        minHeap.add(2,2);
        minHeap.add(3,3);
        minHeap.add(4,4);
        minHeap.add(5,2);
        minHeap.add(6,1);
        minHeap.printHeap();
        assertEquals(minHeap.size(), 6);
    }

    @Test
    public void testSmallest() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1,2);
        minHeap.add(2,3);
        minHeap.add(3,1);
        assertEquals((int)minHeap.getSmallest(),3);
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1,2);
        minHeap.add(2,3);
        minHeap.add(3,1);
        assertEquals((boolean)minHeap.contains(4), false);
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1,2);
        minHeap.add(2,3);
        minHeap.add(3,1);
        minHeap.printHeap();
        minHeap.removeSmallest();
        minHeap.printHeap();
        assertEquals(1, (int)minHeap.getSmallest());
    }

    @Test
    public void testChangeePriority() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1,1);
        minHeap.add(2,2);
        minHeap.add(3,3);
        minHeap.add(4,4);
        minHeap.add(5,2);
        minHeap.add(6,1);
        minHeap.printHeap();
        minHeap.changePriority(5, 0);
        minHeap.printHeap();

        assertEquals((int)minHeap.getSmallest(), 5);
    }

    @Test
    public void testRuntime() {
        long b, b2, e, e2;
        b = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1,1);
        minHeap.add(2,2);
        minHeap.add(3,3);
        minHeap.add(4,4);
        minHeap.add(5,2);
        minHeap.add(6,1);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        minHeap.add(7,7);
        //minHeap.printHeap();
        //minHeap.printHeap();
        //for(int j=0; j<10; j++){
        //    minHeap.getSmallest();
        //}
        e = System.currentTimeMillis();

        NaiveMinPQ<Integer> minHeap2 = new NaiveMinPQ<>();
        minHeap2.add(1,1);
        minHeap2.add(2,2);
        minHeap2.add(3,3);
        minHeap2.add(4,4);
        minHeap2.add(5,2);
        minHeap2.add(6,1);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        minHeap2.add(7,7);
        //minHeap.printHeap();
        //for(int j=0; j<10; j++){
        //    minHeap2.getSmallest();
        //}
        e2 = System.currentTimeMillis();
        System.out.println(e-b);
        System.out.println(e2 -e);
        assertEquals((e - b) < (e2 - e), true);
    }
}

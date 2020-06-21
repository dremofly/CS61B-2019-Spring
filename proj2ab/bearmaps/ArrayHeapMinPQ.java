package bearmaps;

//import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(null);
        size = 0;
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority){
        items.add(new PriorityNode(item, priority));
        size++;
        swim(size);
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item){
        for(int i=1; i<=size; i++) {
            if(item == items.get(i).getItem()) {
                return true;
            }
        }

        return false;
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(1).getItem();
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest(){
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        T temp = items.get(1).getItem();
        exch(1, size);
        size = size - 1;
        sink(1);
        //items.remove(size);
        return temp;
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size(){
        return size;
    }

    public void printHeap() {
        for(int i=1; i<=size; i++) {
            System.out.printf("%d ", items.get(i).getItem());
        }
        System.out.printf("\n");
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority){
        if(!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = 0;
        for(int i=1; i<=size; i++) {
            if(items.get(i).getItem() == item) {
                index = i;
                break;
            }
        }
        items.get(index).setPriority(priority);
        swim(index);
        sink(index);
    }

    /**
     * Helper functions
     */

    private boolean isEmpty() {
        return size==0;
    }

    private void swim(int k) {
        while(k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while(2*k <= size) {
            int j = 2*k;
            if(j<size && greater(j, j+1)) j++;
            if(!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return items.get(i).getPriority() > items.get(j).getPriority();
    }

    private void exch(int i, int j) {
        double p = items.get(i).getPriority();
        T e = items.get(i).getItem();
        items.get(i).setPriority(items.get(j).getPriority());
        items.get(i).setItem(items.get(j).getItem());
        items.get(j).setPriority(p);
        items.get(j).setItem(e);
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {return item;}

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        void setItem(T e) {
            this.item = e;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if(other ==null){
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o==null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o ).getItem().equals(getItem());
            }
        }
    }
}

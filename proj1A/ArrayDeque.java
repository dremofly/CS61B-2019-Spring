public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

    /**
     * Constructor: construct a empty array deque
     */
    ArrayDeque(){
        items = (T[]) new Object[8];
        first = 0;  // the position where first inserts
        last = 1;   // the position where lat inserts
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.size];
        first = other.first;
        last = other.last;
        size = other.size;
    }

    public boolean isFull() {
        return size == items.length;
    }

    public boolean isSparse() {
        return items.length >= 16 & size < items.length/4;
    }

    public int circular_plus(int index) {
        return (index + 1) % items.length;
    }

    public int circular_minus(int index) {
        return (index - 1 + items.length) % items.length;
    }

    public void resize(int capacity) {
        T[] newDeque = (T[]) new Object[capacity];
        int oldMinus = circular_plus(first);
        for(int newIndex = 0; newIndex<size; newIndex++){
            newDeque[newIndex] = items[oldMinus];
        }
        items = newDeque;
        first = capacity - 1;
        last = size;
    }

    private void upSize() {
        resize(size*2);
    }

    private void downSize() {
        resize(items.length/2);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size();
    }

    public void printDeque(){
        int begin = circular_plus(first);
        for(int i = 0; i<size; i++){
            System.out.print(items[begin] + " " );
            begin = circular_plus(begin);
        }
        System.out.println();
    }

    public T removeFirst(){
        if(!isEmpty())
        {
            first = circular_plus(first);
            size--;
            if(isSparse()) {
                downSize();
            }
            return items[first];
        } else {
            return null;
        }

    }

    public T removeLast(){
        if(!isEmpty()){
            last = circular_minus(last);
            size--;
            if(isSparse())
                downSize();
            return items[last];
        } else {
            return null;
        }

    }

    public void addFirst(T x) {
        if(isFull())
            upSize();

        items[first] = x;
        first = circular_minus(first);
        size++;
    }

    public void addLast(T x) {
        if(isFull())
            upSize();

        items[last] = x;
        last = circular_plus(last);
        size++;
    }

    public T get(int index) {
        if(index>=size)
            return null;
        int begin = circular_plus(first);
        return items[(begin+index)%items.length];
    }

    public static void main(String[] args){
        ArrayDeque<String> ald = new ArrayDeque();
        ald.addFirst("hello");
        ald.addLast("world");
        ald.printDeque();

        ald.addFirst("Test");
        ald.printDeque();
    }
}

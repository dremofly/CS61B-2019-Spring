public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public T item;
        public Node next;
        public Node prev;

        Node(T _item, Node _next, Node _prev){
            item = _item;
            next = _next;
            prev = _prev;
        }
    }


    LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item){
        sentinel.prev = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        } else
        {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public T removeFirst(){
        Node temp = this.sentinel.next;
        this.sentinel.next = temp.next;
        temp.next.prev = this.sentinel;
        size--;
        return temp.item;
    }

    public T removeLast(){
        Node last = this.sentinel.next;
        while(last.next!=null)
            last = last.next;
        last.prev.next = null;
        size--;
        return last.item;
    }

    public T get(int index){
        Node temp = sentinel;
        for(int i=0; i<index; i++){
            temp = temp.next;
        }
        return temp.item;
    }

    public void printDeque(){
        Node p = sentinel.next;
        for(int i=0; i<size; i++){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
}

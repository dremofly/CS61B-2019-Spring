import java.util.HashSet;
import java.util.Iterator;

import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BSTMap() {

    }

    public BSTMap(K key, V val) {
       put(root, key, val);
    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if(key == null)
            throw new IllegalArgumentException();
        if(n == null)
            return null;
        int cmp = key.compareTo(n.key);
        if(cmp > 0) {
            return get(n.right, key);
        } else if (cmp < 0){
            return get(n.left, key);
        } else {
            return n.val;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        Node x = root;
        if(x == null)
            return 0;
        else
            return x.size;
    }

    private int size(Node x) {
        if(x==null)
            return 0;
        else
            return x.size;
    }

    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node root) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node curr = stack.pop();

            if(curr.right != null) {
                Node temp = curr.right;
                while(temp!=null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if(key==null)
            throw new IllegalArgumentException();
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V value) {
        if(n==null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(n.key);
        if(cmp<0)
            n.left = put(n.left, key, value);
        else if(cmp>0)
            n.right = put(n.right, key, value);
        else
            n.val = value;
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    private Node select(int i){
        if(i<0 || i>=size()){
            throw new IllegalArgumentException();
        }
        return select(root, i);
    }

    private Node select(Node n, int i){
        if(n==null) {
            return null;
        }
        int t = size(n.left);
        if(t>i){
            return select(n.left, i);
        } else if(t<i) {
            return select(n.right, i-t-1);
        } else {
            return n;
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        //throw new UnsupportedOperationException();
        Set<K> keys = new HashSet<>();
        for(int i=0; i<size(); i++){
            select(i);
        }
        return keys;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        for (int i = 0; i < 10; i++) {
            bstMap.put("hi" + i, 1 + i);
        }
//        bstMap.printInOrder();
        Iterator<String> itr = bstMap.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

}

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K,V> {
    private final int INITIAL_CAPABILITY = 16;
    private final double LOAD_FACTOR = 0.75;

    private int size;
    private int threshold;
    Buckets<K,V>[] buckets;

    private class Buckets<K,V> {
        K key;
        V value;
        Buckets<K,V> next;
        int hashCode;

        Buckets(int hashCode, K key, V value, Buckets<K,V> next) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHashCode() {
            return hashCode;
        }

        public Buckets<K,V> getNext() {
            return next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Buckets<K,V> next) {
            this.next = next;
        }
    }

    public MyHashMap() {
        this.size = 0;
        this.threshold = (int)(INITIAL_CAPABILITY * LOAD_FACTOR);
        this.buckets = new Buckets[INITIAL_CAPABILITY];
    }

    public MyHashMap(int initialSize) {
        this.size = 0;
        this.threshold = (int)(initialSize * LOAD_FACTOR);
        this.buckets = new Buckets[initialSize];
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.size = 0;
        this.threshold = (int) (initialSize*loadFactor);
        this.buckets = new Buckets[initialSize];
    }
    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        size = 0;
        buckets = new Buckets[buckets.length];
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        return get(key)==null? false : true;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(key == null) {
            throw new IllegalArgumentException();
        }
        int hashCode = hash(key, buckets.length);
        Buckets<K,V> b = get(hashCode, key);
        return b==null ? null : b.getValue();
    }

    private Buckets get(int hashCode, K key) {
        Buckets b = buckets[hashCode];
        while(b != null) {
            if(b.getHashCode() == hashCode && b.getKey().equals(key)) {
                return b;
            }
            b = b.getNext();
        }
        return null;
    }

    private int hash(K key, int length) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        return (key.hashCode() & 0x7fffffff) % length;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value){
        int hashCode = hash(key, buckets.length);
        Buckets<K,V> b = buckets[hashCode];
        while(b != null) {
            if(b.getKey().equals(key) && b.getHashCode() == hashCode) {
                b.setValue(value);
                return;
            }
            b = b.getNext();
        }
        put(hashCode, key, value);
    }

    private void put(int hashCode, K key, V value) {
        Buckets<K, V> b  = new Buckets<>(hashCode, key, value, buckets[hashCode]);
        buckets[hashCode] = b;
        size += 1;
        if(size > threshold) {
            resize(buckets.length*2);
        }
    }



    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        Set<K> allKeys = new HashSet<>();
        for(int i=0; i<buckets.length; i++){
            Buckets<K,V> b = buckets[i];
            while(b!=null){
                allKeys.add(b.getKey());
                b = b.getNext();
            }
        }
        return allKeys;
    }

    private void resize(int capability) {
        Buckets<K,V>[] newBuckets = new Buckets[capability];
        for(int i=0; i<buckets.length; i++){
            Buckets<K,V> b = buckets[i];
            while(b != null) {
                Buckets<K,V> oldNext = b.getNext();
                int newHashCode = hash(b.getKey(), capability);
                b.setNext(newBuckets[newHashCode]);
                b.setHashCode(newHashCode);
                newBuckets[newHashCode] = b;
                b = oldNext;
            }
        }
        buckets = newBuckets;
        threshold = (int)(capability * LOAD_FACTOR); //TOFIXED: how about there is an input load factor?
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
}
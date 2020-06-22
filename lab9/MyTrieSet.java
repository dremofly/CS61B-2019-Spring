import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 128; // ASCII
    private Node root;
    public MyTrieSet() {
        root = new Node(false, '\0');
    }
    /** Clears all items out of Trie */
    @Override
    public void clear(){
        root = null;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
        if(key == null || key.length()==0 || root==null) {
            return false;
        }

        Node p = root;
        Node next = null;
        for(int i=0; i<key.length(); i++) {
            char c = key.charAt(i);
            next = p.next.get(c);
            if(next == null) {
                return false;
            }
            p = next;
        }
        return p.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key){
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.next.containsKey(c)) {
                curr.next.put(c, new Node(false, c));
            }
            curr = curr.next.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix){
        if(prefix == null || prefix.length()==0 || root==null) {
            return null;
        }
        List<String> res = new ArrayList<>();
        Node curr = root;
        Node next = null;
        for(int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!curr.next.containsKey(c)) {
                return res;
            }
            curr = curr.next.get(c);
        }

        if(curr.isKey == true) {
            res.add(prefix);
        }

        for(Node n : curr.next.values()) {
            if(n != null){
                keysWithPrefix(res, prefix, n);
            }
        }
        return res;
    }

    private void keysWithPrefix(List<String> res, String word, Node currNode) {
        if(currNode.isKey) {
            res.add(word + currNode.ch);
        }

        for(Node n : currNode.next.values()) {
            if(n != null) {
                keysWithPrefix(res, word + currNode.ch, n);
            }
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    }

    private static class Node {
        private char ch;
        private boolean isKey;
        private Map<Character, Node > next;

        private Node(boolean blue, char R) {
            this.ch = R;
            this.isKey = blue;
            this.next = new HashMap<>();
        }
    }
}

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for(int i=0; i<word.length(); i++){
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wd = new ArrayDeque<>();
        wd = this.wordToDeque(word);
        while(wd.size()>1){
            if(wd.removeFirst() != wd.removeLast())
                return false;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wd = new LinkedListDeque<>();
        wd = this.wordToDeque(word);
        while(wd.size()>1) {
            if(cc.equalChars(wd.removeFirst(), wd.removeLast())){
                return false;
            }
        }
        return true;
    }
}
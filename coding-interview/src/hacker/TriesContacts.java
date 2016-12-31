package hacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TriesContacts {
    
    static class TrieEntry {
        
        public static final int COLOR_DEFAULT = 0;
        public static final int COLOR_RED = 1;
        public static final int COLOR_BLUE = 2;
                
        private int color;
        private TrieEntry[] children = new TrieEntry[26];
        private int wordCount;
        
        public TrieEntry() {
            this.color = 0;
            this.wordCount = 0;
        }
        
        private void increaseWordCount() {
            this.wordCount++;
        }
        
        public int getWordCount() {
            return this.wordCount;
        }
        
        public void updateColor(int color) {
            this.increaseWordCount();
            if(color == COLOR_BLUE || color == COLOR_DEFAULT) {
                if(this.color != COLOR_RED) {
                    this.color = color;
                }
            } else {
                this.color = color;
            }
        }
        
        public boolean hasChild(int index) {
            return (children[index] != null);
        }
        
        public TrieEntry getChild(int index) {
            if(children[index] != null) {
                return children[index];
            } else {
                children[index] = new TrieEntry();
                return children[index];
            }
        }
        
        public boolean isRed() {
            return (this.color == COLOR_RED);
        }
        
        public boolean isBlue() {
            return (this.color == COLOR_BLUE);
        }
    }
    
    static class Trie {
        
        private TrieEntry root = new TrieEntry();
        
        public int getIndex(char currChar) {
            return ((int)currChar - 97);
        }
        
        public void add(String word) {
            if(word.length() > 0) {
                char[] charArray = word.toCharArray();
                TrieEntry localRoot = root;
                for(int i = 0; i < charArray.length - 1; i++) {
                    int trieIdx = getIndex(charArray[i]);
                    localRoot = localRoot.getChild(trieIdx);
                    localRoot.updateColor(TrieEntry.COLOR_BLUE);
                }
                int lastIdx = getIndex(charArray[charArray.length - 1]);
                localRoot = localRoot.getChild(lastIdx);
                localRoot.updateColor(TrieEntry.COLOR_RED);            
            }
        }
        
        public int findMatchesCount(String phrase) {
            
            if(phrase.length() > 0) {
                char[] phArray = phrase.toCharArray();
                TrieEntry localRoot = root;
                for(int i = 0; i < phArray.length; i++) {
                    char currChar = phArray[i];
                    if(localRoot.hasChild(getIndex(currChar))) {
                        localRoot = localRoot.getChild(getIndex(currChar));
                    } else {
                        return 0;    
                    } 
                }
                return localRoot.getWordCount();
            }
            
            return 0;      
        }
        
        public List<String> find(String phrase) {
            
            List<String> matches = new ArrayList<String>(); 
            
            if(phrase.length() > 0) {
                
                char[] phArray = phrase.toCharArray();
                StringBuffer currPath = new StringBuffer();
                TrieEntry localRoot = root;
                boolean prematureEnd = false;
                for(int i = 0; i < phArray.length; i++) {
                    char currChar = phArray[i];
                    if(localRoot.hasChild(getIndex(currChar))) {
                        currPath.append(currChar);  
                        localRoot = localRoot.getChild(getIndex(currChar));
                    } else {
                        prematureEnd = true;
                        break;
                    }
                }
                if(!prematureEnd) {
                    if(localRoot.isRed()) {
                        matches.add(currPath.toString());
                    }
                    recBuildMatches(matches, localRoot, currPath.toString());
                }
            }
            
            //for(int i = 0; i < matches.size(); i++) {
            //    System.out.println(matches.get(i));
            //}
            
            return matches;      
            
        }
                
        private void recBuildMatches(List<String> matches, TrieEntry localRoot, String partialPath) {
            for(int i = 0; i < 26; i++) {
                StringBuffer path = new StringBuffer(partialPath);
                if(localRoot.hasChild(i)) {
                    TrieEntry currChild = localRoot.getChild(i);
                    path.append((char)(i + 97));
                    if(currChild.isRed()) {
                        matches.add(path.toString());
                    }
                    recBuildMatches(matches, currChild, path.toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        
        Trie dict = new Trie();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if("add".equals(op)) {
                dict.add(contact);
            } else if("find".equals(op)) {
                //System.out.println(dict.find(contact).size());
                System.out.println(dict.findMatchesCount(contact));
            }
        }
        in.close();
    }
}

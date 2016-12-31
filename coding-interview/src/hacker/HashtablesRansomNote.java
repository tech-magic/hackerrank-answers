package hacker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class HashtablesRansomNote {
	
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;
    
    public HashtablesRansomNote(String magazine, String note) {
        this.magazineMap = this.getWordCount(magazine);
        this.noteMap = this.getWordCount(note);
    }
    
    private Map<String, Integer> getWordCount(String phrase) {
        String[] phraseWords = phrase.split(" ");
        Map<String, Integer> dict = new HashMap<String, Integer>();
        for(int i = 0; i < phraseWords.length; i++) {
            String currWord = phraseWords[i].trim();
            if(currWord.length() > 0) {
                if(!dict.containsKey(currWord)) {
                    dict.put(currWord, 1);
                } else {
                    dict.put(currWord, dict.get(currWord) + 1);                
                }
            }
        }
        return dict;
    }
    
    public boolean solve() {
        boolean canBeSolved = true;
        Iterator<String> wordsIt = this.noteMap.keySet().iterator();
        while(wordsIt.hasNext()) {
            String currWord = wordsIt.next();
            canBeSolved = canBeSolved && 
                this.magazineMap.get(currWord) != null &&
                this.magazineMap.get(currWord) >= this.noteMap.get(currWord);
        }
        return canBeSolved;        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        
        // Eat whitespace to beginning of next line
        scanner.nextLine();
        
        HashtablesRansomNote s = new HashtablesRansomNote(scanner.nextLine(), scanner.nextLine());
        scanner.close();
        
        boolean answer = s.solve();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");
      
    }
}


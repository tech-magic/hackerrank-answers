package hacker;

import java.util.Comparator;

class Player {
	int score;
	String name;
}

//Write your Checker class here
public class SortingPlayerComparator implements Comparator<Player> {
 
 public int compare(Player p1, Player p2) {
     if(p1.score > p2.score) {
         return -1;
     } else if(p1.score < p2.score) {
         return 1;
     } else {
         return p1.name.compareTo(p2.name);
     }
 }
 
}

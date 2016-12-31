package hacker;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class StacksBalancedBrackets {
    
    static Map<String, String> opposites;
    static Set<String> closings;
        
    static {
        opposites = new HashMap<String, String>();
        opposites.put(")", "(");
        opposites.put("]", "[");
        opposites.put("}", "{");
        
        closings = opposites.keySet();
    }
    
    public static boolean isBalanced(String expression) {
        Stack<String> stack = new Stack<String>();
        char[] chars = expression.trim().toCharArray();
        for(int i = 0; i < chars.length; i++) {
            String currOperator = new Character(chars[i]).toString();
            if(closings.contains(currOperator)) {
                if(stack.size() > 0) {
                    String stackTop = stack.peek();
                    if(stackTop.equals(opposites.get(currOperator))) {
                        stack.pop();
                    } else {
                        stack.push(currOperator);                        
                    }
                } else {
                    stack.push(currOperator);
                }
            } else {
                stack.push(currOperator);
            }            
        }
        return stack.isEmpty();
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
        in.close();
    }
}


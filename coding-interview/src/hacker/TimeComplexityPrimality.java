package hacker;

import java.util.Scanner;

public class TimeComplexityPrimality {
    
    static boolean isPrime(int n) {
        if(n <= 1 || n == 4) {
            return false;
        } else {
            for(int i = 2; i < (n / 2); i++) {
                if(n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            if(isPrime(n)) {
                System.out.println("Prime");
            } else {
                System.out.println("Not prime");
            }
        }
    }
}


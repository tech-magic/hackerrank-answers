package hacker;

import java.util.Arrays;
import java.util.Scanner;

class IceCream implements Comparable {
    int flavor;
    int index;

    public IceCream(int flavor, int index) {
        this.flavor = flavor;
        this.index = index;
    }
   
    @Override
    public int compareTo(Object o) {
        return new Integer(this.flavor).compareTo(((IceCream)o).flavor);       
    }

    @Override
    public boolean equals(Object o){
        IceCream other = (IceCream)o;
        return (this.index == other.index) && (this.flavor == other.flavor);
    }
        
}

public class BinarySearchIceCreamParlor {
   
    public static int binarySearch(int first, int last, IceCream[] arr, int search) {
        if(first > last) {
            return -1;
        } else {
            int middle = (first + last) / 2;
            if(arr[middle].flavor == search) {
                return arr[middle].index;
            } else {
                int index1 = binarySearch(first, middle - 1, arr, search);
                int index2 = binarySearch(middle + 1, last, arr, search);
                if(index1 != -1) {
                    return index1; 
                } else if(index2 != -1) {
                    return index2; 
                } else {
                    return -1;
                }
            }
        }
        
    }

    public static void main(String[] args) {
        
        int t;
        int n, m;
 
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        for(int test = 0; test < t; test++) {       
            
            m = in.nextInt();
            n = in.nextInt(); 
            IceCream[] arr = new IceCream[n];
    
            for (int i = 0; i < n; i++)
                arr[i] = new IceCream(in.nextInt(), i + 1);
            
            Arrays.sort(arr);
            for(int i = 0; i < n - 1 ; i++) {
                int search = m - arr[i].flavor;
                if(search >= arr[i].flavor) {
                    int index = binarySearch( i + 1, n - 1, arr, search);
                    if( index != -1 ) {
                        System.out.println( Math.min(arr[i].index, index) + " " + Math.max(arr[i].index, index));
                        break;

                    }
                }
            } 
            
        }
        in.close();
    }
                        
}


package hacker;

import java.util.Scanner;

public class MergeSortSwapCount {
    
    public static long countInversions(int[] arr) {
        
        int[] tmpArr = new int[arr.length];
                
        long inversions = recMergeSort(arr, tmpArr, 0, arr.length - 1);
        
        for(int i = 0; i < arr.length; i++) {
            //System.out.print(arr[i] + " ");
        }
        //System.out.println();        
                
        return inversions;    
    }
    
    static long recMergeSort(int[] arr, int[] tmpArr, int start, int end) {
        
        long inversions = 0;
        
        if(start >= end) {
            return inversions;
        } else {
            int middle = (start + end) / 2;
            inversions += recMergeSort(arr, tmpArr, start, middle);
            inversions += recMergeSort(arr, tmpArr, middle + 1, end);
            inversions += merge(arr, tmpArr, start, middle, middle + 1, end);
            return inversions;
        }        
    }
    
    static long merge(int[] arr, int[] tmpArr, int a1Start, int a1End, int a2Start, int a2End) {
        int blockStart = a1Start;
        int blockEnd = a2End;
        int blockMiddle = a2Start; 
        long inversions = 0;
                
        int i = 0;
        while(a1Start <= a1End && a2Start <= a2End) {
            if(arr[a1Start] <= arr[a2Start]) {
                tmpArr[i] = arr[a1Start];
                a1Start++;
            } else {
                tmpArr[i] = arr[a2Start];
                a2Start++;
                inversions = inversions + (blockMiddle - a1Start);
            }
            i++;
        }
        while(a1Start <= a1End) {
            tmpArr[i] = arr[a1Start];
            a1Start++;
            i++;
        }
        while(a2Start <= a2End) {
            tmpArr[i] = arr[a2Start];
            a2Start++;
            i++;
        }
        
        int k = 0;
        for(int j = blockStart; j <= blockEnd; j++) {
            arr[j] = tmpArr[k];
            k++;
        }
        return inversions;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int arr_i=0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            System.out.println(countInversions(arr));
        }
        in.close();
    }
    
    
}


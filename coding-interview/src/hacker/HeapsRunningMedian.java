package hacker;

import java.util.Scanner;

public class HeapsRunningMedian {
    
    static interface HeapDefinition {
        public boolean condition(int parentValue, int childValue);
    }
    
    static class Heap {
        private int[] a;
        private int maxCapacity;
        private HeapDefinition definition;
        private int size;
                
        public Heap(int maxCapacity, HeapDefinition definition) {
            this.maxCapacity = maxCapacity;
            this.a = new int[this.maxCapacity];
            this.size = 0;
            this.definition = definition;
        }
        
        public void push(int value) {
            size++;
            int currIndex = size - 1;
            int parentIndex = getParentIdx(currIndex);
            a[currIndex] = value; 
            while(currIndex != 0 && 
                  !definition.condition(a[parentIndex], a[currIndex])) {
                int tmp = a[currIndex];
                a[currIndex] = a[parentIndex];
                a[parentIndex] = tmp;
                
                currIndex = parentIndex;
                parentIndex = getParentIdx(currIndex);
            }
        }
        
        public int pop() {
            int topElement = a[0];
            a[0] = a[size - 1];
            size--;
            int currIndex = 0;
            boolean heapFixed = false;
            while(!heapFixed && currIndex < size) {
                int[] childIndexes = getChildIndices(currIndex);
                if((childIndexes[0] < size && !definition.condition(a[currIndex], a[childIndexes[0]])) ||
                  (childIndexes[1] < size && !definition.condition(a[currIndex], a[childIndexes[1]]))) {
                    if(childIndexes[0] < size && childIndexes[1] < size) {
                        if(definition.condition(a[childIndexes[0]], a[childIndexes[1]])) {
                            int tmp = a[currIndex];
                            a[currIndex] = a[childIndexes[0]];
                            a[childIndexes[0]] = tmp;
                            currIndex = childIndexes[0];                        
                        } else {
                            int tmp = a[currIndex];
                            a[currIndex] = a[childIndexes[1]];
                            a[childIndexes[1]] = tmp;
                            currIndex = childIndexes[1];
                        }
                    } else if(childIndexes[0] < size) {
                        int tmp = a[currIndex];
                        a[currIndex] = a[childIndexes[0]];
                        a[childIndexes[0]] = tmp;
                        currIndex = childIndexes[0];                        
                    } else {
                        int tmp = a[currIndex];
                        a[currIndex] = a[childIndexes[1]];
                        a[childIndexes[1]] = tmp;
                        currIndex = childIndexes[1];
                    }
                } else {
                    heapFixed = true;
                }
            }
            return topElement;
        }
        
        private int getParentIdx(int currIndex) {
            return (currIndex - 1) / 2;
        }
        
        private int[] getChildIndices(int currIndex) {
            return new int[] { currIndex * 2 + 1, currIndex * 2 + 2 };
        }
        
        public void print() {
            for(int j = 0; j < size; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public int size() {
            return size;
        }
        
        public int peek() {
            return a[0];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Heap minHeap = new Heap(n, new HeapDefinition() {
            public boolean condition(int parentValue, int childValue) {
                return parentValue < childValue;
            }
        });
        Heap maxHeap = new Heap(n, new HeapDefinition() {
            public boolean condition(int parentValue, int childValue) {
                return parentValue > childValue;
            }
        });
        
        for(int i = 0; i < n; i++) {
            int curr = in.nextInt();
            if(maxHeap.isEmpty()) {
                maxHeap.push(curr);
            } else {
                if(curr < maxHeap.peek()) {
                    maxHeap.push(curr);
                } else {
                    minHeap.push(curr);
                }
            }
            
            if(Math.abs(maxHeap.size() - minHeap.size()) > 1) {
                if(maxHeap.size() > minHeap.size()) {
                    minHeap.push(maxHeap.pop());
                } else {
                    maxHeap.push(minHeap.pop());
                }    
            }
            
            //maxHeap.print();
            //minHeap.print();
            
            System.out.println(median(minHeap, maxHeap));
        }
        in.close();
    }
    
    static double median(Heap minHeap, Heap maxHeap) {
        if(minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if(maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / (double)2; 
        }
    }
}


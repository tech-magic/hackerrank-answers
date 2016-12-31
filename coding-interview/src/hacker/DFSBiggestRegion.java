package hacker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DFSBiggestRegion {
    
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static int getBiggestRegion(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] traversed = new boolean[matrix.length][matrix[0].length];
        int maxRegionSize = -1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] != 0 && !traversed[i][j]) {
                    int regionSize = getRegionSize(i, j, matrix, traversed, rows, cols);
                    if(regionSize > maxRegionSize) {
                        maxRegionSize = regionSize;
                    }
                }
            }
        }
        return maxRegionSize;
    }
    
    
    
    public static int getRegionSize(int starti, int startj, 
                                    int[][] matrix, boolean[][] traversed, 
                                    int rows, int cols) {
        int regionSize = 0;
        
        Stack<Point> dfs = new Stack<Point>();
        
        Point currPoint = new Point(starti, startj);
        dfs.push(currPoint);
        traversed[starti][startj] = true;
        regionSize++;
        
        while (!dfs.isEmpty()) {
            boolean nextNeighbourAvailable = false;
            List<Point> neighBours = getNeighbours(dfs.peek(), rows, cols, matrix);
            Iterator<Point> it = neighBours.iterator();
            while(it.hasNext()) {
                Point next = it.next();
                if(!traversed[next.x][next.y]) {
                    dfs.push(next);
                    traversed[next.x][next.y] = true;
                    regionSize++;
                    nextNeighbourAvailable = true;
                }
            }
            if(!nextNeighbourAvailable) {
                dfs.pop();
            }
        }
        
        return regionSize;
    }
    
    public static List<Point> getNeighbours(Point p, int rows, int cols, int[][] matrix) {
        List<Point> neighBours = new ArrayList<Point>();
        if(p.x - 1 >= 0 && p.y - 1 >= 0 && matrix[p.x - 1][p.y - 1] != 0) {
            neighBours.add(new Point(p.x - 1, p.y - 1));
        }
        if(p.y - 1 >= 0 && matrix[p.x][p.y - 1] != 0) {
            neighBours.add(new Point(p.x, p.y - 1));
        }
        
        if(p.x + 1 < rows && p.y - 1 >= 0 && matrix[p.x + 1][p.y - 1] != 0) {
            neighBours.add(new Point(p.x + 1, p.y - 1));
        }
        if(p.x + 1 < rows && matrix[p.x + 1][p.y] != 0) {
            neighBours.add(new Point(p.x + 1, p.y));
        }
        
        if(p.x + 1 < rows && p.y + 1 < cols && matrix[p.x + 1][p.y + 1] != 0) {
            neighBours.add(new Point(p.x + 1, p.y + 1));
        }
        if(p.y + 1 < cols && matrix[p.x][p.y + 1] != 0) {
            neighBours.add(new Point(p.x, p.y + 1));
        }
        
        if(p.x - 1 >= 0 && p.y + 1 < cols && matrix[p.x - 1][p.y + 1] != 0) {
            neighBours.add(new Point(p.x - 1, p.y + 1));
        }
        if(p.x - 1 >= 0 && matrix[p.x - 1][p.y] != 0) {
            neighBours.add(new Point(p.x - 1, p.y));
        }
        
        return neighBours;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        in.close();
        System.out.println(getBiggestRegion(grid));
    }
}


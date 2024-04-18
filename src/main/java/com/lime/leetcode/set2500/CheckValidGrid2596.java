package com.lime.leetcode.set2500;

/**
 * Created by YuHang on 2023/9/13.
 */
public class CheckValidGrid2596 {

    public boolean checkValidGrid(int[][] grid){
        if(grid[0][0] != 0){
            return false;
        }
        int n = grid.length;
        int[][] step = new int[n * n][2];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                step[grid[i][j]][0] = i;
                step[grid[i][j]][1] = j;
            }
        }
        for (int i = 1; i < n * n; i++){
            int dx = Math.abs(step[i][0] - step[i - 1][0]);
            int dy = Math.abs(step[i][1] - step[i - 1][1]);
            if(dx * dy != 2){
                return false;
            }
        }
        return true;
    }

    public boolean checkValidGrid1(int[][] grid) {
        if(grid[0][0] != 0){
            return false;
        }
        int n = grid.length;
        int step = 0;
        int x = 0, y = 0;
        int[][] move = new int[][]{{1,2},{2,1},{1,-2},{2,-1},{-1,2},{-2,1},{-1,-2},{-2,-1}};
        while (step < n * n - 1){
            boolean hasNext = false;
            int nx = x;
            int ny = y;
            for (int i = 0; i < move.length; i++){
                nx = x + move[i][0];
                ny = y + move[i][1];
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == step + 1){
                    hasNext = true;
                    break;
                }
            }
            if(hasNext){
               step++;
               x = nx;
               y = ny;
            }else {
                break;
            }
        }
        return (step == (n * n - 1))? true: false;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}};
        CheckValidGrid2596 checkValidGrid2596 = new CheckValidGrid2596();
        System.out.println(checkValidGrid2596.checkValidGrid(grid));
    }
}

package com.lime.leetcode.set600;

import java.util.*;

/**
 * Created by YuHang on 2023/5/8.
 */
public class MaxAreaOfIsland695 {

    public int maxAreaOfIsland3(int[][] grid){
        int max = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                int cur = 0;
                Queue<Integer> dequeX = new LinkedList<>();
                Queue<Integer> dequeY = new LinkedList<>();
                dequeX.offer(i);
                dequeY.offer(j);
                while (!dequeX.isEmpty()){
                    int x = dequeX.poll();
                    int y = dequeY.poll();
                    if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1){
                        continue;
                    }
                    cur++;
                    grid[x][y] = 0;
                    int[] dx = {0, 0, 1, -1};
                    int[] dy = {1, -1, 0, 0};
                    for(int k = 0; k < 4; k++){
                        dequeX.offer(x + dx[k]);
                        dequeY.offer(y + dy[k]);
                    }
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    public int maxAreaOfIsland2(int[][] grid){
        int max = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                int cur = 0;
                Deque<Integer> dequeX = new LinkedList<>();
                Deque<Integer> dequeY = new LinkedList<>();
                dequeX.push(i);
                dequeY.push(j);
                while (!dequeX.isEmpty()){
                    int x = dequeX.pop();
                    int y = dequeY.pop();
                    if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1){
                        continue;
                    }
                    cur++;
                    grid[x][y] = 0;
                    int[] dx = {0, 0, 1, -1};
                    int[] dy = {1, -1, 0, 0};
                    for(int k = 0; k < 4; k++){
                        dequeX.push(x + dx[k]);
                        dequeY.push(y + dy[k]);
                    }
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    public int maxAreaOfIsland1(int[][] grid){
        int max = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int x, int y){
        if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1){
            return 0;
        }
        //以(x,y)为中心，把陆地集中于ans，为了避免重复计算，将(x,y)设置为水域，整块陆地被分为5部分
        grid[x][y] = 0;
        //陆地中心部分：数量1
        int ans = 1;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for(int k = 0; k < 4; k++){
            //陆地四周部分，深度优先搜索
            ans += dfs(grid, x + dx[k], y + dy[k]);
        }
        return ans;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] area = new int[m + 1][n + 1];
        Set<Integer>[][] island = new Set[m + 1][n + 1];
        int max = 0;
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    //不是陆地
                    area[i + 1][j + 1] = 0;
                    island[i + 1][j + 1] = new HashSet<>();
                }else {
                    //是陆地
                    Set<Integer> areaSet = new HashSet<>();
                    areaSet.add(i* m + j);
                    if(area[i][j + 1] != 0){
                        areaSet.addAll(island[i][j + 1]);
                    }
                    if(area[i + 1][j] != 0){
                        areaSet.addAll(island[i + 1][j]);
                    }
                    island[i + 1][j + 1] = areaSet;
                    area[i + 1][j + 1] = areaSet.size();
                    if(areaSet.size() > max){
                        max = areaSet.size();
                    }
                    for(int k = j - 1; k >= 0; k--){
                        if(grid[i][k] != 0){
                            area[i + 1][k + 1] = area[i + 1][j + 1];
                            island[i + 1][k + 1] = island[i + 1][j + 1];
                        }else {
                            break;
                        }
                    }
                    for(int k = j; k < n; k++){
                        if(area[i][k + 1] != 0){
                            area[i][k + 1] = area[i + 1][j + 1];
                            island[i][k + 1] = island[i + 1][j + 1];
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1},{1,0,1},{1,0,0}};
        MaxAreaOfIsland695 maxAreaOfIsland695 = new MaxAreaOfIsland695();
        System.out.println(maxAreaOfIsland695.maxAreaOfIsland2(grid));
    }
}

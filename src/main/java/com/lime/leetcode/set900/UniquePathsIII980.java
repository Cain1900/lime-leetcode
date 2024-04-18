package com.lime.leetcode.set900;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by YuHang on 2023/8/4.
 */
public class UniquePathsIII980 {

    public int uniquePathsIIIWithDPBit(int[][] grid){
        int m = grid.length, n = grid[0].length;
        //搜索出起始方格、结束方格
        int sx = -1, sy = -1, dx= -1, dy = -1;
        int bitNum = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    sx = i;
                    sy = j;
                }else if(grid[i][j] == 0 || grid[i][j] == 2){
                    bitNum |= 1 << (i * n + j);
                }
            }
        }
        return dpWithBit(grid, sx, sy, bitNum);
    }

    public int dpWithBit(int[][] grid, int x, int y, int bitNum){
        if(grid[x][y] == 2){
            //n == 0，剩余需要走的步数为0，所有空方格都走完了
            return bitNum == 0? 1: 0;
        }
        int res = 0;
        //移动方向
        int[][] move = new int[][]{{1, -1, 0, 0}, {0, 0, 1, -1}};
        int r = grid.length, c = grid[0].length;
        for (int i= 0; i < 4; i++){
            int nx = x + move[0][i];
            int ny = y + move[1][i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c || grid[nx][ny] == -1 || grid[nx][ny] == 1){
                //坐标越界，或者遇到无法跨越的障碍
                continue;
            }
            int index = 1 << (nx * c + ny);
            if((bitNum & index) == 0){
                //已经访问过
                continue;
            }
            //未访问过
            res += dpWithBit(grid, nx, ny, bitNum - index);
        }
        return res;
    }


    public int uniquePathsIIIWithDPNum(int[][] grid){
        int m = grid.length, n = grid[0].length;
        //搜索出起始方格、结束方格
        int sx = -1, sy = -1, dx= -1, dy = -1;
        int num = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    sx = i;
                    sy = j;
                    num++;
                }else if(grid[i][j] == 0){
                    num++;
                }
            }
        }
        return dpWithNum(grid, sx, sy, num);
    }

    //n表示剩余需要走的步数
    public int dpWithNum(int[][] grid, int x, int y, int n){
        if(grid[x][y] == 2){
            //n == 0，剩余需要走的步数为0，所有空方格都走完了
            return n == 0? 1: 0;
        }
        int t = grid[x][y];
        //设置成无法跨越的障碍
        grid[x][y] = -1;
        int res = 0;
        //移动方向
        int[][] move = new int[][]{{1, -1, 0, 0}, {0, 0, 1, -1}};
        int r = grid.length, c = grid[0].length;
        for (int i= 0; i < 4; i++){
            int nx = x + move[0][i];
            int ny = y + move[1][i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c || grid[nx][ny] == -1){
                //坐标越界，或者遇到无法跨越的障碍
                continue;
            }
            res += dpWithNum(grid, nx, ny, n - 1);
        }
        //恢复成空方格
        grid[x][y] = t;
        return res;
    }



    public int uniquePathsIIIWithQueue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        //搜索出起始方格、结束方格
        int sx = -1, sy = -1, dx= -1, dy = -1;
        int line = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    sx = i;
                    sy = j;
                }else if(grid[i][j] == 2){
                    dx = i;
                    dy = j;
                }else if(grid[i][j] == 0){
                    line += 1 << (i * n + j);
                }
            }
        }
        //移动方向
        int[][] move = new int[][]{{1, -1, 0, 0}, {0, 0, 1, -1}};
        //记录
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            for (int i= 0; i < 4; i++){
                int nx = poll[0] + move[0][i];
                int ny = poll[1] + move[1][i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == -1){
                    //坐标越界，或者遇到无法跨越的障碍
                    continue;
                }
                if(nx == dx && ny == dy){
                    //结束方格
                    //判断是否所有无障碍方格都通过
                    if(poll[2] == line){
                        count++;
                    }
                    continue;
                }
                int index = 1 << (nx * n + ny);
                if((poll[2] & index) != 0){
                    //已经访问过
                    continue;
                }
                //不是结束方格,继续往四个方向移动
                queue.offer(new int[]{nx, ny, poll[2] + index});
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        UniquePathsIII980 uniquePathsIII980 = new UniquePathsIII980();
        System.out.println(uniquePathsIII980.uniquePathsIIIWithDPBit(grid));
    }
}

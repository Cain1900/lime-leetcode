package com.lime.leetcode.set2600;

import java.util.*;

/**
 * Created by YuHang on 2023/9/21.
 */
public class CollectTheCoins2603 {

    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Integer>[] grid = new ArrayList[n];
        Arrays.fill(grid, new ArrayList<>());
        int[] degree = new int[n];
        for (int[] e: edges){
            //记录节点关系
            grid[e[0]].add(e[1]);
            grid[e[1]].add(e[0]);
            //记录节点度
            degree[e[0]]++;
            degree[e[1]]++;
        }
        //一个
        int leftEdges = n - 1;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if(degree[i] == 1 && coins[i] == 0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            leftEdges--;
            for (int e: grid[poll]){
                degree[e]--;
                if(degree[e] == 1 && coins[e] == 0){
                    queue.offer(e);
                }
            }
        }

        for (int i = 0; i < n; i++){
            if(degree[i] == 1 && coins[i] == 1){
                queue.offer(i);
            }
        }

        leftEdges -= queue.size();
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            for (int e: grid[poll]){
                degree[e]--;
                if (degree[e] == 1) { // y 现在是叶子了
                    leftEdges--; // 删除 y（到其父节点的边）
                }
            }
        }
        return Math.max(leftEdges * 2, 0);
    }
}

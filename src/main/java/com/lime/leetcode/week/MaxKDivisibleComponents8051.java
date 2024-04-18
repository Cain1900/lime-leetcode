package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/9/30.
 */
public class MaxKDivisibleComponents8051 {

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        if(edges.length == 0){
            return values[0] % k == 0? 1: 0;
        }
        List<Integer>[] grid = new List[n];
        Arrays.setAll(grid, e -> new ArrayList<>());
        int[] degree = new int[n];
        for (int[] e: edges){
            int i = e[0], j = e[1];
            grid[i].add(j);
            grid[j].add(i);
            degree[i]++;
            degree[j]++;
        }
        int ans = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if(degree[i] == 1){
                //叶子节点
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            //叶子节点减掉一条边，度数为0
            degree[poll]--;
            boolean isCut = false;
            if(values[poll] % k == 0){
                //与poll相连的边可以去掉
                ans++;
                isCut = true;
            }
            for (int e: grid[poll]){
                //去掉以poll为起点，e为终点的边
                degree[e]--;
                if(degree[e] < 0) {
                    //剩余边小于0，表示已经分析过的叶子节点
                    continue;
                }
                if(degree[e] == 1){
                    queue.offer(e);
                }
                if(!isCut){
                    values[e] += values[poll];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};
        int[] values = new int[]{3,0,6,1,5,2,1};
        int k = 3;
        MaxKDivisibleComponents8051 maxKDivisibleComponents8051 = new MaxKDivisibleComponents8051();
        System.out.println(maxKDivisibleComponents8051.maxKDivisibleComponents(n, edges, values, k));
    }
}

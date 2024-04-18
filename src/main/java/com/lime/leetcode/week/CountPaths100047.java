package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/9/24.
 */
public class CountPaths100047 {


    public long countPaths(int n, int[][] edges) {
        List<Integer>[] grid = new List[n + 1];
        Arrays.setAll(grid, e -> new ArrayList<>());
        int[] degree = new int[n + 1];
        //以i位起点的质数个数
        long[][] primeNum = new long[n + 1][2];
        boolean[] np = new boolean[n + 1]; // 质数=false 非质数=true
        np[1] = true;
        for (int i = 2; i * i <= n; i++) {
            if (!np[i]) {
                for (int j = i * i; j <= n; j += i) {
                    np[j] = true;
                }
            }
        }
        for (int[] e: edges){
            int i = e[0], j = e[1];
            grid[i].add(j);
            grid[j].add(i);
            degree[i]++;
            degree[j]++;
        }
        long ans = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++){
            if(degree[i] == 1){
                //叶子节点
                queue.offer(i);
            }
            if(np[i]){
                //非质数
                primeNum[i][0] = 1;
            }else {
                //质数
                primeNum[i][1] = 1;
            }
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            //叶子节点减掉一条边，度数为0
            degree[poll]--;
            for (int e: grid[poll]){
                //去掉以poll为起点，e为终点的边
                degree[e]--;
                if(degree[e] < 0) {
                    //剩余边小于0，表示已经分析过的叶子节点
                    continue;
                }
                //统计以poll为起点，e为终点的边构建的有效记录
                ans += (primeNum[poll][1] * primeNum[e][0] + primeNum[poll][0] * primeNum[e][1]);
                //将叶子节点合并
                if(np[e]){
                    //非质数
                    primeNum[e][0] += primeNum[poll][0];
                    primeNum[e][1] += primeNum[poll][1];
                }else {
                    //质数
                    //符合条件的数为包含0个质数的对数
                    primeNum[e][1] += primeNum[poll][0];
                }
                if(degree[e] == 1){
                    queue.offer(e);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = new int[][]{{1,2},{1,3},{2,4},{2,5}};
        //int[][] edges = new int[][]{{1,3},{4,3},{2,3},{2,5}};
        CountPaths100047 countPaths100047 = new CountPaths100047();
        System.out.println(countPaths100047.countPaths(n, edges));
    }
}

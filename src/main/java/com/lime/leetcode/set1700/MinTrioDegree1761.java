package com.lime.leetcode.set1700;

/**
 * Created by YuHang on 2023/8/31.
 */
public class MinTrioDegree1761 {

    public int minTrioDegree(int n, int[][] edges) {
        int[][] g = new int[n][n];
        int[] degrees = new int[n];
        for (int i = 0; i < edges.length; i++){
            int x = edges[i][0] - 1, y = edges[i][1] - 1;
            g[x][y] = 1;
            g[y][x] = 1;
            degrees[x]++;
            degrees[y]++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 2; i < n; i++){
            for (int j = 1; j < i; j++){
                if(g[i][j] != 1){
                    continue;
                }
                for (int k = 0; k < j; k++){
                    if(g[i][k] == 1 && g[j][k] == 1){
                        ans = Math.min(ans, degrees[i] + degrees[j] + degrees[k] - 6);
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE? -1: ans;
    }
}

package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/8/6.
 */
public class MaximumSafenessFactor6951 {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size(), m = grid.get(0).size();
        int[][] g = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = grid.get(i).get(j);
                if (g[i][j] == 1) {
                    g[i][j] = -1;
                }
            }
        }

        boolean[][] st = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == -1) {
                    q.offer(new int[]{i, j});
                    st[i][j] = true;
                }
            }
        }

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                for (int i = 0; i < 4; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= n || b < 0 || b >= m || st[a][b]) continue;
                    st[a][b] = true;
                    q.offer(new int[]{a, b});
                    g[a][b] = g[x][y] + 1;
                }
            }
        }

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], -0x3f3f3f3f);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[2] - o1[2]));
        pq.offer(new int[]{0, 0, g[0][0]});
        dist[0][0] = g[0][0];
        for (int i = 0; i < n; i++) Arrays.fill(st[i], false);

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int x = t[0], y = t[1], w = t[2];
            if (st[x][y]) continue;
            st[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= m || st[a][b]) continue;
                if (dist[a][b] < Math.min(w, g[a][b])) {
                    dist[a][b] = Math.min(w, g[a][b]);
                    pq.offer(new int[]{a, b, dist[a][b]});
                }
            }
        }

        return dist[n - 1][m - 1] + 1;
    }


    public int maximumSafenessFactor1(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] length = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                length[i][j] = Integer.MAX_VALUE;
            }
        }
        Set<int[]> thief = new HashSet<>();
        for (int i = 0; i < n; i++){
            List<Integer> item = grid.get(i);
            for (int j = 0; j < n; j++){
                if(item.get(j) == 1){
                    thief.add(new int[]{i, j});
                    dp(length, i, j, n, i, j);
                }
            }
        }
        return moveDp(length, 0, 0, n, length[0][0]);
    }

    private int moveDp(int[][] length, int x, int y, int n, int value) {
        if(value == 0 || x < 0 || x >= n || y < 0 || y >= n || length[x][y] == -1){
            return 0;
        }
        value = Math.min(value, length[x][y]);
        if(x == n - 1 && y == n - 1){
            return value;
        }
        int t = length[x][y];
        //设置成无法跨越的障碍
        length[x][y] = -1;
        //移动方向
        int[][] move = new int[][]{{1, -1, 0, 0}, {0, 0, 1, -1}};
        int tempMax = 0;
        for (int i= 0; i < 4; i++){
            int nx = x + move[0][i];
            int ny = y + move[1][i];
            tempMax = Math.max(tempMax, moveDp(length, nx, ny, n, value));
        }
        //恢复成空方格
        length[x][y] = t;
        return Math.min(value, tempMax);
    }

    public void dp(int[][] length, int x, int y, int n, int i, int j){
        int l = Math.abs(x - i) + Math.abs(y - j);
        if(x < 0 || x >= n || y < 0 || y >= n || length[x][y] <= l){
            return;
        }
        length[x][y] = l;
        dp(length, x + 1, y, n, i, j);
        dp(length, x - 1, y, n, i, j);
        dp(length, x, y + 1, n, i, j);
        dp(length, x, y - 1, n, i, j);
    }

    public static void main(String[] args) {
        List<Integer> item1 = new ArrayList<>();
        item1.add(0);
        item1.add(0);
        item1.add(0);
        item1.add(1);
        List<Integer> item2 = new ArrayList<>();
        item2.add(0);
        item2.add(0);
        item2.add(0);
        item2.add(0);
        List<Integer> item3 = new ArrayList<>();
        item3.add(0);
        item3.add(0);
        item3.add(0);
        item3.add(0);
        List<Integer> item4 = new ArrayList<>();
        item4.add(1);
        item4.add(0);
        item4.add(0);
        item4.add(0);
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(item1);
        grid.add(item2);
        grid.add(item3);
        grid.add(item4);
        MaximumSafenessFactor6951 maximumSafenessFactor6951 = new MaximumSafenessFactor6951();
        System.out.println(maximumSafenessFactor6951.maximumSafenessFactor(grid));
    }
}

package com.lime.leetcode.set1200;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by YuHang on 2023/5/8.
 */
public class MinPushBox1263 {

    /*
    箱子当前位置(i,j)
    移动操作指令: 上(0,1), 下(0,-1), 左(-1,0), 右(1,0)
    推动前人位置: 推上(i,j-1), 推下(i,j+1), 推左(i+1,j), 推右(i-1,j)
    推动后箱子位置: 上(i,j+1), 下(i,j-1), 左(i-1,j), 右(i+1,j)
    推动后人位置: (i,j)
    下一步条件:
        1、目标位置为空: 上(0,1), 下(0,-1), 左(-1,0), 右(1,0)
        2、推动指令人位置为空: 推上(0,-1), 推下(0,1), 推左(1,0), 推右(-1,0)
        3、人能到达推动位置

    位置分类：
    1、'#': 障碍物，集合表示。其他: 非障碍物，集合表示。
    2、'T': 箱子目标位置，数组表示。
    3、'B': 箱子所处位置，动态变化，数组表示。
    4、'S': 人所处位置，动态变化，数组表示。
    * */

    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }else if(grid[i][j] == 'B'){
                    bx = i;
                    by = j;
                }
            }
        }
        int[] dx = {0 ,0, 1, -1};
        int[] dy = {1 ,-1, 0, 0};
        //S移动的位置
        Queue<int[]> queueS = new LinkedList<>();
        int[][] dp = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++){
            for (int j = 0; j < m * n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int dps = sx * n + sy, dpb = bx * n + by;
        queueS.offer(new int[]{dps, dpb});
        dp[dps][dpb] = 0;
        int ans = Integer.MAX_VALUE;
        while (!queueS.isEmpty()){
            int[] poll = queueS.poll();
            int dps1 = poll[0], dpb1 = poll[1];
            int sx1 = dps1 / n, sy1 = dps1 % n, bx1 = dpb1 / n, by1 = dpb1 % n;
            if(grid[bx1][by1] == 'T'){
                //单队列是深度搜索逻辑，移动次数会错序
                ans = Math.min(ans, dp[dps1][dpb1]);
                continue;
            }
            //人移动
            for(int k = 0; k < 4; k++){
                int sx2 = sx1 + dx[k], sy2 = sy1 + dy[k];
                //判断人移动后位置是否有效
                if(sx2 < 0 || sx2 >= m || sy2 < 0 || sy2 >= n || grid[sx2][sy2] == '#'){
                    continue;
                }
                int dps2 = sx2 * n + sy2;
                if(sx2 == bx1 && sy2 == by1){
                    //人移动到箱子位置，触发一次推箱子
                    int bx2 = bx1 + dx[k], by2 = by1 + dy[k];
                    //判断箱子移动后位置是否有效
                    if(bx2 < 0 || bx2 >= m || by2 < 0 || by2 >= n || grid[bx2][by2] == '#'){
                        continue;
                    }
                    int dpb2 = bx2 * n + by2;
                    if(dp[dps2][dpb2] <= dp[dps1][dpb1] + 1){
                        //已经访问过了
                        continue;
                    }
                    dp[dps2][dpb2] = dp[dps1][dpb1] + 1;
                    queueS.offer(new int[]{dps2, dpb2});
                }else {
                    if(dp[dps2][dpb1] <= dp[dps1][dpb1]){
                        //已经访问过了
                        continue;
                    }
                    dp[dps2][dpb1] = dp[dps1][dpb1];
                    queueS.offer(new int[]{dps2, dpb1});
                }
            }
        }
        return ans == Integer.MAX_VALUE? -1: ans;
    }

    public int minPushBox2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }else if(grid[i][j] == 'B'){
                    bx = i;
                    by = j;
                }
            }
        }
        int[] dx = {0 ,0, 1, -1};
        int[] dy = {1 ,-1, 0, 0};
        //S移动的位置
        Queue<int[]> queueS = new LinkedList<>();
        int[][] isVisit = new int[m * n][m * n];
        queueS.offer(new int[]{sx * n + sy, bx * n + by});
        int ans = 0;
        while (!queueS.isEmpty()){
            Queue<int[]> queueB = new LinkedList<>();
            while (!queueS.isEmpty()){
                int[] poll = queueS.poll();
                int sx1 = poll[0] / n, sy1 = poll[0] % n;
                int bx1 = poll[1] / n, by1 = poll[1] % n;
                if(grid[bx1][by1] == 'T'){
                    return ans;
                }
                //人移动
                for(int k = 0; k < 4; k++){
                    int sx2 = sx1 + dx[k], sy2 = sy1 + dy[k];
                    //判断人移动后位置是否有效
                    if(sx2 < 0 || sx2 >= m || sy2 < 0 || sy2 >= n || grid[sx2][sy2] == '#'){
                        continue;
                    }
                    if(sx2 == bx1 && sy2 == by1){
                        //人移动到箱子位置，触发一次推箱子
                        int bx2 = bx1 + dx[k], by2 = by1 + dy[k];
                        //判断箱子移动后位置是否有效
                        if(bx2 < 0 || bx2 >= m || by2 < 0 || by2 >= n || grid[bx2][by2] == '#'){
                            continue;
                        }
                        if(isVisit[sx2 * n + sy2][bx2 * n + by2] != 0){
                            //已经访问过了
                            continue;
                        }
                        isVisit[sx2 * n + sy2][bx2 * n + by2] = 1;
                        queueB.offer(new int[]{sx2 * n + sy2, bx2 * n + by2});
                    }else {
                        if(isVisit[sx2 * n + sy2][poll[1]] != 0){
                            //已经访问过了
                            continue;
                        }
                        isVisit[sx2 * n + sy2][poll[1]] = 1;
                        queueS.offer(new int[]{sx2 * n + sy2, poll[1]});
                    }
                }

            }
            queueS = queueB;
            ans++;
        }
        return -1;
    }

    public int minPushBox1(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 玩家、箱子的初始位置
        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 'S'){
                    //人所处位置
                    sx = i;
                    sy = j;
                }else if(grid[i][j] == 'B'){
                    //箱子所处位置
                    bx = i;
                    by = j;
                }
            }
        }
        //位置移动坐标变化数组
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        //人任意移动时会产生不同的人和箱子位置组合
        //将位置组合放入栈，遍历栈中元素，依次对人进行四个方向的移动，产生新的人和箱子位置组合
        //其中人移动后到达箱子位置时，会产生推箱子动作，箱子也会移动； 其他情况箱子位置不动，只有人移动
        Deque<int[]> dequeS = new LinkedList<>();
        //记录人和箱子位置组合是否被访问过
        int[][] dp = new int[m * n][m * n];
        //将人和箱子初始位置放入栈中
        dequeS.push(new int[]{sx * n + sy, bx * n + by});
        //记录箱子被移动次数
        int ans = 0;
        while (!dequeS.isEmpty()){
            //人移动后到达箱子位置时，会产生推箱子动作，箱子也会移动； 其他情况箱子位置不动，只有人移动
            //记录箱子能被推动时的初始状态
            Deque<int[]> dequeB = new LinkedList<>();
            while (!dequeS.isEmpty()){
                //取出人和箱子位置组合
                int[] pop = dequeS.pop();
                //记录人和箱子位置组合被访问过
                dp[pop[0]][pop[1]] = 1;
                //人的位置
                int sx1 = pop[0] / n, sy1 = pop[0] % n;
                //箱子位置
                int bx1 = pop[1] / n, by1 = pop[1] % n;
                //判断箱子位置是否是目标位置
                if(grid[bx1][by1] == 'T'){
                    return ans;
                }
                //移动人的位置
                for (int i = 0; i < 4; i++){
                    //移动后人的位置
                    int sx2 = sx1 + dx[i], sy2 = sy1 + dy[i];
                    if ( sx2 < 0 || sx2 >= m || sy2 < 0 || sy2 >= n || grid[sx2][sy2] == '#') {
                        //人新的位置是否合法
                        continue;
                    }
                    //判断人新的位置是否是箱子位置
                    if(sx2 == bx1 && sy2 == by1){
                        //产生推箱子动作，箱子和人一样做同样的运动
                        int bx2 = bx1 + dx[i], by2 = by1 + dy[i];
                        if ( bx2 < 0 || bx2 >= m || by2 < 0 || by2 >= n || grid[bx2][by2] == '#') {
                            //箱子新的位置是否合法
                            continue;
                        }
                        //判断人和箱子位置组合是否被访问过
                        if(dp[sx2 * n + sy2][bx2 * n + by2] != 0){
                            //被访问过，跳过
                            continue;
                        }
                        //设置位置组合被访问过
                        dp[sx2 * n + sy2][bx2 * n + by2] = 1;
                        //触发了一次推箱子动作，放入箱子被移动后的栈中
                        dequeB.offer(new int[]{sx2 * n + sy2, bx2 * n + by2});
                    }else {
                        //判断人和箱子位置组合是否被访问过
                        if(dp[sx2 * n + sy2][bx1 * n + by1] != 0){
                            //被访问过，跳过
                            continue;
                        }
                        //设置位置组合被访问过
                        dp[sx2 * n + sy2][bx1 * n + by1] = 1;
                        //没有触发箱子推动，只是人位置发生变化，放入人移动位置栈中
                        dequeS.offer(new int[]{sx2 * n + sy2, bx1 * n + by1});
                    }
                }
            }
            //基于新的箱子位置，再次移动人的位置
            dequeS = dequeB;
            //推动次数加1
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        char[][] grid = {{'#','#','#','#','#','#'},{'#','T','#','#','#','#'},{'#','.','.','B','.','#'},{'#','.','#','#','.','#'},{'#','.','.','.','S','#'},{'#','#','#','#','#','#'}};
        MinPushBox1263 minPushBox1263 = new MinPushBox1263();
        System.out.println(minPushBox1263.minPushBox(grid));
    }

}

package com.lime.leetcode.set;

import java.util.*;

/**
 * Created by YuHang on 2023/6/27.
 */
public class PondSizes16_19 {

    public int[] pondSizes(int[][] land) {
        List<Integer> lakeList = new ArrayList<>();
        for (int i = 0; i < land.length; i++){
            for(int j = 0; j < land[0].length; j++){
                if(land[i][j] == 0){
                    //如果是水域，进行面积计算
                    lakeList.add(bfs(land, i, j));
                }
            }
        }
        int[] ans = new int[lakeList.size()];
        for (int i = 0; i < lakeList.size(); i++){
            ans[i] = lakeList.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }

    //广度搜索
    private Integer bfs(int[][] land, int i, int j) {
        int sum = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        land[i][j] = -1;
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            sum++;
            for (int dx = -1; dx < 2; dx++){
                for(int dy = -1; dy < 2; dy++){
                    if(dx ==  0 && dy == 0 ){
                        continue;
                    }else if(poll[0] + dx < 0 || poll[0] + dx >= land.length || poll[1] + dy < 0 || poll[1] + dy >= land[0].length || land[poll[0] + dx][poll[1] + dy] != 0){
                        continue;
                    }
                    land[poll[0] + dx][poll[1] + dy] = -1;
                    queue.offer(new int[]{poll[0] + dx, poll[1] + dy});
                }
            }
        }
        return sum;
    }

    //深度搜索
    private Integer dfs(int[][] land, int i, int j) {
        if(i < 0 || j < 0 || i >= land.length || j >= land[0].length || land[i][j] != 0){
            //越界或者不是水域时，返回面积大小0
            return 0;
        }
        //已经计算过的水域标记成非水域，避免重复遍历
        land[i][j] = -1;
        //当前水域面积为1，同时遍历周围8个方向是否是水域
        return 1 + dfs(land, i + 1, j)
                + dfs(land, i - 1, j)
                + dfs(land, i, j + 1)
                + dfs(land, i, j - 1)
                + dfs(land, i + 1, j + 1)
                + dfs(land, i + 1, j - 1)
                + dfs(land, i - 1, j + 1)
                + dfs(land, i - 1, j - 1) ;
    }


}

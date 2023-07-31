package com.lime.leetcode.set1000;

import java.util.*;

/**
 * Created by YuHang on 2023/4/15.
 */
public class GardenNoAdj1042 {



    public int[] gardenNoAdj(int n, int[][] paths) {
        int[] ans = new int[n];
        int[] value = new int[n];
        //记录每个花园的相邻位置
        List<Integer>[] nextPath = new List[n];
        for (int i = 0; i < n; i++){
            //初始化相邻位置
            nextPath[i] = new ArrayList<>();
        }
        for (int i = 0; i < paths.length; i++){
            //只记录编号小于自己的花园于自己相邻
            if(paths[i][0] > paths[i][1]){
                nextPath[paths[i][0] - 1].add(paths[i][1]);
            }else {
                nextPath[paths[i][1] - 1].add(paths[i][0]);
            }
        }
        //遍历每一个花园
        for(int i = 0; i < n; i++){
            ans[i] = countValue(ans, nextPath[i]);
        }
        return ans;
    }

    public int countValue(int[] ans, List<Integer> gardenList){
        if(gardenList.isEmpty()){
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < gardenList.size(); i++){
            sum |= (1 << ans[gardenList.get(i) - 1] -1);
        }
        sum ^= 15;
        int count = 1;
        while (sum%2 == 0){
            sum /= 2;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] paths = {{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}};
        GardenNoAdj1042 gardenNoAdj1042 = new GardenNoAdj1042();
        System.out.println(gardenNoAdj1042.gardenNoAdj(n, paths));
    }
}

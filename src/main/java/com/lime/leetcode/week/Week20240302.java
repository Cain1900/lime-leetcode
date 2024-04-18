package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2024/3/2.
 */
public class Week20240302 {

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        int[][] speed = new int[n][n];
        int[] degree = new int[n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        return new int[0];
    }

    public int[] resultArray(int[] nums) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(nums[0]);
        list2.add(nums[1]);
        int pre1 = nums[0];
        int pre2 = nums[1];
        for (int i = 2; i < nums.length; i++){
            if(pre1 > pre2){
                pre1 = nums[i];
                list1.add(pre1);
            }else {
                pre2 = nums[i];
                list2.add(pre2);
            }
        }
        list1.addAll(list2);
        return list1.stream().mapToInt(i -> i).toArray();
    }
}

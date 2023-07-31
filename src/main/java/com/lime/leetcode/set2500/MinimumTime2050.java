package com.lime.leetcode.set2500;

import java.util.*;

/**
 * Created by YuHang on 2023/7/28.
 */
public class MinimumTime2050 {

    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Set<Integer>> preList = new ArrayList<>();
        for (int i = 0;  i < n; i++){
            preList.add(new HashSet<>());
        }
        for (int i = 0; i < relations.length; i++){
            preList.get(relations[i][1] - 1).add(relations[i][0] - 1);
        }
        int max = 0;
        Map<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < n; i++){
            max = Math.max(max, dp(i, time, sum, preList));
        }
        return max;
    }

    private int dp(int i, int[] time, Map<Integer, Integer> sum, List<Set<Integer>> preList) {
        if(sum.containsKey(i)){
           return sum.get(i);
        }
        int cur = 0;
        for (Integer preIndex: preList.get(i)){
            cur = Math.max(cur, dp(preIndex, time, sum, preList));
        }
        cur += time[i];
        sum.put(i, cur);
        return cur;
    }

    public int minimumTime1(int n, int[][] relations, int[] time) {
        int[] pre = new int[n];
        int[] needTime = new int[n];
        List<Set<Integer>> preList = new ArrayList<>();
        List<Set<Integer>> nextList = new ArrayList<>();
        for (int i = 0;  i < n; i++){
            preList.add(new HashSet<>());
            nextList.add(new HashSet<>());
            needTime[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < relations.length; i++){
            nextList.get(relations[i][0] - 1).add(relations[i][1] - 1);
            pre[relations[i][1] - 1]++;
            preList.get(relations[i][1] - 1).add(relations[i][0] - 1);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if(pre[i] == 0){
                queue.offer(i);
            }
        }
        int max = 0;
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            Integer pollMax = 0;
            for (Integer preIndex: preList.get(poll)){
                pollMax = Math.max(pollMax, needTime[preIndex]);
            }
            needTime[poll] = time[poll] + pollMax;
            max = Integer.max(max, needTime[poll]);
            for (Integer nextIndex: nextList.get(poll)){
                pre[nextIndex]--;
                if(pre[nextIndex] == 0){
                    queue.offer(nextIndex);
                }
            }
        }
        return max;
    }
}

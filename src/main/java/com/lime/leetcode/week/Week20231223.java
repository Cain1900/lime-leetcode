package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/12/23.
 */

public class Week20231223 {

    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int left = n - 1;
        for(int i = 1; i < n; i++) {
            if (nums[i - 1] >= nums[i]) {
                left = i;
                break;
            }
        }
        int right = 0;
        for(int i = n - 2; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) {
                right = i;
                break;
            }
        }
        long count = 0;
        int pre = 0;
        int nextIndex = Math.max(1, right + 1);
        for(int i = 0; i <= left; i++){
            if(nextIndex == n){
                count++;
                continue;
            }
            while (i >= nextIndex || (nextIndex < n && pre >= nums[nextIndex])){
                nextIndex++;
            }
            //nextIndex == n - 1 || pre < nums[nextIndex]
            count += (n - nextIndex + 1);
            pre = nums[i];
        }
        return count;
    }


    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        int last = nums.length - 2;
        for (int i = 0; i <= last; i++){
            sum += nums[i];
        }
        while (sum <= nums[last + 1]){
            if(last == 1){
                return -1;
            }
            sum -= nums[last];
            last--;
        }
        return sum + nums[last + 1];
    }

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        Map<Integer, List<Integer>> next = new HashMap<>();
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            if(next.containsKey(edge[0])){
                next.get(edge[0]).add(edge[1]);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(edge[1]);
                next.put(edge[0], list);
            }
            if(next.containsKey(edge[1])){
                next.get(edge[1]).add(edge[0]);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(edge[0]);
                next.put(edge[1], list);
            }
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        long[] ans = new long[n];
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer>[] subSet = new HashSet[n];
        int[][] costList = new int[n][6];
        for (int i = 0; i < n; i++){
            if(cost[i] < 0){
                costList[i][0] = cost[i];
                costList[i][1] = Integer.MIN_VALUE;
                costList[i][2] = Integer.MIN_VALUE;
                costList[i][3] = cost[i];
            }else {
                costList[i][0] = cost[i];
                costList[i][1] = Integer.MIN_VALUE;
                costList[i][2] = Integer.MIN_VALUE;
            }
            costList[i][5] = 1;
            if(i != 0 && degree[i] == 1){
                queue.offer(i);
                ans[i] = 1;
                degree[i]--;
            }
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            List<Integer> list = next.get(poll);
            for (Integer item: list){
                if(degree[item] > 0){
                    //子树合并
                    mergeCostList(costList, item, poll);
                }
                degree[item]--;
                if((item != 0 && degree[item] == 1) || (item == 0 && degree[item] == 0)){
                    if( costList[item][5] < 3){
                        ans[item] = 1;
                    }else {
                        if(costList[item][2] == Integer.MIN_VALUE || costList[item][1] == Integer.MIN_VALUE){
                            long multiple3 = ((long) costList[item][0] * costList[item][3]) * costList[item][4];
                            ans[item] = Math.max(0, multiple3);
                        }else {
                            long multiple1 = ((long) costList[item][0] * costList[item][1]) * costList[item][2];
                            long multiple3 = ((long) costList[item][0] * costList[item][3]) * costList[item][4];
                            ans[item] = Math.max(0, Math.max(multiple1, multiple3));
                        }
                    }
                    if(item != 0){
                        queue.offer(item);
                    }
                }
            }
        }
        return ans;
    }

    private void mergeCostList(int[][] costList, int i, int j) {
        costList[i][5] += costList[j][5];
        if(costList[j][0] > costList[i][0]){
            costList[i][2] =  costList[i][1];
            costList[i][1] =  costList[i][0];
            costList[i][0] =  costList[j][0];
         }else if(costList[j][0] > costList[i][1]){
            costList[i][2] =  costList[i][1];
            costList[i][1] =  costList[j][0];
        }else if(costList[j][0] > costList[i][2]){
            costList[i][2] =  costList[j][0];
        }
        if(costList[j][1] > costList[i][0]){
            costList[i][2] =  costList[i][1];
            costList[i][1] =  costList[i][0];
            costList[i][0] =  costList[j][1];
        }else if(costList[j][1] > costList[i][1]){
            costList[i][2] =  costList[i][1];
            costList[i][1] =  costList[j][1];
        }else if(costList[j][1] > costList[i][2]){
            costList[i][2] =  costList[j][1];
        }
        if(costList[j][2] > costList[i][0]){
            costList[i][2] =  costList[i][1];
            costList[i][1] =  costList[i][0];
            costList[i][0] =  costList[j][2];
        }else if(costList[j][2] > costList[i][1]){
            costList[i][2] =  costList[i][1];
            costList[i][1] =  costList[j][2];
        }else if(costList[j][2] > costList[i][2]){
            costList[i][2] =  costList[j][2];
        }
        if(costList[j][3] < 0){
            if(costList[j][3] < costList[i][3]){
                costList[i][4] =  costList[i][3];
                costList[i][3] =  costList[j][3];
            }else if(costList[j][3] < costList[i][4]){
                costList[i][4] =  costList[j][3];
            }
        }
        if(costList[j][4] < 0){
            if(costList[j][3] < costList[i][3]){
                costList[i][4] =  costList[i][3];
                costList[i][3] =  costList[j][4];
            }else if(costList[j][4] < costList[i][4]){
                costList[i][4] =  costList[j][4];
            }
        }
    }


    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2}};
        int[] cost = {1,2,-2};
        Week20231223 week20231223 = new Week20231223();
        System.out.println(week20231223.placedCoins(edges, cost));

    }


}

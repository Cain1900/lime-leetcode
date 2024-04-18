package com.lime.leetcode.week;

import java.sql.Array;
import java.util.*;

/**
 * Created by YuHang on 2024/4/13.
 */
public class Week20240413 {

    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int count = 1;
        int start = points[0][0];
        int end = start + w;
        for (int i = 1; i < points.length; i++){
            if(points[i][0] > end){
                count++;
                start = points[i][0];
                end = start + w;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Week20240413 week20240413 = new Week20240413();
        int n = 8;
        int[][] edges = {{4,0,5},{3,7,3},{0,2,3},{3,5,3},{7,0,1},{2,0,3},{7,7,10}};
        int[] disappear = {15,8,4,3,9,18,9,13};
        int[] nums = {1,4,3,3,2};
        System.out.println(week20240413.numberOfSubarrays(nums));
    }

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++){
            if(map.containsKey(edges[i][0])){
                map.get(edges[i][0]).add(edges[i]);
            }else {
                List<int[]> list = new ArrayList<>();
                list.add(edges[i]);
                map.put(edges[i][0], list);
            }
            if(map.containsKey(edges[i][1])){
                map.get(edges[i][1]).add(new int[]{edges[i][1], edges[i][0], edges[i][2]});
            }else {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{edges[i][1], edges[i][0], edges[i][2]});
                map.put(edges[i][1], list);
            }
        }
        int[] min = new int[n];
        for (int i = 1; i < n; i++){
            min[i] = Integer.MAX_VALUE;
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        List<int[]> ints = map.get(0);
        if(ints != null){
            for (int[] item: ints){
                //1: 编号； 2: 时间
                queue.offer(new int[]{item[1], item[2]});
            }
        }
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            if(poll[1] < disappear[poll[0]] && poll[1] < min[poll[0]]){
                //未消失, 最短路径更新了
                min[poll[0]] = poll[1];
                List<int[]> next = map.get(poll[0]);
                if(next != null){
                    for (int[] item: next){
                        //1: 编号； 2: 时间
                        queue.offer(new int[]{item[1], poll[1] + item[2]});
                    }
                }
            }
        }
        for (int i = 1; i < n; i++){
            if(min[i] == Integer.MAX_VALUE){
                min[i] = -1;
            }
        }
        return min;
    }

    public long numberOfSubarrays_01(int[] nums) {
        long sum = 0;
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            set.add(nums[i]);
            if(map.containsKey(nums[i])){
                map.get(nums[i]).add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        int[] max = new int[n];
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b -a);
        for (Integer item: set){
            queue.offer(item);
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            List<Integer> indexList = map.get(poll);
            for(int i = 0; i < indexList.size(); i++){
                for(int j = 0; j <= i; j++){
                    sum += check(max, indexList.get(i), indexList.get(j));
                }
            }
            for(int i = 0; i < indexList.size(); i++){
                max[indexList.get(i)] = 1;
            }
        }
        return sum;
    }

    public int check(int[] max, int start, int end){
        if(start > end){
            int temp = end;
            end = start;
            start = temp;
        }
        for (int i = start; i <= end; i++){
            if(max[i] == 1){
                return 0;
            }
        }
        return 1;
    }

    public long numberOfSubarrays(int[] nums) {
        return dp(nums, 0, nums.length - 1);
    }

    public long dp(int[] nums, int start, int end){
        if(start > end){
            return 0;
        }else if(start == end){
            return 1;
        }
        int max = 0;
        for(int i = start; i <= end; i++){
            if(max < nums[i]){
                max = nums[i];
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = start; i <= end; i++){
            if(max == nums[i]){
                list.add(i);
            }
        }
        if(list.size() == 1){
            return 1 + dp(nums, start, list.get(0) - 1) + dp(nums, list.get(0) + 1, end);
        }
        long n = list.size();
        long sum = (n + 1) * n / 2 + dp(nums, start, list.get(0) - 1) + dp(nums, list.get(list.size() - 1) + 1, end);
        for (int i = 1; i < list.size(); i++){
            sum += dp(nums, list.get(i - 1) + 1, list.get(i) - 1);
        }
        return sum;
    }

}

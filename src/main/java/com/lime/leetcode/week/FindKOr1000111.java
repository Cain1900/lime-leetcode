package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/10/29.
 */
public class FindKOr1000111 {

    public int findKOr(int[] nums, int k) {
        boolean isLoop = true;
        int ans = 0;
        int j = 0;
        while (isLoop){
            isLoop = false;
            int count = 0;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] % 2 == 1){
                    count++;
                }
                nums[i] /= 2;
                if(nums[i] > 0){
                    isLoop = true;
                }
            }
            if(count >= k){
                ans += (1 << j);
            }
            j++;
        }
        return ans;
    }

    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        long count1 = 0, count2 = 0;
        for(int i = 0; i < nums1.length; i++){
            sum1 += (nums1[i] == 0? 1:nums1[i]);
            count1 += (nums1[i] == 0? 1:0);
        }
        for(int i = 0; i < nums2.length; i++){
            sum2 += (nums2[i] == 0? 1:nums2[i]);
            count2 += (nums2[i] == 0? 1:0);
        }
        if(sum1 > sum2 && count2 == 0){
            return -1;
        }else if(sum1 < sum2 && count1 == 0){
            return -1;
        }else {
            return Math.max(sum1, sum2);
        }
    }


    public static void main(String[] args) {
        testFindKOr();
    }

    private static void testFindKOr() {
        int[] nums = {14,7,12,9,8,9,1,15};
        int k = 4;
        FindKOr1000111 findKOr1000111 = new FindKOr1000111();
        System.out.println(findKOr1000111.findKOr(nums, k));
    }

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        int[][] method1 = new int[n][2];
        int[][] method2 = new int[n][2];
        int[] degree = new int[n];
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        for(int[] item: edges){
            degree[item[0]]++;
            degree[item[1]]++;
            if(preMap.containsKey(item[0])){
                preMap.get(item[0]).add(item[1]);
            }else {
                Set<Integer> set = new HashSet<>();
                set.add(item[1]);
                preMap.put(item[0], set);
            }
            if(preMap.containsKey(item[1])){
                preMap.get(item[1]).add(item[0]);
            }else {
                Set<Integer> set = new HashSet<>();
                set.add(item[0]);
                preMap.put(item[1], set);
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if(degree[i] == 1){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            Integer next = new ArrayList<>(preMap.get(poll)).get(0);
            //第一种方法
            method1[next][0] = method1[poll][0] + coins[poll];
            method1[next][1] = method1[poll][1] - k;
            //第二种方法
            method2[next][0] = method1[poll][0] + coins[poll];
            method2[next][1] = method1[poll][1] - k;
        }
        return 1;
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for(int i = citations.length - 1; i >= 0 ; i--){
            if(citations[i] < citations.length - i){
                return citations.length - i;
            }
        }
        return citations.length;
    }
}

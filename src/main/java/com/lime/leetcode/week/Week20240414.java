package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2024/4/14.
 */
public class Week20240414 {

    public String findLatestTime(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        if(s.charAt(0) == '?'){
            if(s.charAt(1) == '?' || s.charAt(1) == '0' || s.charAt(1) == '1'){
                stringBuilder.append("1");
            }else {
                stringBuilder.append("0");
            }
        }else {
            stringBuilder.append(s.charAt(0));
        }
        if(s.charAt(1) == '?'){
            if(stringBuilder.charAt(0) == '1'){
                stringBuilder.append("1");
            }else {
                stringBuilder.append("9");
            }
        }else {
            stringBuilder.append(s.charAt(1));
        }
        stringBuilder.append(":");
        if(s.charAt(3) == '?'){
            stringBuilder.append("5");
        }else {
            stringBuilder.append(s.charAt(3));
        }
        if(s.charAt(4) == '?'){
            stringBuilder.append("9");
        }else {
            stringBuilder.append(s.charAt(4));
        }
        return stringBuilder.toString();
    }

    public int maximumPrimeDifference(int[] nums) {
        int left = -1;
        for(int i = 0; i < nums.length; i++){
            if(isPrime(nums[i])){
                left = i;
                break;
            }

        }
        int right = left;
        for(int i = nums.length - 1; i > left; i--){
            if(isPrime(nums[i])){
                right = i;
                break;
            }
        }
        return right - left;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public long findKthSmallest01(int[] coins, int k) {
        Queue<long[]> queue = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[0] * o1[1] <= o2[0] * o2[1]){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < coins.length; i++){
            queue.offer(new long[]{coins[i], 1});
        }
        int count  = 0;
        long pre = 0;
        while (count < k){
            long[] poll = queue.poll();
            if(pre != poll[0] * poll[1]){
                count++;
                pre = poll[0] * poll[1];
            }
            queue.offer(new long[]{poll[0], poll[1] + 1});
        }
        return pre;
    }

    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        int n = coins.length;
        long left = coins[0];
        long right = (long) coins[0] * k;
        while (left < right){

        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < coins.length; i++){
            for (int j = 0; )
        }
    }

    



    public static void main(String[] args) {
        Week20240414 week20240414 = new Week20240414();
        int[] nums = {4,8,2,8};
        int[] coins = {3, 6, 9};
        int k = 3;
        week20240414.findKthSmallest(coins, k);
        Queue<Long> queue = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return 0;
            }
        });
        queue.offer((long) 7f);
        queue.offer((long) 6f);
        queue.offer((long) 8f);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}

package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2024/3/24.
 */
public class Week20240324 {


    public int maximumLengthSubstring(String s) {
        int[] firstIndex = new int[26];
        int[] secondIndex = new int[26];
        Arrays.fill(firstIndex, -1);
        Arrays.fill(secondIndex, -1);
        int max = 0;
        int pre = -1;
        for (int i = 0; i < s.length(); i++){
            int cur = s.charAt(i) - 'a';
            if(firstIndex[cur] == -1){
                max = Math.max(max, i - pre);
                //记录第一次出现位置
                firstIndex[cur] = i;
            }else if(secondIndex[cur] == -1){
                //第二次出现了
                max = Math.max(max, i - pre);
                //记录第二次出现位置
                secondIndex[cur] = i;
            }else {
                max = Math.max(max, i - Math.max(firstIndex[cur], pre));
                pre = Math.max(firstIndex[cur], pre);
                firstIndex[cur] = secondIndex[cur];
                secondIndex[cur] = i;
            }
        }
        return max;
    }

    public int minOperations(int k) {
        int sqrt = (int) Math.sqrt(k);
        if(sqrt * sqrt >= k){
            return sqrt * 2 - 2;
        }else {
            return sqrt * 2 - 1;
        }
    }

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Long, Long> map = new HashMap<>();
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> (b[1] > a[1] ? 1: (b[1] == a[1]? 0: -1)));
        int n = nums.length;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++){
            long[] item = new long[]{nums[i], map.getOrDefault(Long.valueOf(nums[i]), 0l) + freq[i]};
            map.put(item[0], item[1]);
            queue.offer(item);
            while (!queue.isEmpty() && map.get(queue.peek()[0]) != queue.peek()[1]){
                queue.poll();
            }
            while(!queue.isEmpty() && queue.peek()[1] == 0){
                queue.poll();
            }
            ans[i] = queue.isEmpty()? 0: queue.peek()[1];
        }
        return ans;
    }

    public static void main(String[] args) {
        Week20240324 week20240324 = new Week20240324();
        System.out.println(week20240324.mostFrequentIDs(new int[]{5, 5, 3}, new int[]{2, -2, 1}));

    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        for (int i = 0; i < coins.length; i++){
            count += (amount / coins[i]);
            amount %= coins[i];
        }
        return amount == 0 ? count: -1;
    }
}

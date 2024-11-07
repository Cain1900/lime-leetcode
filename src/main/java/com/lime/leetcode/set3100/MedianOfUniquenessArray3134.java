package com.lime.leetcode.set3100;

import java.util.*;

public class MedianOfUniquenessArray3134 {

    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n;
        //统计个数
        long count = ((long) n * (n + 1) + 2)/ 4;
        while (left <= right){
            int mid = (left + right) / 2;
            if(calculate(nums, count, mid)){
                //mid对应的统计个数大于count
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    //出现次数最大为up的服务要求子数组个数大于或等于count， 则中位数在【left，mid】之间
    //小于count时，则中位数在【mid + 1， right】之间
    public boolean calculate(int[] nums, long count, int up){
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.size() > up){
                if(map.get(nums[start]) == 1){
                    map.remove(nums[start]);
                }else {
                    map.put(nums[start], map.get(nums[start]) - 1);
                }
                start++;
            }
            ans += (i - start + 1);
            if(ans >= count){
                return true;
            }
        }
        return false;
    }

    public int medianOfUniquenessArray02(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        //前一个统计数
        int[] preCount = new int[n];
        //统计个数
        int m = (n * (n + 1) + 2)/ 4;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++){
            //nums[i]上一次出现的位置sameIndex
            int sameIndex = map.getOrDefault(nums[i], -1) + 1;
            //在上一次出现的位置之前，数量不会变
            for (int j = 0; j < sameIndex; j++){
                updateQueue(queue, m, preCount[j]);
            }
            //在上一次出现的位置之后，数量加1
            for (int j = sameIndex; j < i; j++){
                updateQueue(queue, m, ++preCount[j]);
            }
            //只包含i位置的子数组
            updateQueue(queue, m, 1);
            preCount[i] = 1;
            //更新迭代信息
            map.put(nums[i], i);
        }
        return queue.peek();
    }

    public void updateQueue(PriorityQueue<Integer> queue, int m, int cur){
        if(queue.size() < m){
            queue.offer(cur);
        }else if(queue.peek() > cur){
            queue.poll();
            queue.offer(cur);
        }
    }

    public int medianOfUniquenessArray01(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++){
          set.add(nums[i]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (Integer item: set){
            map.put(item, index);
            index++;
        }
        int m = set.size();
        int[][] count = new int[n + 1][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                count[i + 1][j] = count[i][j] + (map.getOrDefault(nums[i], -1) == j? 1: 0);
            }
        }
        int[] ans = new int[n * (n + 1) / 2];
        index = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                int c = 0;
                for (int k = 0; k < m; k++){
                    if(count[i + 1][k] - count[j][k] > 0){
                        c++;
                    }
                }
                ans[index] = c;
                index++;
            }
        }
        Arrays.sort(ans);
        return ans[(n * (n + 1) + 2)/ 4 - 1];
    }

    public static void main(String[] args) {
        int[] nums = {3,4,3,4,5};
        MedianOfUniquenessArray3134 medianOfUniquenessArray3134 = new MedianOfUniquenessArray3134();
        System.out.println(medianOfUniquenessArray3134.medianOfUniquenessArray(nums));
    }


}

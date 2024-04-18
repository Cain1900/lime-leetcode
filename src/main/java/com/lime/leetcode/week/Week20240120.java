package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2024/1/20.
 */
public class Week20240120 {

    public boolean canSortArray(int[] nums) {
        int preCount = countNumber(nums[0]);
        List<int[]> list = new ArrayList<>();
        int preMin = nums[0];
        int preMax = nums[0];
        for(int i = 1; i < nums.length; i++){
            int count = countNumber(nums[i]);
            if(count != preCount){
                if(nums[i] > preMin && nums[i] < preMax){
                    return false;
                }
                //不能互换
                list.add(new int[]{preMin, preMax});
                preMin = nums[i];
                preMax = nums[i];
                preCount = count;
            }else {
                //能互换
                preMin = Math.min(preMin, nums[i]);
                preMax = Math.max(preMax, nums[i]);
            }
        }
        list.add(new int[]{preMin, preMax});
        boolean isIncrease = true;
        boolean isDecrease = true;
        for(int i = 1; i < list.size(); i++){
            isIncrease &= list.get(i - 1)[1] <= list.get(i)[0];
            isDecrease &= list.get(i - 1)[0] >= list.get(i)[1];
        }
        return isIncrease || isDecrease;
    }

    public int countNumber(int num){
        int count = 0;
        while(num != 0){
            count ++;
            num = num & (num - 1);
        }
        return count;
    }


    public int minimumArrayLength(int[] nums) {
        //不能整除
        //余数
        //剩余长度为0的个数
        int min = Integer.MAX_VALUE;
        int minCount = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == min){
                minCount++;
            }else if(nums[i] < min){
                min = nums[i];
                minCount = 1;
            }
        }
        int gcd = min;
        for (int num : nums) {
            gcd = gcd(gcd, num);
            if (gcd != min) return 1;
        }
        return (minCount + 1)/2;
    }

    int gcd(int a, int b) {
        if (a==0) return b;
        return gcd(b%a, a);
    }

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        //选择的最大堆
        PriorityQueue<int[]> selectQueue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        //未选择的最小堆
        PriorityQueue<int[]> dropQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        //选择的下标
        Set<Integer> selectSet = new HashSet<>();
        long sum = nums[0];
        for(int i = 1; i <= k - 2; i++){
            //一共k - 2个子数组第一个元素
            selectQueue.offer(new int[]{i, nums[i]});
            sum += nums[i];
            selectSet.add(i);
        }
        long min = sum + nums[k - 1];
        //保持queue中含有 k - 2个元素
        for(int i = k - 1; i < n; i++){
            min = Math.min(min, sum + nums[i]);
            //第 k 个子数组的第一个元素为nums[i]
            int start = Math.max( i - dist, 1);
            int end = i - 1;
            //需要在[start， end]中选取k - 2 个最小值;
            /*while (!queue.isEmpty() && queue.peek()[0] < start){
                int[] poll = queue.poll();
                if(set.contains(poll[0])){
                    sum -= poll[1];
                }
            }
            if(!queue.isEmpty() && queue.peek()[1] > nums[i]){
                sum -= queue.poll()[1];
                queue.offer(new int[]{i, nums[i]});
                sum += nums[i];
            }*/
        }
        return min;
    }


    public static void main(String[] args) {
        Week20240120 week20240120 = new Week20240120();
        int[] nums = {10,1,2,2,2,1};
        int k = 4;
        int dist = 3;
        System.out.println(week20240120.minimumCost(nums, k, dist));
    }
}

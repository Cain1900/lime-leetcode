package com.lime.leetcode.set;

import java.util.PriorityQueue;

/**
 * Created by YuHang on 2024/2/6.
 */
public class MagicTower30 {

    public int magicTower(int[] nums) {
        long sum = 1;
        int remove = 0;
        int count = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(nums[i] < 0){
                queue.offer(nums[i]);
            }
            while (sum <= 0 && !queue.isEmpty()){
                Integer poll = queue.poll();
                sum -= poll;
                remove += poll;
                count++;
            }
        }
        if(sum <= remove){
            return -1;
        }
        return count;
    }
}


package com.lime.leetcode.set2200;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Created by YuHang on 2023/7/25.
 */
public class HalveArray2208 {

    public int halveArray(int[] nums) {
        Double sum = 0d;
        PriorityQueue<Double> queue = new PriorityQueue<Double>((a, b) -> (int) (b/a));
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            queue.offer(Double.valueOf(nums[i]));
        }
        int count = 0;
        Double subtract = 0d;
        while (subtract * 2 < sum){
            Double poll = queue.poll() / 2;
            subtract += poll;
            queue.add(poll);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        HalveArray2208 halveArray2208 = new HalveArray2208();
        System.out.println(halveArray2208.halveArray(nums));
    }
}

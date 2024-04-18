package com.lime.leetcode.set600;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by YuHang on 2023/9/11.
 */
public class ScheduleCourse630 {

    public int scheduleCourse1(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (int[] c: courses){
            sum += c[0];
            queue.offer(c[0]);
            if(sum > c[1]){
                sum -= queue.poll();
            }
        }
        return queue.size();
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (int[] c: courses){
            if(sum + c[0] <= c[1]){
                sum += c[0];
                queue.offer(c[0]);
            }else if(!queue.isEmpty() && queue.peek() > c[0]){
                sum += (c[0] - queue.poll());
                queue.offer(c[0]);
            }
            if(sum > c[1]){
                sum -= queue.poll();
            }
        }
        return queue.size();
    }


}

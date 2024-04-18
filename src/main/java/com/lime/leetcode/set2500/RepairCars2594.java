package com.lime.leetcode.set2500;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by YuHang on 2023/9/7.
 */
public class RepairCars2594 {

    public long repairCars(int[] ranks, int cars) {
        //int[] item = new int[3]
        //item[0]表示工人i
        //item[1]表示工人i当前任务数
        //item[2]表示工人i当前任务总时长
        //最大堆
        PriorityQueue<long[]> decreaseQueue = new PriorityQueue<>((a, b) -> Long.compare(b[2], a[2]));
        //int[] item = new int[3]
        //item[0]表示工人i
        //item[1]表示工人i当前任务数
        //item[2]表示工人i增加一个任务时总时长
        //最小堆
        PriorityQueue<long[]> increaseQueue = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));
        Arrays.sort(ranks);
        long[] count = new long[ranks.length];
        decreaseQueue.offer(new long[]{0, cars, Long.valueOf(ranks[0]) * Long.valueOf(cars) * Long.valueOf(cars)});
        increaseQueue.offer(new long[]{0, cars, Long.valueOf(ranks[0]) * Long.valueOf(cars + 1) * Long.valueOf(cars + 1)});
        count[0] = cars;
        for (int i = 1; i < ranks.length; i++){
            decreaseQueue.offer(new long[]{i, 0, 1});
            increaseQueue.offer(new long[]{i, 0, ranks[i]});
        }
        while (decreaseQueue.peek()[2] - increaseQueue.peek()[2] > 0){
            long[] decrease = decreaseQueue.poll();
            long[] increase = increaseQueue.poll();
            decreaseQueue.offer(new long[]{decrease[0], decrease[1] - 1, Long.valueOf(ranks[(int) decrease[0]]) * Long.valueOf(decrease[1] - 1) * Long.valueOf(decrease[1] - 1)});
            count[(int) decrease[0]] = (int) (decrease[1] - 1);
            increaseQueue.offer(new long[]{increase[0], increase[1] + 1, Long.valueOf(ranks[(int) increase[0]]) * Long.valueOf(increase[1] + 2) * Long.valueOf(increase[1] + 2)});
            count[(int) increase[0]] = (int) (increase[1] + 1);
        }
        long max = 0;
        for (int i = 0; i < ranks.length; i++){
            max = Math.max(max, ((long) ranks[i] * count[i]) * count[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        minHeap();
        maxHeap();
        int[] ranks = new int[]{9,13,15,9,5,2,15,12,9,9,9,18,9,22,28,15,4,21,17,23,10,25,10,12,24,30,13,13,24,29,18,5,28,16,1,15,15,30,31,21,15,21,18,10,23,27,27,2,6,4,12,26,17,24,6,31,29,31,29,12,24,27,7,15,14,22,18,23,27,6,6,18,19,15,9,1,3,31,24,16,14,27,6,19,9,2,16,8,9,24,9,1,14,12,3,18,18,21,28,11,15,15,23,26,4,23,12,18,20,19,9,23,3,7,21,21,27,25,29,11,19,1,16,29,25,16,20,21,17,17,20,18,8,15,27,17,21,20,23,14,31,9,29,21,10,3,13,24,3,12,19,14,9,20,25,18,30,8,2,14,3,14,7,23,4,12,26,14,6,3,28,20,8,30,10,19,8,14,3,19,6,12,4,25,28,20,7,19,7,30,17,31,19,19,1,31,26,2,22,13,5,25,31,10,17,24,12,26,23,7,13,22,22,11,29,22,12,7,27,10,26,3,8,4,29,13,4,1,1,24,23,1,13,4,23,26,2,16,25,23,3,25,15,21,1,25,5,25,21,31,14,31,30,21,7,12,2,31,23,20,19,14,28,23,10,18,30,25,3,2,25,14,27,16,18,27,3,25,20,19,29,1,10,18,13,28,14,2,12,5,10,17,4,20,3,14,26,31,30,19,17,6,15,22,28,18,3,11,24,19,7,16,25,28,26,6,7,25,3,28,6,18,9,29,31,9,29,1,23,14,22,17,21,15,14,30,23,12,31,26,25,31,29,28,10,29,2,8,21,24,11,18,24,26,7,12,16,21,13,2,22,9,19,5,7,19,2,22,4,8,19,26,5,13,4,19,27,3,31,22,2,25,30,29,5,7,18,18,18,22,9,5,22,23,25,23,14,5,9,9,31,2,21,12,7,26,6,7,16,19,29,16,25,16,6,19,22,12,21,5,16,20,28,4,27,10,29,27,23,20,17,3,21,15,29,10,19,13,19,15,15,16,20,28,16,17,29,21,12,2,15,22,11,28,4,26,29,4,6,11,5,1,27,19,10,15,19,14,5,27,19,22,14,24,27,11,30,25,12,19,25,23,7,9,20,27,26,25,4,16,19,2,16,13,11,11,13,25,7,23,1,29,18,11,15,16,14,1,21,2,22,31,21,8,19,17,2,3,22,11,8,12,24,16,21,18,7,27,9,12,14,4,14,20,13,16,5,9,1,13,8,2,27,1,17,23,3,30,27,6,17,14,4,3,28,4,13,28,7,29,24,21,27,4,19,16,31,18,15,27,12,25,20,23,21,17,1,6,6,10,10,25,20,14,4,12,7,17,18,24,1,18,11,30,21,21,13,1,24,7,14,11,13,31,13,20,11,11,8,29,22,21,18,21,11,16,30,23,3,5,18,15,19,18,17,6,22,17,10,15,25,10,4,6,29,7,14,26,29,23,5,6,2,1,9,18,17,11,30,27,1,21,17,14,9,16,5,20,3,7,16,14,18,17,21,10,21,30,18,12,28,16,8,6,28,25,8,23,25,27,11,28,7,13,24,31,3,27,11,18,3,8,25,10,8,18,8,16,28,27,27,14,25,31,10,6,11,5,17,27,27,9,2,30,18,2,16,6,21,6,10,24,17,20,1,7,20,16,10,17,15,16,22,24,4,5,1,12,27,3,14,10,10,24,27,6,5,29,10,11,30,15,3,23,17,19,4,1,8,20,25,11,1,31,26,19,11,25,8,14,26,24,27,23,14,22,17,11,17,15,9,3,9,10,30,1,12,30,11,29,18,19,13,17,19,17,20,2,14,11,7,17,10,14,15,18,31,22,25,31,13,4,26,22,20,27,13,17,19,24,20,1,15,1,19,21,30,18,5,23,29,10,24,26,12,30,22,12,2,26,16,7,25,17,12,22,27,4,26,5,17,25,20,12,15,14,6,12,1,16,27,12,15,23,29,17,5,6,23,25,28,14,21,14,30,23,10,22,5,6,21,20,24,17,25,18,5,12,15,30,28,14,28,9,31,15,17,26,6,19,20,24,28,19,4,22,2,17,13,15,3,26,29,6,7,24,16,27,7};
        int cars = 292126;
        RepairCars2594 repairCars2594 = new RepairCars2594();
        System.out.println(repairCars2594.repairCars(ranks, cars));
    }

    public static void maxHeap(){
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> (int) (b[1] - a[1]));
        queue.offer(new long[]{1,2});
        System.out.println(queue.peek());
        queue.offer(new long[]{2,120000l * 120000l});
        System.out.println(queue.peek());
        queue.offer(new long[]{3,1120000l * 120000l});
        System.out.println(queue.peek());
        queue.offer(new long[]{4,20});
        System.out.println(queue.peek());
    }

    public static void minHeap(){
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> (int) (a[1] - b[1]));
        queue.offer(new long[]{1,2});
        System.out.println(queue.peek());
        queue.offer(new long[]{2,120000l * 120000l});
        System.out.println(queue.peek());
        queue.offer(new long[]{3,1120000l * 120000l});
        System.out.println(queue.peek());
        queue.offer(new long[]{4,20});
        System.out.println(queue.peek());
    }

    public long repairCars2(int[] ranks, int cars) {
        long l = 1, r = 1l * ranks[0] * cars * cars;
        while (l < r) {
            //取中间时间点
            long m = l + r >> 1;
            //判断m时间内能否完成
            if (check(ranks, cars, m)) {
                //能完成
                r = m;
            } else {
                //不能完成
                l = m + 1;
            }
        }
        return l;
    }

    //m指定时间，统计m时间内能完成的任务数
    //能完成返回true，不能完成返回false
    public boolean check(int[] ranks, int cars, long m) {
        long cnt = 0;
        for (int x : ranks) {
            cnt += (long) Math.sqrt(m / x);
        }
        return cnt >= cars;
    }
}

package com.lime.leetcode.set1400;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by YuHang on 2023/7/21.
 */
public class FindMaxValueOfEquation1499 {


    public int findMaxValueOfEquation2(int[][] points, int k) {
        int max = Integer.MIN_VALUE;
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < points.length; i++){
            while (!deque.isEmpty() && points[i][0] - deque.peekFirst()[0] > k){
                deque.pollFirst();
            }
            if(!deque.isEmpty()){
                max = Math.max(max, points[i][0] + points[i][1] + deque.peekFirst()[1]);
            }
            while (!deque.isEmpty() && points[i][1] - points[i][0] >= deque.peekLast()[1]){
                deque.pollLast();
            }
            deque.offer(new int[]{points[i][0], points[i][1] - points[i][0]});
        }
        return max;
    }

    public int findMaxValueOfEquation(int[][] points, int k) {
        int max = Integer.MIN_VALUE;
        PriorityQueue<int[]> queue = new PriorityQueue<>((i, j) -> j[1] - i[1]);
        for (int i = 0; i < points.length; i++){
            while (!queue.isEmpty()){
                if(points[i][0] - queue.peek()[0] <= k){
                    max = Math.max(max, points[i][0] + points[i][1] + queue.peek()[1]);
                    break;
                }else {
                    queue.poll();
                }
            }
            queue.offer(new int[]{points[i][0], points[i][1] - points[i][0]});
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,3},{2,0},{5,10},{6,-10}};
        int k = 1;
        FindMaxValueOfEquation1499 findMaxValueOfEquation1499 = new FindMaxValueOfEquation1499();
        System.out.println(findMaxValueOfEquation1499.findMaxValueOfEquation(points, k));
    }

    public int findMaxValueOfEquation1(int[][] points, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < points.length; i++){
            for (int j = i - 1; j >= 0; j--){
                if(points[i][0] - points[j][0] > k){
                    break;
                }
                max = Math.max(max, points[i][0] + points[i][1] + points[j][1] - points[j][0]);
            }
        }
        return max;
    }
}

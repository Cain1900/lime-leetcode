package com.lime.leetcode.set1600;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by YuHang on 2023/8/30.
 */
public class MinimumJumps1654 {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int n = x + a + b + 1;
        int[] reach = new int[n];
        for (int i = 0; i < n; i++){
            reach[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < forbidden.length; i++){
            if(forbidden[i] < n){
                reach[forbidden[i]] = -1;
            }
        }
        Queue<Integer[]> queue = new ArrayDeque<>();
        // 第1个元素: 位置
        // 第2个元素: 次数
        queue.offer(new Integer[]{0, 0});
        while (!queue.isEmpty()){
            Integer[] poll = queue.poll();
            int next = poll[0] + a;
            if(next < n && reach[next] != -1 && poll[1] + 1 < reach[next] ){
                if(next == x){
                    reach[next] = poll[1] + 1;
                }else {
                    queue.offer(new Integer[]{next, poll[1] + 1});
                }
            }
            next = poll[0] - b;
            if(next >= 0 && reach[next] != -1){
                //能跳过去
                reach[next] = Math.min(reach[next], reach[poll[0]] + 1);
                int pre = next;
                next = pre + a;
                if(next < n && reach[next] != -1 && reach[pre] + 1 < reach[next] ){
                    if(next == x){
                        reach[next] = reach[pre] + 1;
                    }else {
                        queue.offer(new Integer[]{next, reach[pre] + 1});
                    }
                }
            }
        }
        return reach[x] == Integer.MAX_VALUE? -1: reach[x];
    }

    public static void main(String[] args) {
        int[] forbidden = new int[]{18,13,3,9,8,14};
        int a = 3;
        int b = 8;
        int x = 6;
        MinimumJumps1654 minimumJumps1654 = new MinimumJumps1654();
        System.out.println(minimumJumps1654.minimumJumps(forbidden, a, b, x));
    }
}

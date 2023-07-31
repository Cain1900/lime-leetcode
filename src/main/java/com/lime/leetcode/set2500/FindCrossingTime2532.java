package com.lime.leetcode.set2500;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by YuHang on 2023/7/7.
 */
public class FindCrossingTime2532 {

    public int findCrossingTime(int n, int k, int[][] time) {
        //k个工人可以到达桥头时间点
        //当moveTime[i] <= sum 时,表示第i个工人可以过桥。执行过桥后更新过桥时间 moveTime[i]
        //从左到右过桥，moveTime[i] = sum + time[i][0] + time[i][1], 它会搬掉一个箱子，待搬箱子数减1, n = n - 1； 总耗时更新为 sum = sum + time[i][0];
        //从右到左过桥，moveTime[i] = sum + time[i][2] + time[i][3], 总耗时更新为 sum = sum + time[i][2];
        int[] moveTime = new int[k];
        /**确定过桥工人的条件
         * 1、待搬箱子数n = 0, 且河右侧没有工人，结束
         * 2、当 n > 0 时，
        */
        //默认采用的是最小堆实现的
        PriorityQueue<Integer> waitLeft = new PriorityQueue<Integer>((x, y) -> {
            //时间总和越高，效率越低，优先过河
            //在队列中优先级越低，越优先被取出
            int timeX = time[x][0] + time[x][2];
            int timeY = time[y][0] + time[y][2];
            return timeX != timeY ? timeY - timeX : y - x;
        });
        for (int i = 0; i < k; i++){
            waitLeft.offer(i);
        }
        PriorityQueue<Integer> waitRight = new PriorityQueue<Integer>((x, y) -> {
            int timeX = time[x][0] + time[x][2];
            int timeY = time[y][0] + time[y][2];
            return timeX != timeY ? timeY - timeX : y - x;
        });
        PriorityQueue<int[]> workLeft = new PriorityQueue<int[]>((x, y) -> {
            if (x[0] != y[0]) {
                return x[0] - y[0];
            } else {
                return x[1] - y[1];
            }
        });
        PriorityQueue<int[]> workRight = new PriorityQueue<int[]>((x, y) -> {
            if (x[0] != y[0]) {
                return x[0] - y[0];
            } else {
                return x[1] - y[1];
            }
        });
        int curTime = 0;
        while (n > 0  || !workRight.isEmpty() || !waitRight.isEmpty()){
            //判断正在工作的工人是否完成，将完成任务工人转化为等待状态
            while (!workLeft.isEmpty() && workLeft.peek()[0] <= curTime) {
                waitLeft.offer(workLeft.poll()[1]);
            }
            while (!workRight.isEmpty() && workRight.peek()[0] <= curTime) {
                waitRight.offer(workRight.poll()[1]);
            }
            if(!waitRight.isEmpty()){
                //河右侧的工人等待过河
                int id = waitRight.poll();
                //河右侧的工人过河
                curTime += time[id][2];
                workLeft.offer(new int[]{curTime + time[id][3], id});
            }else if( n > 0 && !waitLeft.isEmpty()){
                //河左侧的工人等待过河
                int id = waitLeft.poll();
                //河左侧的工人过河
                curTime += time[id][0];
                workRight.offer(new int[]{curTime + time[id][1], id});
                n--;
            }else {
                //都没有等待过河的，寻找最先完成任务的工人
                int nextTime = Integer.MAX_VALUE;
                if(!workLeft.isEmpty()){
                    nextTime = Math.min(nextTime, workLeft.peek()[0]);
                }
                if(!workRight.isEmpty()){
                    nextTime = Math.min(nextTime, workRight.peek()[0]);
                }
                curTime = nextTime;
            }
        }
        return curTime;
    }

    public int compare(int i, int j, int[][] time){
        if(time[i][0] + time[i][2] > time[j][0] + time[j][2]){
            return i;
        }else if(time[i][0] + time[i][2] == time[j][0] + time[j][2]){
            return Math.max(i, j);
        }else {
            return j;
        }
    }

    public static void main(String[] args) {
        int n = 1;
        int k = 3;
        int[][] time = new int[][]{{1,1,2,1},{1,1,3,1},{1,1,4,1}};
        FindCrossingTime2532 findCrossingTime2532 = new FindCrossingTime2532();
        System.out.println(findCrossingTime2532.findCrossingTime(n, k, time));
    }
}

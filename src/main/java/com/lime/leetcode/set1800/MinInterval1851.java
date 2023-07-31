package com.lime.leetcode.set1800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by YuHang on 2023/7/18.
 */
public class MinInterval1851 {

    public int[] minInterval(int[][] intervals, int[] queries) {
        Integer[] queryIndex = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++){
            queryIndex[i] = i;
        }
        Arrays.sort(queryIndex, (i, j) -> queries[i] - queries[j]);
        Arrays.sort(intervals, (i, j) -> i[0] - j[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((i, j) -> i[0] - j[0]);
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        int i = 0;
        for (int j = 0; j < queryIndex.length; j++){
            //从小到大遍历queryIndex[j]
            while (i < intervals.length && intervals[i][0] <= queries[queryIndex[j]]){
                //intervals[i]递增排序,将符合区间范围的intervals放入优先级队列，
                //intervals[i][0] > queryIndex[j]时，中断放入队列
                pq.offer(new int[]{intervals[i][1] - intervals[i][0]  + 1, intervals[i][0], intervals[i][1]});
                i++;
            }
            //pq中保存的是起始点小于queryIndex[j]的区间，按长度从短到长排序的。
            //pq为空时，也就没有符合 intervals[i][0] <= queryIndex[j] 要求的区间了。
            while (!pq.isEmpty() && pq.peek()[2] < queries[queryIndex[j]]){
                //将区间结束点小于queryIndex[j]的区间去掉
                pq.poll();
            }
            if(!pq.isEmpty()){
                res[queryIndex[j]] = pq.peek()[0];
            }
        }
        return res;
    }

    public int[] minInterval2(int[][] intervals, int[] queries) {
        int minQuery = Integer.MAX_VALUE;
        int maxQuery = Integer.MIN_VALUE;
        for (int i = 0; i < queries.length; i++){
            minQuery = Math.min(minQuery, queries[i]);
            maxQuery = Math.max(maxQuery, queries[i]);
        }
        int size = 100, step = (maxQuery - minQuery) / size + 1;
        List<List<int[]>> subIntervalList = new ArrayList<>();
        int[] fullIntervalMin = new int[size];
        for (int i = 0; i < size; i++){
            fullIntervalMin[i] = Integer.MAX_VALUE;
            subIntervalList.add(new ArrayList<>());
        }
        for (int i = 0; i < intervals.length; i++){
            if(intervals[i][1] < minQuery || intervals[i][0] > maxQuery){
                continue;
            }
            int intervalLength = intervals[i][1] - intervals[i][0] + 1;
            int leftIndex = -1;
            if(intervals[i][0] >= minQuery){
                leftIndex = (intervals[i][0] - minQuery) / step;
                subIntervalList.get(leftIndex).add(intervals[i]);
            }
            int rightIndex = size;
            if(intervals[i][1] <= maxQuery){
                rightIndex = (intervals[i][1] - minQuery) / step;
                if(rightIndex != leftIndex){
                    subIntervalList.get(rightIndex).add(intervals[i]);
                }
            }
            for (int j = leftIndex + 1; j < rightIndex; j++){
                fullIntervalMin[j] = Math.min(fullIntervalMin[j], intervalLength);
            }
        }
        int[] minIntervalLength = new int[queries.length];
        for (int i = 0; i < queries.length; i++){
            int index = (queries[i] - minQuery) / step;
            minIntervalLength[i] = fullIntervalMin[index];
            for (int[] interval: subIntervalList.get(index)){
                int intervalLength = interval[1] - interval[0] + 1;
                if(minIntervalLength[i] > intervalLength && interval[1] >= queries[i] && interval[0] <= queries[i]){
                    minIntervalLength[i] = intervalLength;
                }
            }
        }
        for (int i = 0; i < queries.length; i++){
            if(minIntervalLength[i] == Integer.MAX_VALUE){
                minIntervalLength[i] = -1;
            }
        }
        return minIntervalLength;
    }

    public static void main(String[] args) {
        int[][] intervals =  new int[][]{{1,4}, {2,4}, {3,6}, {4,4}};
        int[] queries = new int[]{2,3,4,5};
        MinInterval1851 minInterval1851 = new MinInterval1851();
        System.out.println(minInterval1851.minInterval(intervals, queries));
    }

    public int[] minInterval1(int[][] intervals, int[] queries) {
        int[] minIntervalLength = new int[queries.length];
        int minQuery = Integer.MAX_VALUE;
        int maxQuery = Integer.MIN_VALUE;
        for (int i = 0; i < queries.length; i++){
            minIntervalLength[i] = Integer.MAX_VALUE;
            minQuery = Math.min(minQuery, queries[i]);
            maxQuery = Math.max(maxQuery, queries[i]);
        }
        for (int i = 0; i < intervals.length; i++){
            if(intervals[i][1] < minQuery || intervals[i][0] > maxQuery){
                continue;
            }
            int intervalLength = intervals[i][1] - intervals[i][0] + 1;
            for (int j = 0; j < queries.length; j++){
                if(minIntervalLength[j] > intervalLength && intervals[i][1] >= queries[j] && intervals[i][0] <= queries[j]){
                    minIntervalLength[j] = intervalLength;
                }
            }
        }
        for (int i = 0; i < queries.length; i++){
            if(minIntervalLength[i] == Integer.MAX_VALUE){
                minIntervalLength[i] = -1;
            }
        }
        return minIntervalLength;
    }
}

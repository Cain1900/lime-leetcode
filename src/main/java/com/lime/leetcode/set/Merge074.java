package com.lime.leetcode.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by YuHang on 2023/4/6.
 */
public class Merge074 {

    public int[][] merge(int[][] intervals) {
        //sort(intervals);
        Arrays.sort(intervals, (o1,o2)-> o1[0]-o2[0]);
        List<Integer> ansIndex = new ArrayList<>();
        ansIndex.add(0);
        int[] seqment = intervals[0];
        for (int i = 1; i < intervals.length; i++){
            if(intervals[i][0] > seqment[1]){
                //新建一段
                ansIndex.add(i);
                seqment = intervals[i];
            }else if(intervals[i][1] > seqment[1]) {
                //延续
                seqment[1] = intervals[i][1];
            }
        }
        int[][] ans = new int[ansIndex.size()][2];
        for (int i = 0; i < ansIndex.size(); i++){
            ans[i] = intervals[ansIndex.get(i)];
        }
        return ans;
    }

    public void sort(int[][] intervals){
        for (int i = 0; i < intervals.length; i++){
            for (int j = i; j < intervals.length; j ++){
                if(intervals[i][0] > intervals[j][0]){
                    int[] temp = intervals[j];
                    intervals[j] = intervals[i];
                    intervals[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] intervals = new int[4][2];
        intervals[0] = new int[]{1, 3};
        intervals[1] = new int[]{2, 6};
        intervals[2] = new int[]{8, 10};
        intervals[3] = new int[]{15, 18};
        Merge074 merge074 = new Merge074();
        System.out.println(merge074.merge(intervals));
    }
}

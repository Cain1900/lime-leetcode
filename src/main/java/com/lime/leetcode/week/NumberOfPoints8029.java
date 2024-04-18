package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuHang on 2023/9/10.
 */
public class NumberOfPoints8029 {

    public int numberOfPoints(List<List<Integer>> nums) {
        Collections.sort(nums, (a, b) -> a.get(0) - b.get(0));
        int sum = 0;
        int pre = 0;
        for (List<Integer> item: nums){
            int start;
            if(pre < item.get(0)){
                start = item.get(0) - 1;
            }else {
                start = pre;
            }
            int end = Math.max(start, item.get(1));
            sum += (end - start);
            pre = end;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(3,6));
        nums.add(Arrays.asList(1,5));
        nums.add(Arrays.asList(4,7));
        NumberOfPoints8029 numberOfPoints8029 = new NumberOfPoints8029();
        System.out.println(numberOfPoints8029.numberOfPoints(nums));
    }

    public int minimumMoves(int[][] grid) {
        List<int[]> more = new ArrayList<>();
        List<int[]> less =  new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == 0){
                    less.add(new int[]{i, j});
                }else if(grid[i][j] > 1){
                    more.add(new int[]{i, j});
                }
            }
        }
        int sum = 0;
        for (int[] point: less){
            int count = 2;
            for (int[] desc: more){
                if(Math.max(Math.abs(point[0] - desc[0]), Math.abs(point[1] - desc[1])) != 2){
                    count = 1;
                    break;
                }
            }
            sum += count;
        }
        return sum;
    }
}

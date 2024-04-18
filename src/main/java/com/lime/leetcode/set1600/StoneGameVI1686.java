package com.lime.leetcode.set1600;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by YuHang on 2024/2/2.
 */
public class StoneGameVI1686 {

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] array = new int[n][3];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++){
            array[i] = new int[]{aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]};
        }
        Arrays.sort(array, (a, b) -> b[0] - a[0]);
        int k = 0;
        long aliceSum = 0;
        long bobSum = 0;
        while (k < n){
            if(k % 2 == 0){
                //alice选择
                aliceSum += array[k][1];
            }else {
                //bob选择
                bobSum += array[k][2];
            }
            k++;
        }
        return Long.compare(aliceSum, bobSum);
    }

    public static void main(String[] args) {
        int[] aliceValues = {69,36,42,96,61,80,74,75,75,26,34,73,22,53,13,88,73,26,78,1,12,39,51,10,21,97,85,98,41,92,48,37};
        int[] bobValues = {73,37,39,75,98,88,53,13,96,73,51,26,92,74,75,80,69,97,1,78,85,42,34,41,48,26,12,61,10,22,21,36};
        StoneGameVI1686 stoneGameVI1686 = new StoneGameVI1686();
        System.out.println(stoneGameVI1686.stoneGameVI(aliceValues, bobValues));
    }
}

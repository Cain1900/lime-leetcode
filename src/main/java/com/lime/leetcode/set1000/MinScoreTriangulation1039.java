package com.lime.leetcode.set1000;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/4/3.
 */
public class MinScoreTriangulation1039 {

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int minScoreTriangulation(int[] values) {
        int min = 0;
        int minValue = values[0] * values[1] + values[0] * values[values.length - 1];
        int ans = 0;
        for (int i = 1; i < values.length; i++){
            if(values[i] < values[min]){
                min = i;
            }else if(values[i] == values[min]) {
                if(minValue < values[i -1] * values[i] + values[i] * values[(i + 1) % values.length]){
                    min = i;
                    minValue = values[i -1] * values[i] + values[i] * values[(i + 1) % values.length];
                }
            }
            ans += values[i] * values[i - 1];
        }
        if(min == 0){
            ans -= values[0] * values[1];
        }else if(min == values.length -1){
            ans -= values[values.length - 1] * values[values.length - 2];
        }else {
            ans += values[0] * values[values.length - 1];
            ans -= values[min -1] * values[min];
            ans -= values[min] * values[min + 1];
        }
        return ans * values[min];
    }


    public int minScoreTriangulation1(int[] values) {
        return dp(values, 0, values.length - 1);
    }

    public int dp(int[] values, int i, int j){
        if(i + 2 > j){
            return 0;
        }
        if(i + 2 == j){
            return values[i] * values[i + 1] * values[i + 2];
        }
        int key = i * (values.length - 1) + j;
        if (!memo.containsKey(key)) {
            int minScore = Integer.MAX_VALUE;
            for (int k = i + 1; k < j; k++) {
                minScore = Math.min(minScore, values[i] * values[k] * values[j] + dp(values, i, k) + dp(values, k, j));
            }
            memo.put(key, minScore);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
        int[] values = new int[]{1,3,1,4,1,5};
        MinScoreTriangulation1039 minScoreTriangulation1039 =new MinScoreTriangulation1039();
        System.out.println(minScoreTriangulation1039.minScoreTriangulation1(values));
    }
}

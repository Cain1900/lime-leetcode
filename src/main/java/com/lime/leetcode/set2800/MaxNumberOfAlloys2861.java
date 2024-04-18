package com.lime.leetcode.set2800;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YuHang on 2024/1/27.
 */
public class MaxNumberOfAlloys2861 {

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int[] st = new int[n];
        int[] cos = new int[n];
        for(int i=0;i<n;i++){
            st[i] = stock.get(i);
            cos[i] = cost.get(i);
        }
        int max = 0;
        //k台机器
        for(int i = 0; i < k; i++){
            List<Integer> perNeedList = composition.get(i);
            int[] compos = new int[n];
            int left = 0;
            int right = budget + stock.get(0) / perNeedList.get(0);
            while (left <= right){
                int mid = (right + left) / 2;
                long sum = 0;
                for(int j = 0; j < n; j++){
                    long need = mid * perNeedList.get(j) - st[j];
                    if(need > 0){
                        sum += (need * cost.get(j));
                    }
                }
                if(sum <= budget){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            max = Math.max(max, left - 1);
        }
        return max;
    }

    public int maxNumberOfAlloys1(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int[] st = new int[n];
        int[] cos = new int[n];
        for(int i=0;i<n;i++){
            st[i] = stock.get(i);
            cos[i] = cost.get(i);
        }
        int max = 0;
        //k台机器
        for(int i = 0; i < k; i++){
            List<Integer> perNeedList = composition.get(i);
            int left = max + 1;
            int right = 0;
            int[] com = new int[n];
            long sum = 0;
            for(int j = 0; j < n; j++){
                com[j] = perNeedList.get(j);
                long need = left * com[j] - st[j];
                if(need > 0){
                    sum += (need * cos[j]);
                }
                right = Math.max(right, budget + st[0] / com[0]);
            }
            if(sum > budget){
                continue;
            }
            while (left <= right){
                int mid = (right + left) / 2;
                sum = 0;
                for(int j = 0; j < n; j++){
                    long need = mid * com[j] - st[j];
                    if(need > 0){
                        sum += (need * cos[j]);
                    }
                }
                if(sum <= budget){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            max = Math.max(max, left - 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        int budget = 15;
        List<List<Integer>> composition = Arrays.asList(Arrays.asList(1,1,1), Arrays.asList(1,1,10));
        List<Integer> stock = Arrays.asList(0,0,100);
        List<Integer> cost = Arrays.asList(1,2,3);
        MaxNumberOfAlloys2861 maxNumberOfAlloys2861 = new MaxNumberOfAlloys2861();
        System.out.println(maxNumberOfAlloys2861.maxNumberOfAlloys(n, k, budget, composition, stock, cost));
    }
}

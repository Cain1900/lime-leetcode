package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuHang on 2023/8/20.
 */
public class MaximizeTheProfit7006 {

    public int maximizeTheProfit(int n, List<List<Integer>> offers) {

        Collections.sort(offers, (a, b) -> a.get(1) - b.get(1));
        //profit[i + 1]表示买房到第i间时的最大收益
        int[] profit = new int[n + 1];
        int before = 0;
        for (int i = 0; i < offers.size(); i++){
            List<Integer> item = offers.get(i);
            for(int j = before; j <=  item.get(1) + 1 ; j++){
                profit[j] = Math.max(profit[j], profit[before]);
            }
            profit[item.get(1) + 1] = Math.max(profit[item.get(1) + 1], profit[item.get(0)] + item.get(2));
            before = item.get(1) + 1;
        }
        for(int j = before; j <  n + 1; j++){
            profit[j] = Math.max(profit[j], profit[before]);
        }
        return profit[n];
    }

    public static void main(String[] args) {
        List<List<Integer>> offers = new ArrayList<>();
        List<Integer> item1 = new ArrayList<>(Arrays.asList(0,0,1));
        offers.add(item1);
        List<Integer> item2 = new ArrayList<>(Arrays.asList(0,2,2));
        offers.add(item2);
        List<Integer> item3 = new ArrayList<>(Arrays.asList(1,3,2));
        offers.add(item3);
        MaximizeTheProfit7006 maximizeTheProfit7006 = new MaximizeTheProfit7006();
        System.out.println(maximizeTheProfit7006.maximizeTheProfit(5, offers));
    }


}

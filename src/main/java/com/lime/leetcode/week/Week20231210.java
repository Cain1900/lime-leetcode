package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2023/12/10.
 */
public class Week20231210 {

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < variables.length; i++){
            if(count(count(variables[i][0], variables[i][1], 10), variables[i][2], variables[i][2]) == target){
                ans.add(i);
            }
        }
        return ans;
    }

    public int count(int a, int b, int c){
        if(b == 0){
            return 1;
        }
        int multiply = 1;
        for(int i = 1; i <= b; i++){
            multiply = (multiply * a) % c;
        }
        return multiply;
    }

}

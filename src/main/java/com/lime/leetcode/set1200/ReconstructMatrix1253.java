package com.lime.leetcode.set1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by YuHang on 2023/6/29.
 */

public class ReconstructMatrix1253 {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        Integer[] column1 = new Integer[colsum.length];
        Integer[] column2 = new Integer[colsum.length];
        int count = 0;
        for (int i = 0; i < colsum.length; i++){
            if(colsum[i] == 2){
                column1[i] = 1;
                column2[i] = 1;
                count++;
            }else if(colsum[i] == 0){
                column1[i] = 0;
                column2[i] = 0;
            }
        }
        if(count > upper || count > lower){
            return new ArrayList<>();
        }
        upper -= count;
        lower -= count;
        for (int i = 0; i < colsum.length; i++){
            if(colsum[i] == 1){
                if(upper > 0){
                    column1[i] = 1;
                    column2[i] = 0;
                    upper--;
                }else if(lower > 0){
                    column1[i] = 0;
                    column2[i] = 1;
                    lower--;
                }else {
                    return new ArrayList<>();
                }
            }
        }
        if(upper != 0 || lower != 0){
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(column1));
        ans.add(Arrays.asList(column2));
        return ans;
    }

    public static void main(String[] args) {
        int upper = 5;
        int lower = 5;
        int[] colsum = new int[]{2,1,2,0,1,0,1,2,0,1};
        ReconstructMatrix1253 reconstructMatrix1253 = new ReconstructMatrix1253();
        System.out.println(reconstructMatrix1253.reconstructMatrix(upper, lower, colsum));
    }
}

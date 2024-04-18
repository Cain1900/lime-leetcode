package com.lime.leetcode.set1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuHang on 2023/9/14.
 */
public class QueensAttacktheKing1222 {

    public List<List<Integer>>  queensAttacktheKing(int[][] queens, int[] king) {
        int[] index = new int[8];
        for (int i = 0; i < index.length; i++){
            index[i] = 10;
        }
        Integer[][] position = new Integer[8][2];
        for (int i = 0; i < queens.length; i++){
            if(queens[i][0] == king[0]){
                if(queens[i][1] > king[1] && queens[i][1] - king[1] < index[0]){
                    //同一行右侧
                    index[0] = queens[i][1] - king[1];
                   position[0][0] = queens[i][0];
                   position[0][1] = queens[i][1];
                }else if(queens[i][1] < king[1] && king[1] - queens[i][1] < index[1]){
                    //同一行左侧
                    index[1] = king[1] - queens[i][1];
                    position[1][0] = queens[i][0];
                    position[1][1] = queens[i][1];
                }
            }else if(queens[i][1] == king[1]){
                if(queens[i][0] > king[0] && queens[i][0] - king[0] < index[2]){
                    //同一列上侧
                    index[2] = queens[i][0] - king[0];
                    position[2][0] = queens[i][0];
                    position[2][1] = queens[i][1];
                }else if(queens[i][0] < king[0] && king[0] - queens[i][0] < index[3]){
                    //同一列下侧
                    index[3] = king[0] - queens[i][0];
                    position[3][0] = queens[i][0];
                    position[3][1] = queens[i][1];
                }
            }else if(queens[i][0] - king[0] == queens[i][1] - king[1]){
                if(queens[i][0] > king[0] && queens[i][0] - king[0] < index[4]){
                    //对角线右下角
                    index[4] = queens[i][0] - king[0];
                    position[4][0] = queens[i][0];
                    position[4][1] = queens[i][1];
                }else if(queens[i][0] < king[0] && king[0] - queens[i][0] < index[5]){
                    //对角线左上角
                    index[5] = king[0] - queens[i][0];
                    position[5][0] = queens[i][0];
                    position[5][1] = queens[i][1];
                }
            }else if(queens[i][0] - king[0] == king[1] - queens[i][1]){
                if(queens[i][0] > king[0]  && queens[i][0] - king[0] < index[6]){
                    //对角线右上角
                    index[6] = queens[i][0] - king[0];
                    position[6][0] = queens[i][0];
                    position[6][1] = queens[i][1];
                }else if(queens[i][0] < king[0] && king[0] - queens[i][0] < index[7]){
                    //对角线左下角
                    index[7] = king[0] - queens[i][0];
                    position[7][0] = queens[i][0];
                    position[7][1] = queens[i][1];
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < index.length; i++){
            if(index[i] != 10){
                ans.add(Arrays.asList(position[i]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] queens = new int[][]{{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] king = new int[]{0,0};
        QueensAttacktheKing1222 queensAttacktheKing1222 = new QueensAttacktheKing1222();
        System.out.println(queensAttacktheKing1222.queensAttacktheKing(queens, king));
    }
}

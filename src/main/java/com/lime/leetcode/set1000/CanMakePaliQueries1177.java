package com.lime.leetcode.set1000;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2023/6/15.
 */
public class CanMakePaliQueries1177 {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int[] count = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++){
            count[i + 1] = count[i] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            int num = count[queries[i][0]] ^ count[queries[i][1] + 1];
            int res = 0;
            while(num != 0){
                num &= (num -1);
                res++;
            }
            if(res <= queries[i][2] * 2 + 1){
                ans.add(true);
            }else {
                ans.add(false);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "hunu";
        int[][] queries = {{6,6,0},{1,1,1},{2,5,4},{1,3,1},{5,6,1}};
        CanMakePaliQueries1177 canMakePaliQueries1177 = new CanMakePaliQueries1177();
        System.out.println(canMakePaliQueries1177.canMakePaliQueries(s, queries));
    }
}

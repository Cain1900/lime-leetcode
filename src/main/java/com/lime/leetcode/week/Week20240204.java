package com.lime.leetcode.week;

/**
 * Created by YuHang on 2024/2/4.
 */
public class Week20240204 {


    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        int start = k;
        while(start < n){
            String substring = word.substring(start, n);
            String substring1 = word.substring(0, n - start);
            if(word.substring(start, n).equals(word.substring(0, n - start))){
                break;
            }else {
                start += k;
            }
        }
        return start/k;
    }

    public static void main(String[] args) {
        String word = "abacaba";
        int k = 3;
        Week20240204 week20240204 = new Week20240204();
        System.out.println(week20240204.minimumTimeToInitialState(word, k));
    }
}

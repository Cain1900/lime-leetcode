package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/8/19.
 */
public class CanMakeSubsequence8014 {

    public boolean canMakeSubsequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        for (int i = 0; i < m - n + 1; i++){
            int k = 0;
            for(int j = i; j < m; j++){
                int index = str2.charAt(k) - str1.charAt(j);
                if(index == 0 ||  index == 1 || index == -25){
                    k++;
                    if(k == n){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str1 = "zc";
        String str2 = "ad";
        CanMakeSubsequence8014 canMakeSubsequence8014 = new CanMakeSubsequence8014();
        System.out.println(canMakeSubsequence8014.canMakeSubsequence(str1, str2));
    }
}

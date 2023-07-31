package com.lime.leetcode.set2300;

/**
 * Created by YuHang on 2023/4/9.
 */
public class CheckDistances2399 {

    public boolean checkDistances(String s, int[] distance) {
        for(int i = 0; i < s.length(); i++){
            int j = distance[s.charAt(i) - 'a'] + i + 1;
            if(j >= s.length() || s.charAt(j) != s.charAt(i)){
                return false;
            }else{
                distance[s.charAt(i) - 'a'] = -1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aa";
        int[] distance = new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        CheckDistances2399 checkDistances2399 = new CheckDistances2399();
        System.out.println(checkDistances2399.checkDistances(s, distance));
    }
}

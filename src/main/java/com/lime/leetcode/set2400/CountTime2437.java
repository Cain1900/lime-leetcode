package com.lime.leetcode.set2400;

/**
 * Created by YuHang on 2023/5/9.
 */
public class CountTime2437 {

    public int countTime(String time) {
        int ans = 1;
        if(time.charAt(0) == '?' && time.charAt(1) == '?'){
            //都是未知
            ans = 24;
        }else if(time.charAt(0) == '?' && time.charAt(1) - '3' <= 0 ){
            ans = 3;
        }else if(time.charAt(0) == '?' && time.charAt(1) - '3' > 0 ){
            ans = 2;
        }else if(time.charAt(1) == '?' && time.charAt(0) - '1' <= 0){
            ans = 10;
        }else if(time.charAt(1) == '?' && time.charAt(0) - '1' > 0){
            ans = 4;
        }
        if(time.charAt(3) == '?'){
            ans *= 6;
        }
        if(time.charAt(4) == '?'){
            ans *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        String time = "0?:0?";
        CountTime2437 countTime2437 = new CountTime2437();
        System.out.println(countTime2437.countTime(time));
    }
}

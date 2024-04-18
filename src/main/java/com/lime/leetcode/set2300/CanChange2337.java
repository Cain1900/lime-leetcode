package com.lime.leetcode.set2300;

/**
 * Created by YuHang on 2023/8/21.
 */
public class CanChange2337 {

    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n){
            //遍历到非_位置
            while (i < n && start.charAt(i) == '_'){
                i++;
            }
            while (j < n && target.charAt(j) == '_'){
                j++;
            }
            if(i < n && j < n){
                //不相同时
                if(start.charAt(i) != target.charAt(j)){
                    return false;
                }
                //相同时
                if(start.charAt(i) == 'L'){
                    //L是可以左移的，start的下边较大
                    if(i < j){
                        return false;
                    }
                }else {
                    //R是可以右移的，start的下边较小
                    if(i > j){
                        return false;
                    }
                }
                i++;
                j++;
            }
        }
        while (i < n){
            if(start.charAt(i) != '_'){
                return false;
            }
            i++;
        }
        while (j < n){
            if(target.charAt(j) != '_'){
                return false;
            }
            j++;
        }
        return true;
    }
}

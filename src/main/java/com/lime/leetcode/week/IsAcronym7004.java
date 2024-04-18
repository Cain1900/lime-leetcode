package com.lime.leetcode.week;

import java.util.List;

/**
 * Created by YuHang on 2023/8/20.
 */
public class IsAcronym7004 {

    public boolean isAcronym(List<String> words, String s) {
        int m = words.size(), n = s.length();
        if( m != n){
            return false;
        }
        for (int i = 0; i < m; i++){
            if(words.get(i).charAt(0) != s.charAt(i)){
                return false;
            }
        }
        return true;
    }
}

package com.lime.leetcode.set1000;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2023/4/14.
 */
public class CamelMatch1023 {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            boolean isMatch = true;
            int j = 0;
            for(int k = 0; k < queries[i].length(); k++){
                if(j < pattern.length() && pattern.charAt(j) == queries[i].charAt(k)){
                    j++;
                }else if(queries[i].charAt(k) < 'a' || queries[i].charAt(k) > 'z'){
                    isMatch = false;
                    break;
                }
            }
            if(j < pattern.length()){
                isMatch = false;
            }
            ans.add(isMatch);
        }
        return ans;
    }

    public List<Boolean> camelMatch1(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            boolean isMatch = true;
            int j = 0, k = 0;
            while (j < pattern.length()){
                if( k >= queries[i].length()){
                    isMatch = false;
                    break;
                }
                if(pattern.charAt(j) == queries[i].charAt(k)){
                    j++;
                    k++;
                }else if(queries[i].charAt(k) >= 'a' && queries[i].charAt(k) <= 'z'){
                    k++;
                }else {
                    isMatch = false;
                    break;
                }
            }
            while (k < queries[i].length()){
                if(queries[i].charAt(k) >= 'a' && queries[i].charAt(k) <= 'z'){
                    k++;
                }else {
                    isMatch = false;
                    break;
                }
            }
            ans.add(isMatch);
        }
        return ans;
    }


}

package com.lime.leetcode.set800;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/15.
 */
public class FindReplaceString833 {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        String[][] array = new String[indices.length][3];
        for (int i = 0; i < indices.length; i++){
            array[i][0] = String.valueOf(indices[i]);
            array[i][1] = sources[i];
            array[i][2] = targets[i];
        }
        Arrays.sort(array, (a , b) -> Integer.valueOf(a[0]) - Integer.valueOf(b[0]));
        StringBuilder stringBuilder = new StringBuilder();
        int k = 0;
        for (int i = 0; i < s.length() || k < array.length; i++){
            if(k == array.length || i + array[k][1].length() >= s.length()){
                stringBuilder.append(s.charAt(i));
                continue;
            }
            if(i == Integer.valueOf(array[k][0])){
                if(i + array[k][1].length() < s.length() && s.substring(i, i + array[k][1].length()).equals(array[k][1])){
                    stringBuilder.append(array[k][2]);
                    i += array[k][1].length() - 1;
                }else {
                    stringBuilder.append(s.charAt(i));
                }
                k++;
            }else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}

package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YuHang on 2023/10/14.
 */
public class GetWordsInLongestSubsequence100077 {

    List<String> max = new ArrayList<>();

    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        for(int i = 0; i < n; i++){
            List<String> list = new ArrayList<>();
            list.add(words[i]);
            dp(n, i + 1, words, groups, list, i);
        }
        return max;
    }

    public void dp(int n, int x, String[] words, int[] groups, List<String> list, int pre){
        if(x == n){
            if(list.size() > max.size()){
                max = list;
            }
            return;
        }
        if( groups[pre] != groups[x] && checkHanming(words[pre], words[x])){
            List<String> copiedList = list.stream().collect(Collectors.toList());
            copiedList.add(words[x]);
            dp(n, x + 1, words, groups, copiedList, x);
        }
        dp(n, x + 1, words, groups, list, pre);
    }

    public boolean checkHanming(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        int diff = 0;
        for(int i = 0; i < str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                diff++;
            }
            if(diff > 1){
                return false;
            }
        }
        return diff == 1;
    }

    public static void main(String[] args) {
        int n = 9;
        String[] words = {"bad","dc","bc","ccd","dd","da","cad","dba","aba"};
        int[] groups = {9,7,1,2,6,8,3,7,2};
        GetWordsInLongestSubsequence100077 getWordsInLongestSubsequence100077 = new GetWordsInLongestSubsequence100077();
        System.out.println(getWordsInLongestSubsequence100077.getWordsInLongestSubsequence(n, words, groups));
    }
}

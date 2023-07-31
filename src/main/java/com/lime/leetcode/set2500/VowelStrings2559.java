package com.lime.leetcode.set2500;

/**
 * Created by YuHang on 2023/6/2.
 */
public class VowelStrings2559 {

    public int[] vowelStrings(String[] words, int[][] queries){
        int[] counts = new int[words.length + 1];
        for (int i = 0; i < words.length; i ++){
            if(isVowelStrings(words[i])){
                counts[i + 1] = counts[i] + 1;
            }else {
                counts[i + 1] = counts[i];
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++){
            ans[i] = counts[queries[i][1] + 1] - counts[queries[i][0] + 1];
        }
        return ans;
    }

    public boolean isVowelStrings(String word){
        return isVowelLetter(word.charAt(0)) && isVowelLetter(word.charAt(word.length() - 1));
    }

    public boolean isVowelLetter(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        String[] words = new String[]{"aba","bcb","ece","aa","e"};
        int[][] queries = new int[][]{{0,2},{1,4},{1,1}};
        VowelStrings2559 vowelStrings2559 = new VowelStrings2559();
        System.out.println(vowelStrings2559.vowelStrings(words, queries));
    }
}

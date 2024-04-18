package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/10/15.
 */
public class ShortestBeautifulSubstring100084 {

    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int[] count = new int[n + 1];
        int minLength = 100;
        int start = 0;
        int end = 0;
        int pre = 0;
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '1'){
                count[i + 1] = count[i] + 1;
                while(pre <= i && count[i + 1] - count[pre] >= k){
                    if(count[pre + 1] == count[pre]){
                        pre++;
                        continue;
                    }
                    if(i - pre + 1 < minLength){
                        minLength = i - pre + 1;
                        start = pre;
                        end = i;
                    }else if(i - pre + 1 == minLength){
                        for(int j = 0; j < minLength; j++){
                            if(s.charAt(pre + j) < s.charAt(start + j)){
                                start = pre;
                                end = i;
                                break;
                            }else if(s.charAt(pre + j) > s.charAt(start + j)){
                                break;
                            }
                        }
                    }
                    pre++;
                }
            }else{
                count[i + 1] = count[i];
            }
        }
        if(count[n] < k){
            return "";
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        String s= "110101000010110101";
        int k = 3;
        ShortestBeautifulSubstring100084 shortestBeautifulSubstring100084 = new ShortestBeautifulSubstring100084();
        System.out.println(shortestBeautifulSubstring100084.shortestBeautifulSubstring(s, k));
    }
}

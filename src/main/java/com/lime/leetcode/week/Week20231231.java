package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by YuHang on 2023/12/31.
 */
public class Week20231231 {

    public int maximumLength(String s) {
        PriorityQueue<Integer>[] queues = new PriorityQueue[26];
        for (int i = 0; i < 26; i++){
            queues[i] = new PriorityQueue<>((a, b) -> b - a);
        }
        int preChar = s.charAt(0) - 'a';
        int preNum = 1;
        for(int i = 1; i < s.length(); i++){
            int curChar = s.charAt(i) - 'a';
            if(curChar == preChar){
                preNum++;
            }else {
                queues[preChar].offer(preNum);
                preChar = curChar;
                preNum = 1;
            }
        }
        queues[preChar].offer(preNum);
        int ans = -1;
        for (int i = 0; i < 26; i++){
            if(queues[i].size() == 0){
                continue;
            }else if(queues[i].size() == 1){
                Integer count = queues[i].poll();
                if(count >= 3){
                    ans = Math.max(ans, count - 2);
                }
            }else if(queues[i].size() == 2){
                Integer first = queues[i].poll();
                Integer second = queues[i].poll();
                if(first == second){
                    if(first != 1){
                        ans = Math.max(ans, first - 1);
                    }
                }else if(first == second + 1){
                    ans = Math.max(ans, second);
                }else {
                    ans = Math.max(ans, first - 2);
                }
            }else {
                Integer first = queues[i].poll();
                Integer second = queues[i].poll();
                Integer third = queues[i].poll();
                if(first == second){
                    if(second == third){
                        ans = Math.max(ans, first);
                    }else {
                        ans = Math.max(ans, first - 1);
                    }
                }else if(first == second + 1){
                    ans = Math.max(ans, second);
                }else {
                    ans = Math.max(ans, first - 2);
                }
            }
        }
        return ans;
    }

    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length() / 2;
        int[][] left = new int[n + 1][26];
        for(int i = 0; i < n; i++){
            int c = s.charAt(i) - 'a';
            for(int j = 0; j < 26; j++){
                left[i + 1][j] = left[i][j] + (j == c? 1: 0);
            }
        }
        int[][] right = new int[n + 1][26];
        for(int i = 0; i < n; i++){
            int c = s.charAt(2 * n - 1 - i) - 'a';
            for(int j = 0; j < 26; j++){
                right[i + 1][j] = right[i][j] + (j == c? 1: 0);
            }
        }
        boolean[] ans = new boolean[queries.length];
        boolean all = true;
        for(int i = 0; i < 26; i++){
            if(left[n][i] != right[n][i]){
                all = false;
                break;
            }
        }
        if(!all){
            Arrays.fill(ans, false);
            return ans;
        }
        boolean[] leftLoop = new boolean[n + 1];
        boolean isSame = true;
        leftLoop[0] = true;
        for(int i = 0; i < n; i++){
            if(isSame && s.charAt(i) == s.charAt(2 * n - 1 - i)){
                leftLoop[i + 1] = true;
            }else {
                isSame = false;
                leftLoop[i + 1] = isSame;
            }
        }
        boolean[] rightLoop = new boolean[n + 1];
        rightLoop[0] = true;
        isSame = true;
        for(int i = 0; i < n; i++){
            if(isSame && s.charAt(n - 1 - i) == s.charAt( n + i)){
                rightLoop[i + 1] = true;
            }else {
                isSame = false;
                rightLoop[i + 1] = isSame;
            }
        }
        for(int i = 0; i < queries.length; i++){
            int leftIndex = Math.min(queries[i][0], 2 * n - 1 - queries[i][3]);
            int rightIndex = Math.min(n - 1 - queries[i][1], queries[i][2] - n);
            if(leftLoop[leftIndex] && rightLoop[rightIndex]){
                ans[i] = true;
            }else {
                ans[i] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "odaxusaweu" +
                "asuoeudxwa";
        int[][] queries = {{0,5,10,14}};
        Week20231231 week20231231 = new Week20231231();
        System.out.println(week20231231.canMakePalindromeQueries(s, queries));
    }


}

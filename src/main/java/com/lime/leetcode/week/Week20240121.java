package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by YuHang on 2024/1/21.
 */
public class Week20240121 {

    public int minimumPushes(String word) {
        int[] dp = new int[26];
        for (int i = 0; i < word.length(); i++){
            dp[word.charAt(i) - 'a']++;
        }
        Arrays.sort(dp);
        long sum = 0;
        for(int i = 0; i < 26; i++){
            sum += (dp[25 - i] * (i / 8 + 1));
        }
        return (int) sum;
    }

    public int[] countOfPairs2(int n, int x, int y) {
        //确保 x <= y
        if(y < x){
            int t = x;
            x = y;
            y = t;
        }
        int[] distance = new int[n];
        for (int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                distance[Math.min(Math.abs(x - 1 - i) + Math.abs(y - 1 - j) + 1, j - i) - 1] += 2;
            }
        }
        return distance;
    }

    public long[] countOfPairs(int n, int x, int y) {
        //确保 x <= y
        if(y < x){
            int t = x;
            x = y;
            y = t;
        }
        long[] distance = new long[n];
        for (int i = 0; i < n; i++){
            //没有x，y点时理论值
            distance[i] = 2 * ( n - 1 - i);
            //经过x，y点时影响点
            for (int j = 0; j <= i; j++){
                //离x距离j: 坐标点  x - j 和 x + j
                //离y距离 i - j: 坐标点 y - (i - j) 和 y + (i + j)
                if(x - j >= 0){
                    int ll = y - (i - j) - (x - j);
                    if(ll > i){
                        distance[ll] -= 2;
                        distance[i] += 2;
                    }
                    if(y + (i + j) < n){
                        int lr = y + (i + j) - (x - j);
                        if(lr > i){
                            distance[lr] -= 2;
                            distance[i] += 2;
                        }
                    }
                }
                if(y - (i - j) > x + j){
                    int rl = y - (i - j) - (x + j);
                    if(rl > i){
                        distance[rl] -= 2;
                        distance[i] += 2;
                    }
                }
                if(y + (i + j) < n){
                    int rr = y + (i + j) - (x + j);
                    if(rr > i){
                        distance[rr] -= 2;
                        distance[i] += 2;
                    }
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Week20240121 week20240121 = new Week20240121();
        //System.out.println(week20240121.countOfPairs(3, 1,3));
        System.out.println(week20240121.maximumSwap(2736));
    }

    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        Stack<Integer> stack = new Stack<>();
        boolean isDecrease = true;
        int max = 0;
        int maxIndex = 0;
        for(int i = 0; i < n; i++){
            int i1 = str.charAt(i) - '0';
            if(isDecrease){
                if(!stack.isEmpty() && i1 > stack.peek()) {
                    isDecrease = false;
                    max = i1;
                    maxIndex = i;
                }else {
                    stack.push(i1);
                }
            }else if(i1 >= max){
                max = i1;
                maxIndex = i;
            }
        }
        if(isDecrease){
            return num;
        }else {
            while (!stack.isEmpty() && max > stack.peek()){
                stack.pop();
            }
            int swapIndex = stack.size();
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < n; i++){
                if(i == swapIndex ){
                    stringBuilder.append(str.charAt(maxIndex));
                }else if( i == maxIndex){
                    stringBuilder.append(str.charAt(swapIndex));
                }else {
                    stringBuilder.append(str.charAt(i));
                }
            }
            return Integer.valueOf(stringBuilder.toString());
        }
    }
}

package com.lime.leetcode.week;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Queue;

/**
 * 功能描述
 * <p>
 * $
 *
 * @author yuh109
 * @return $
 * @date $ $
 */
public class Week20240929 {

    public char kthCharacter(int k) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('a');
        while (stringBuilder.length() < k){
            int length = stringBuilder.length();
            for (int i = 0; i < length; i++){
                int cur = stringBuilder.charAt(i) - 'a';
                stringBuilder.append((char)('a' + ((cur + 1) % 26)));
                if(length + i + 1 == k){
                    break;
                }
            }
        }
        return stringBuilder.charAt(k - 1);
    }

    public char kthCharacter(long k, int[] operations) {
        int count = 0;
        int index = 0;
        k = k - 1;
        while (k > 0){
            if(k % 2 == 0 && operations[index] == 1){
                count++;
            }
            k /= 2;
            index++;
        }
        return (char) ('a' + (count % 26));
    }

    public char kthCharacter2(long k, int[] operations) {
        return dp(k - 1, operations);
    }

    public char dp(long num, int[] operations){
        if(num == 0){
            return 'a';
        }
        int i = 64 - Long.numberOfLeadingZeros(num);
        if(operations[i - 1] == 0){
            return dp(num - Long.highestOneBit(num), operations);
        }else {
            char dp = dp(num - Long.highestOneBit(num), operations);
            return (char) ('a' + (dp - 'a' + 1) % 26);
        }
    }

    public static void main(String[] args) {
        String word = "iqeaouqi";
        int i = Long.numberOfLeadingZeros(4);
        int k = 2;
        int[] operations = {1};
        Week20240929 week20240929 = new Week20240929();
        System.out.println(week20240929.kthCharacter(k, operations));

    }

    public long countOfSubstrings(String word, int k) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        Queue<Integer> queue = new ArrayDeque<>();
        int[] pre = new int[5];
        Arrays.fill(pre, -1);
        long ans = 0;
        int left = -1;
        for (int i = 0; i < n; i++){
            if(chars[i] == 'a'){
                pre[0] = i;
            }else if(chars[i] == 'e'){
                pre[1] = i;
            }else if(chars[i] == 'i'){
                pre[2] = i;
            }else if(chars[i] == 'o'){
                pre[3] = i;
            }else if(chars[i] == 'u'){
                pre[4] = i;
            }else {
                queue.add(i);
            }
            if(queue.size() > k){
                //从右往左，第k + 1个辅音字母
                left = queue.poll();
            }
            int min = Arrays.stream(pre).min().getAsInt();
            if(min == -1){
                continue;
            }
            if(k == 0){
                ans += Math.max(min - left, 0);
            }else if(queue.size() == k) {
                ans += Math.max(Math.min(min, queue.peek()) - left, 0);
            }
        }
        return ans;
    }
}

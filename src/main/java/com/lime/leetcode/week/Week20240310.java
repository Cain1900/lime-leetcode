package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by YuHang on 2024/3/10.
 */
public class Week20240310 {

    public int minimumBoxes(int[] apple, int[] capacity) {
        long sum = 0;
        for(int i = 0; i < apple.length; i++){
            sum += apple[i];
        }
        int count = 0;
        int m = capacity.length;
        Arrays.sort(capacity);
        while (sum > 0 && count < m){
            count++;
            sum -= capacity[m - count];
        }
        return count;
    }

    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        Arrays.sort(happiness);
        long sum = 0;
        for (int i = 1; i <= k; i++){
            sum += Math.max(0, happiness[n - i] + 1 - i);
        }
        return sum;
    }

    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        Node head = new Node(0, null);
        for (int i = 0; i < n; i++){
            Node node  = head;
            char[] chars = arr[i].toCharArray();
            for (int j = 0; j < chars.length; j++){
                int index = chars[j] - 'a';
                if(node.nextNode[index] == null){
                    node.nextNode[index] = new Node(node.length + 1, chars[j]);
                }
                node = node.nextNode[index];
                node.count++;
                node.arrIndex = i;
            }
        }
        String[] ans = new String[n];
        return ans;

    }

    public String getHint(String secret, String guess) {
        int[] secretArr = new int[10];
        int[] guessArr = new int[10];
        char[] secretChars = secret.toCharArray();
        char[] guessChars = guess.toCharArray();
        int same = 0;
        for(int i = 0; i < secretChars.length; i++){
            if(secretChars[i] == guessChars[i]){
                same++;
            }else {
                secretArr[secretChars[i] - '0']++;
                guessArr[guessChars[i] - '0']++;
            }
        }
        int some = 0;
        for (int i = 0; i < 10; i++){
            some += Math.min(secretArr[i], guessArr[i]);
        }
        return new StringBuilder().append(same).append("A").append(some)
                .append("B").toString();
    }

    class Node{
        int length;
        char c;
        int count;
        int arrIndex = -1;
        Node[] nextNode = new Node[26];

        public Node(int length, Character c) {
            this.length = length;
            this.c = c;
        }
    }


    public boolean isSubstringPresent(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i < n; i++){
            set.add((chars[i - 1] - 'a') * 100 + (chars[i] - 'a'));
        }
        for(int i = 1; i < n; i++){
            if(set.contains((chars[i] - 'a') * 100 + (chars[i - 1] - 'a'))){
                return true;
            }
        }
        return false;
    }

    public long countSubstrings(String s, char c) {
        char[] chars = s.toCharArray();
        long count  = 0;
        for(char sc: chars){
            if(sc == c){
                count++;
            }
        }
        return count * (count + 1) / 2;
    }

    public int minimumDeletions(String word, int k) {
        int[] count = new int[26];
        char[] chars = word.toCharArray();
        for(char c: chars){
            count[c - 'a']++;
        }
        Arrays.sort(count);
        int min = Integer.MAX_VALUE;
        int start = 0;
        while (count[start] == 0){
            start++;
        }
        for(int i = count[start]; i <= count[25] - k; i++){
            int sum = 0;
            int max = i + k;
            for(int j = start; j < 26; j++){
                if(count[j] < i){
                    sum += count[j];
                }else if( count[j] > max){
                    sum += (count[j] - max);
                }
            }
            min = Math.min(min, sum);
        }
        return min == Integer.MAX_VALUE? 0: min;
    }

    public static void main(String[] args) {
        Week20240310 week20240310 = new Week20240310();
        System.out.println(week20240310.minimumDeletions("aabcaba", 0));
    }

}

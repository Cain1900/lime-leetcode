package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/9/2.
 */
public class CanBeEqual7021 {


    public boolean canBeEqual1(String s1, String s2) {
        if(((s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2))
                || (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0))) &&
                ((s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3))
                || (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1)))){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[][] count = new int[26][2];
        for(int i = 0; i < n; i++){
            count[s1.charAt(i) - 'a'][i % 2]++;
            count[s2.charAt(i) - 'a'][i % 2]--;
        }
        for(int i = 0; i < 26; i++){
            if(count[i][0] != 0 || count[i][1] != 0){
                return false;
            }
        }
        return true;
    }

    public long maxSum(List<Integer> nums, int m, int k) {
        int n = nums.size();
        long max = 0;
        Map<Integer, Integer> count = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        long sum = 0;
        for(int i = 0; i < k; i++){
            Integer num = nums.get(i);
            sum += num;
            count.put(num, count.getOrDefault(num, 0) + 1);
            set.add(num);
        }
        if(set.size() >= m){
            max = Math.max(max, sum);
        }
        for(int i = k; i < n; i++){
            Integer first = nums.get(i - k);
            sum -= first;
            count.put(first, count.get(first) - 1);
            if(count.get(first) == 0){
                set.remove(first);
            }
            Integer last = nums.get(i);
            sum += last;
            count.put(last, count.getOrDefault(last, 0) + 1);
            set.add(last);
            if(set.size() >= m){
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int mod = 1000000007;
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); i++){
            frequency[s.charAt(i) - 'a']++;
        }
        Arrays.sort(frequency);
        long sum = 1;
        int count = 0;
        int pre = Integer.MAX_VALUE;
        for(int i = 25; i >= 26 - k; i--){
            if(frequency[i] == 0){
                return 0;
            }
            sum = (sum * frequency[i]) % mod;
            if(frequency[i] != pre){
                pre = frequency[i];
                count = 1;
            }else {
                count++;
            }
        }
        int same = count;
        for(int i = 25 - k; i >= 0; i--){
            if(frequency[i] == pre){
                same++;
            }else {
                break;
            }
        }
        if(same == count){
            return (int) sum;
        }else {
            sum = (sum * combination(same, count)) % mod;
            return (int) sum;
        }
    }

    private static long combination(int n, int m) {
        return factorial(n, n - m) / factorial(m, 0);
    }

    private static long factorial(int n, int limit) {
        long res = 1;
        for (int i = n; i > limit; i--) {
            res = res * i;
        }
        return res;
    }


    public static void main(String[] args) {
        test4();
    }

    public static void test4() {
        String s = "iewvwmocnrqfuxa";
        Integer k = 12;
        CanBeEqual7021 canBeEqual7021 = new CanBeEqual7021();
        System.out.println(canBeEqual7021.countKSubsequencesWithMaxBeauty(s, k));
    }

    public static void test(String[] args) {
        String s1 = "jh";
        String s2 = "fy";
        CanBeEqual7021 canBeEqual7021 = new CanBeEqual7021();
        System.out.println(canBeEqual7021.checkStrings(s1, s2));
    }
}

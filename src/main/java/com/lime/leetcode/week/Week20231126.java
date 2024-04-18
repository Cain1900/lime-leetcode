package com.lime.leetcode.week;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by YuHang on 2023/11/26.
 */
public class Week20231126 {

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int t = k / n + 1;
        for(int i = 0; i < m; i++){
            //每一行
            if(n % 2 == 0){
                //偶数行
                for(int j = 0; j < n; j++){
                    if(mat[i][j] != mat[i][(j + n * t - k) % n]){
                        return false;
                    }
                }
            }else {
                //奇数行
                for(int j = 0; j < n; j++){
                    if(mat[i][j] != mat[i][(j + k) % n]){
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private static void testAreSimilar() {
        int[][] mat = {{2, 2}, {2, 2}};
        int k = 3;
        Week20231126 week20231126 = new Week20231126();
        System.out.println(week20231126.areSimilar(mat, k));
    }


    public static void main(String[] args) {
        testBeautifulSubstrings();
    }



    private static void testLexicographicallySmallestArray() {
        int[] nums = {1,7,28,19,10};
        int limit = 3;
        Week20231126 week20231126 = new Week20231126();
        System.out.println(week20231126.lexicographicallySmallestArray(nums, limit));
    }


    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++){
            dp[i][0] = nums[i];
            dp[i][1] = i;
        }
        Arrays.sort(dp, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];
        PriorityQueue queue0 = new PriorityQueue();
        queue0.offer(dp[0][0]);
        PriorityQueue queue1 = new PriorityQueue();
        queue1.offer(dp[0][1]);
        for (int i = 1; i < n; i++){
            if(dp[i][0] - dp[i - 1][0] <= limit){
                //在一个连通集
                queue0.offer(dp[i][0]);
                queue1.offer(dp[i][1]);
            }else {
                //不在同一个连通集
                while (!queue0.isEmpty()){
                    ans[(int) queue1.poll()] = (int) queue0.poll();
                }
                queue0.offer(dp[i][0]);
                queue1.offer(dp[i][1]);
            }
        }
        while (!queue0.isEmpty()){
            ans[(int) queue1.poll()] = (int) queue0.poll();
        }
        return ans;
    }




    public int beautifulSubstrings(String s, int k) {
        int n = s.length();
        int[][] num = new int[n + 1][2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> first = new ArrayList<>();
        first.add(0);
        map.put(0, first);
        for (int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                num[i + 1][0] = num[i][0] + 1;
                num[i + 1][1] = num[i][1];
            }else {
                num[i + 1][0] = num[i][0];
                num[i + 1][1] = num[i][1] + 1;
            }
            int diff = num[i + 1][1] - num[i + 1][0];
            if(map.containsKey(diff)){
                map.get(diff).add(i + 1);
            }else {
                List<Integer> set = new ArrayList<>();
                set.add(i + 1);
                map.put(diff, set);
            }
        }
        int ans = 0;
        for (Integer item: map.keySet()){
            List<Integer> list = map.get(item);
            Map<Integer, Integer> remainderMap = new HashMap<>();
            Set<Integer> remainderSet = new HashSet<>();
            for (int i = 0; i < list.size(); i++){
                int remainder = num[list.get(i)][0] % k;
                remainderMap.put(remainder, remainderMap.getOrDefault(remainder, 0) + 1);
                remainderSet.add(remainder);
            }
            List<Integer> remainderList = new ArrayList<>(remainderSet);
            for (int i = 0; i < remainderList.size(); i++){
                Integer count = remainderMap.get(remainderList.get(i));
                ans += (count * (count - 1) / 2);
                for (int j = i + 1; j < remainderList.size(); j++){
                    int remainderLeft = remainderList.get(i) - remainderList.get(j) + k;
                    if(remainderLeft * remainderLeft % k == 0){
                        ans += (remainderMap.get(remainderList.get(i)) * remainderMap.get(remainderList.get(j)));
                    }
                }
            }
        }
        return ans;
    }

    private static void testBeautifulSubstrings() {
        String s = "uzuxpzou";
        int k = 3;
        Week20231126 week20231126 = new Week20231126();
        System.out.println(week20231126.beautifulSubstrings(s, k));
    }
}

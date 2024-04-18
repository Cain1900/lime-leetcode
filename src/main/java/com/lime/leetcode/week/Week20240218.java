package com.lime.leetcode.week;

import javafx.print.Collation;

import java.util.*;

/**
 * Created by YuHang on 2024/2/18.
 */
public class Week20240218 {

    public int countPrefixSuffixPairs_01(String[] words) {
        int sum = 0;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        for (int i = 0; i < words.length; i++){
            for (int j = i + 1; j < words.length; j++){
                if(isPrefixAndSuffix(words[i], words[j])){
                    sum++;
                }
            }
        }
        return sum;
    }

    public boolean isPrefixAndSuffix(String str1, String str2){
        int length1 = str1.length();
        int length2 = str2.length();
        if(length1 > length2){
            return false;
        }
        if(str1.equals(str2.substring(0, length1)) && str1.equals(str2.substring(length2 - length1))){
            return true;
        }else {
            return false;
        }
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        for(int i = 8; i > 0; i--){
            int min = (int) Math.pow(10, i - 1);
            Set<Integer> set = new HashSet<>();
            for (int item: arr1){
                if(item >= min){
                    set.add(Integer.valueOf(String.valueOf(item).substring(0, i)));
                }
            }
            if(!set.isEmpty()){
                for (int item: arr2){
                    if(item >= min && set.contains(Integer.valueOf(String.valueOf(item).substring(0, i)))){
                        return i;
                    }
                }
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        Week20240218 week20240218 = new Week20240218();
        //testLongestCommonPrefix(week20240218);
        testMostFrequentPrime(week20240218);
    }

    private static void testMostFrequentPrime(Week20240218 week20240218) {
        int[][] mat = {{9,7,8},{4,6,5},{2,8,6}};
        week20240218.mostFrequentPrime(mat);
    }

    private static void testLongestCommonPrefix(Week20240218 week20240218) {
        int[] arr1 = {1, 10, 100};
        int[] arr2 = {1000};
        week20240218.longestCommonPrefix(arr1, arr2);
    }

    public int mostFrequentPrime(int[][] mat) {
        int[][] index = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        int m = mat.length, n = mat[0].length;
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //起始位置
                for (int k= 0; k < 8; k++){
                    //延8个方向移动
                    List<Integer> list = new ArrayList<>();
                    int pre = mat[i][j];
                    list.add(pre);
                    int dx = i + index[k][0];
                    int dy = j + index[k][1];
                    while (dx >= 0 && dx < m && dy >= 0 && dy < n){
                        pre = pre * 10 + mat[dx][dy];
                        list.add(pre);
                        dx += index[k][0];
                        dy += index[k][1];
                    }
                    for (Integer item: list){
                        if(item > 10){
                            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
                        }
                    }
                }
            }
        }
        int max = -1;
        int countMax = 0;
        for (Integer item: countMap.keySet()){
            if(isPrime(item)){
                Integer count = countMap.get(item);
                if(count > countMax){
                    max = item;
                    countMax = count;
                }else if(count == countMax && item > max){
                    max = item;
                }
            }
        }
        return max;
    }

    public boolean isPrime(int x){
        for(int j= 2; j<= Math.sqrt(x); j++){
            if(x%j == 0){
                return false;
            }
        }
        return true;
    }

    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        Node root = new Node();
        for (String item: words){
            char[] t = item.toCharArray();
            int n = t.length;
            //z[i]表示从第i位开始的后缀，与原字符串最长公共前缀的长度；当最长公共前缀的长度等于后缀长度时，符合即使前缀又是后缀
            //z[i] == n - 1 - i 时，符合要求，此时最长公共前缀的长度为z[i]
            int[] z = new int[n];
            int l = 0, r = 0;
            for (int i = 1; i < n; i++) {
                if (i <= r) {
                    //[l,r]与[0, r - l]相同，是公共前缀
                    z[i] = Math.min(z[i - l], r - i + 1);
                }
                //i表示从第i位开始分析后缀，z[i]表示公共前缀长度， i + z[i]表示后缀的下标
                //z[i]表示前缀的下标
                while (i + z[i] < n && t[0 + z[i]] == t[i + z[i]]) {
                    //存在公共前缀，l表示后缀的起始点，r表示后缀的终点
                    l = i;
                    r = i + z[i];
                    z[i]++;
                }
            }
            z[0] = n;

            //记录当前item
            Node cur = root;
            for (int i = 0; i < item.length(); i++){
                int c = t[i] - 'a';
                if(cur.son[c] == null){
                    cur.son[c] = new Node();
                }
                cur = cur.son[c];
                //判断是否满足既是前缀，又是后缀
                if (z[n - 1 - i] == i + 1) { // t 的长为 i+1 的前后缀相同
                    ans += cur.cnt;
                }
            }
            cur.cnt++;
        }
        return ans;
    }

    public class Node {
        Node[] son = new Node[26];
        int cnt;
    }
}

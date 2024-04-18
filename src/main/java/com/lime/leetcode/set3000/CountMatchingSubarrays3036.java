package com.lime.leetcode.set3000;

/**
 * Created by YuHang on 2024/2/16.
 */
public class CountMatchingSubarrays3036 {


    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int ans = 0;
        int m = pattern.length;
        //最大匹配数
        int[] dp = new int[m];
        int preMaxLength = 0;
        for(int i = 1; i < m; i++){
            //计算第i位最大匹配长度
            //计算第i位能否继承前面的匹配长度
            int curMaxLength = preMaxLength;
            while (curMaxLength > 0 && pattern[curMaxLength] != pattern[i]){
                //不能继承最大匹配长度
                //次大匹配长度是在最大匹配长度内产生的，最大匹配长度curMaxLength对应数组的最大匹配长度存放在dp[curMaxLength - 1]中
                curMaxLength = dp[curMaxLength - 1];
            }
            //结束while循环条件：
            //curMaxLength == 0: 最大匹配长度为0
            //pattern[curMaxLength] == pattern[i]: 可以继承前面的匹配长度
            if(pattern[curMaxLength] == pattern[i]){
                curMaxLength++;
            }
            //pattern[curMaxLength] != pattern[i]时，curMaxLength依旧为0
            dp[i] = curMaxLength;
            preMaxLength = curMaxLength;
        }
        int j = 0;
        for(int i = 1; i < nums.length; i++){
            int v = Integer.compare(nums[i], nums[i - 1]);
            //不相等
            while (j > 0 && v != pattern[j]){
                j = dp[j - 1];
            }
            if(v == pattern[j]){
                j++;
            }
            if(j == m){
                ans++;
                j = dp[j - 1];
            }
        }
        return ans;
    }

    public int countMatchingSubarrays_02(int[] nums, int[] pattern) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                stringBuilder.append('A');
            }else if(nums[i] == nums[i - 1]){
                stringBuilder.append('B');
            }else {
                stringBuilder.append('C');
            }
        }
        StringBuilder patternStringBuilder = new StringBuilder();
        for (int i = 0; i < pattern.length; i++){
            if(pattern[i] == 1){
                patternStringBuilder.append('A');
            }else if(pattern[i] == 0){
                patternStringBuilder.append('B');
            }else {
                patternStringBuilder.append('C');
            }
        }
        String patternString = patternStringBuilder.toString();
        int ans = 0;
        int m = pattern.length;
        //最大匹配数
        int[] dp = new int[m];
        int preMaxLength = 0;
        for(int i = 1; i < m; i++){
            //计算第i位最大匹配长度
            //计算第i位能否继承前面的匹配长度
            int curMaxLength = preMaxLength;
            while (curMaxLength > 0 && pattern[curMaxLength] != pattern[i]){
                //不能继承最大匹配长度
                //次大匹配长度是在最大匹配长度内产生的，最大匹配长度curMaxLength对应数组的最大匹配长度存放在dp[curMaxLength - 1]中
                curMaxLength = dp[curMaxLength - 1];
            }
            //结束while循环条件：
            //curMaxLength == 0: 最大匹配长度为0
            //pattern[curMaxLength] == pattern[i]: 可以继承前面的匹配长度
            if(pattern[curMaxLength] == pattern[i]){
                curMaxLength++;
            }
            //pattern[curMaxLength] != pattern[i]时，curMaxLength依旧为0
            dp[i] = curMaxLength;
            preMaxLength = curMaxLength;
        }
        int j = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //不相等
            while (j > 0 && stringBuilder.charAt(i) != patternString.charAt(j)){
                j = dp[j - 1];
            }
            if(stringBuilder.charAt(i) == patternString.charAt(j)){
                j++;
            }
            if(j == m){
                ans++;
                j = dp[j - 1];
            }
        }
        return ans;
    }

    public int countMatchingSubarrays_01(int[] nums, int[] pattern) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                stringBuilder.append('A');
            }else if(nums[i] == nums[i - 1]){
                stringBuilder.append('B');
            }else {
                stringBuilder.append('C');
            }
        }
        StringBuilder patternStringBuilder = new StringBuilder();
        for (int i = 0; i < pattern.length; i++){
            if(pattern[i] == 1){
                patternStringBuilder.append('A');
            }else if(pattern[i] == 0){
                patternStringBuilder.append('B');
            }else {
                patternStringBuilder.append('C');
            }
        }
        String patternString = patternStringBuilder.toString();
        int ans = 0;
        for(int i = 0; i < nums.length - pattern.length; i++){
            String substring = stringBuilder.substring(i, i + pattern.length);
            if(patternString.equals(substring)){
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CountMatchingSubarrays3036 countMatchingSubarrays3036 = new CountMatchingSubarrays3036();
        int[] nums = {1,2,3,4,5,6};
        int[] pattern = {1,1};
        System.out.println(countMatchingSubarrays3036.countMatchingSubarrays(nums, pattern));
    }


    public int countMatchingSubarrays_03(int[] nums, int[] pattern) {
        int n = nums.length;
        int m = pattern.length;
        int[] matchLength = new int[m];
        int preMatchLength = 0;
        for(int i = 1; i < m; i++){
            int curMatchLength = preMatchLength;
            while (curMatchLength > 0 && pattern[curMatchLength] != pattern[i]){
                curMatchLength = matchLength[curMatchLength - 1];
            }
            if(pattern[curMatchLength] == pattern[i]){
                curMatchLength++;
            }
            matchLength[i] = curMatchLength;
            preMatchLength = curMatchLength;
        }
        int ans = 0;
        int j = 0;
        for (int i = 1; i < n; i++){
            int v = Integer.compare(nums[i], nums[i - 1]);
            while (j > 0 && pattern[j] != v){
                j = matchLength[j - 1];
            }
            if(pattern[j] == v){
                j++;
            }
            if(j == m){
                ans++;
                j = matchLength[j];
            }
        }
        return ans;
    }
}

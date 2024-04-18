package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/12/17.
 */
public class Week20231217 {

    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int[][] ans = new int[nums.length / 3][3];
        for(int i = 0; i < nums.length / 3; i++){
            if(nums[i * 3 + 2] - nums[i * 3] > k){
                return new int[][]{};
            }
            ans[i][0] = nums[i * 3];
            ans[i][1] = nums[i * 3 + 1];
            ans[i][2] = nums[i * 3 + 2];
        }
        return ans;
    }

    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        Set<Long> candidates = new HashSet<>();
        candidates.addAll(getCandidates(String.valueOf(nums[nums.length/2])));
        if(nums.length % 2 == 0){
            candidates.addAll(getCandidates(String.valueOf(nums[nums.length/2 - 1])));
        }
        long min = Long.MAX_VALUE;
        for (Long candidate: candidates){
            long sum = 0;
            for (int i = 0; i < nums.length; i++){
                sum += Math.abs(candidate - nums[i]);
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            try {
                candidates.add(Long.parseLong(candidate));
            } catch (NumberFormatException ex) {
                continue;
            }
        }
        return candidates;
    }

    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int[][] arr = new int[3][2];
        //left
        arr[0][1] = nums[0];
        //cur
        arr[1][1] = nums[0];
        //right
        arr[2][1] = nums[0];
        int curSum = 0;
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            arr[2][0] = i;
            arr[2][1] = nums[i];
            curSum = curSum + nums[i] - arr[1][1];
            if(curSum <= k){
                max = Math.max(max, arr[2][0] - arr[0][0]);
            }else {
                boolean isNotBalance = true;
                while (isNotBalance){
                    //新的起始下标
                    arr[0][0] = arr[0][0] + 1;
                    arr[0][1] = nums[arr[0][0]];
                    //寻找新的众数使curSum最小
                    curSum = 0;
                    for (int j = arr[0][0]; j <= arr[2][0]; j++){
                        curSum += Math.abs(arr[1][1] - nums[j]);
                    }
                    if(curSum <= k){
                        
                        continue;
                    }
                    while (curSum > k){

                        arr[1][1]++;

                    }
                }
            }

        }
        return -1;
    }

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for(int i = 0; i < m; i++){
            int left = 0, right = n - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if((mid == 0 || mat[i][mid] > mat[i][mid - 1]) && (mid == n - 1 || mat[i][mid] > mat[i][mid + 1])){
                    if((i == 0 || mat[i][mid] > mat[i - 1][mid]) && (i == m - 1 || mat[i][mid] > mat[i + 1][mid])){
                        return new int[]{i, mid};
                    }else{
                        break;
                    }
                }else if(mid != 0 && mat[i][mid] <= mat[i][mid - 1] ){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] mat = {{7,2,3,1,2}, {6,5,4,2,1}};
        Week20231217 week20231217 = new Week20231217();
        System.out.println(week20231217.findPeakGrid(mat));

    }
}

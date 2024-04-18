package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuHang on 2023/9/3.
 */
public class CountSymmetricIntegers7020 {

    public int minimumOperations(String num) {
        int last0 = -1;
        int success0 = -1;
        int last5 = -1;
        int success5 = -1;
        for (int i = num.length() - 1; i >= 0; i--){
            if(num.charAt(i) == '0' && last0 == -1){
                last0 = i;
                continue;
            }
            if(num.charAt(i) == '5' && last5 == -1){
                last5 = i;
                if(last0 != -1 && success0 == -1){
                    success0 = i;
                    break;
                }
                continue;
            }
            if(last0 != -1 && success0 == -1){
                if(num.charAt(i) == '0' || num.charAt(i) == '5'){
                    success0 = i;
                }
            }
            if(last5 != -1 && success5 == -1){
                if(num.charAt(i) == '2' || num.charAt(i) == '7'){
                    success5 = i;
                }
            }
            if(success0 != -1 && success5 != -1 ){
                break;
            }
        }
        int ans = num.length();
        if(last0 != -1){
            ans = Math.min(ans, num.length() - 1);
            if(success0 != -1){
                ans = Math.min(ans, num.length() - success0 - 2);
            }
        }
        if(last5 != -1 && success5 != -1){
            ans = Math.min(ans, num.length() - success5 - 2);
        }
        return ans;
    }

    public static void main(String[] args) {
        test3();
    }

    public static void test3(){
        List<Integer> nums  = Arrays.asList(3,1,9,6);
        int module = 1;
        int k = 0;
        CountSymmetricIntegers7020 countSymmetricIntegers7020 = new CountSymmetricIntegers7020();
        System.out.println(countSymmetricIntegers7020.countInterestingSubarrays(nums, module, k));
    }

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        //count[i + 1][j]表示以第i位为结尾的子数组，cnt 对 module的余数为j
        //若nums[i] % modulo == k, cnt[i + 1] = cnt[i] + 1, 那么 count[i + 1][j] = count[i][j - 1]， 所有余数都加1
        //若nums[i] % modulo != k, cnt[i + 1] = cnt[i], 那么count[i + 1][j] = count[i][j]
        long[][] count = new long[nums.size() + 1][modulo];
        long sum = 0;
        for(int i = 0; i < nums.size(); i++){
            if(nums.get(i) % modulo == k){
                //cnt[i + 1] = cnt[i] + 1
                //新增子数字，余数1
                for(int j = 0; j < modulo; j++){
                    count[i + 1][j] = count[i][(modulo + j - 1) % modulo] + (j == 1 || modulo == 1? 1: 0);
                }
            }else {
                //cnt[i + 1] = cnt[i]
                //新增子数字，余数0
                for(int j = 0; j < modulo; j++){
                    count[i + 1][j] = count[i][j] + (0 == j? 1: 0);
                }
            }
            sum += count[i + 1][k];
        }
        return sum;
    }

    public long countInterestingSubarrays2(List<Integer> nums, int modulo, int k) {
        long sum = 0;
        Map<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, 1);
        int preCnt = 0;
        for(int i = 0; i < nums.size(); i++){
            //新的满足nums[i] % modulo == k个数
            int cnt = (preCnt + ((nums.get(i) % modulo == k? 1: 0))) % modulo;
            sum += modMap.getOrDefault((cnt + modulo - k) % modulo, 0);
            modMap.put(cnt, modMap.getOrDefault(cnt, 0) + 1);
            preCnt = cnt;
        }
        return sum;
    }
}

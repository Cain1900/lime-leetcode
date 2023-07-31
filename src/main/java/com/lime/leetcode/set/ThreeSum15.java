package com.lime.leetcode.set;

import java.util.*;

/**
 * Created by YuHang on 2023/7/4.
 */
public class ThreeSum15 {

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ansList = new ArrayList<List<Integer>>();
        for(int i = 0; i < n; i++){
            if(i != 0 && nums[i - 1] == nums[i]){
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right){
                if(left != i + 1 && nums[left - 1] == nums[left]){
                    left++;
                    continue;
                }
                if(nums[left] + nums[right] + nums[i] > 0){
                    right--;
                }else if(nums[left] + nums[right] + nums[i] < 0){
                    left++;
                }else {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[left]);
                    item.add(nums[i]);
                    item.add(nums[right]);
                    ansList.add(item);
                    left++;
                }
            }
        }
        return ansList;
    }


    public static void main(String[] args) {
        //int[] nums = new int[]{-1,0,1,2,-1,-4};
        int[] nums = new int[]{0,0,0,0};
        ThreeSum15 threeSum15 = new ThreeSum15();
        System.out.println(threeSum15.threeSum(nums));
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>  ansList = new ArrayList<>();
        int left = 0, right = nums.length - 1;
        while (right - left > 1){
            int sum = nums[left] + nums[right];
            if(sum > 0){
                for (int i = left + 1; i < right; i++){
                    if(sum + nums[i] == 0){
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[left]);
                        item.add(nums[i]);
                        item.add(nums[right]);
                        ansList.add(item);
                        break;
                    }else if(sum + nums[i] > 0){
                        break;
                    }
                }
                right--;
                while (nums[right] == nums[right + 1] && right - left > 1){
                    right--;
                }
            }else if(sum < 0) {
                for (int i = right - 1; i > left; i--){
                    if(sum + nums[i] == 0){
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[left]);
                        item.add(nums[i]);
                        item.add(nums[right]);
                        ansList.add(item);
                        break;
                    }else if(sum + nums[i] < 0){
                        break;
                    }
                }
                left++;
                while (nums[left] == nums[left - 1] && right - left > 1){
                    left++;
                }
            }else {

            }
        }
        return ansList;
    }


    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        Set<String> itemSet = new HashSet<>();
        List<List<Integer>>  ansList = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                for(int k = j + 1; k < nums.length; k++){
                    if(nums[i] + nums[j] + nums[k] == 0 && !itemSet.contains(new StringBuilder().append(nums[i]).append(nums[j]).append(nums[k]).toString())){
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.add(nums[j]);
                        item.add(nums[k]);
                        ansList.add(item);
                        itemSet.add(new StringBuilder().append(nums[i]).append(nums[j]).append(nums[k]).toString());
                    }
                }
            }
        }
        return ansList;
    }
}

package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/8/20.
 */
public class LongestEqualSubarray6467 {

    public int longestEqualSubarray(List<Integer> nums, int k) {
        if(nums.size() <= 1){
            return nums.size();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.size(); i++){
            if(map.containsKey(nums.get(i))){
                map.get(nums.get(i)).add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums.get(i), list);
            }
        }
        int max = 1;
        for (Integer ints: map.keySet()){
            List<Integer> intList = map.get(ints);
            for (int i = 0; i < intList.size(); i++){
                for (int j = i + max; j < intList.size(); j++){
                    if(intList.get(j) - intList.get(i) - (j - i) <= k){
                        max = Math.max(max, (j -i + 1));
                    }else {
                        break;
                    }
                }
            }
        }
        return max;
    }

    public int longestEqualSubarray1(List<Integer> nums, int k) {
        //int[i + 1][j]表示结束位1时，删除j个元素的最长等值子数组
        if(nums.size() <= 1){
            return nums.size();
        }
        //[x1,x2,x3]含义 x1: 等值子数组长度，x2： 等值子数组最后一位下标，x3：删除元素个数
        Map<Integer, List<Integer[]>> map = new HashMap<>();
        int max = 1;
        for(int i = 0; i < nums.size(); i++) {
            Integer item = nums.get(i);
            if (map.containsKey(item)) {
                List<Integer[]> intList = map.get(item);
                List<Integer[]> updateList = new ArrayList<>();
                Iterator iterator = intList.iterator();
                while (iterator.hasNext()){
                    //x3：删除元素个数
                    Integer[] ints = (Integer[]) iterator.next();
                    int x3 = ints[2] + i - ints[1] - 1;
                    if (x3 > k) {
                        //删除元素超过k，s删除这个元素
                        iterator.remove();
                    } else {
                        //删除元素个数未超过k，进行补充
                        iterator.remove();
                        updateList.add(new Integer[]{ints[0] + 1, i, x3});
                        max = Math.max(max, ints[0] + 1);
                    }
                }
                intList.addAll(updateList);
                intList.add(new Integer[]{1, i, 0});
            } else {
                List<Integer[]> intList = new ArrayList<>();
                intList.add(new Integer[]{1, i, 0});
                map.put(item, intList);
            }
        }
        return max;
    }

    public int longestEqualSubarray2(List<Integer> nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int max = 0, freq = 0;
        for (int right = 0; right < nums.size(); right++) {
            int num = nums.get(right);
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            freq = Math.max(freq, countMap.get(num));
            // 如果窗口中需要删除的元素数量超过 k，移动左指针
            while (right - left + 1 > freq + k) {

                countMap.put(nums.get(left), countMap.get(nums.get(left)) - 1);
                left++;
            }
            max = Math.max(max, freq);
        }
        return max;
    }

    public static void main(String[] args) {
        //List<Integer> nums = new ArrayList<>(Arrays.asList(9,5,1,4,2,3,4,13,5,2,7,2,2,14));
        //List<Integer> nums = new ArrayList<>(Arrays.asList(1,3,2,3,1,3));
        List<Integer> nums = new ArrayList<>(Arrays.asList(10,1,10,9,1,5,2,3,8,7,7,9,10,7,4,2,10));
        int k = 0;
        LongestEqualSubarray6467 longestEqualSubarray6467 = new LongestEqualSubarray6467();
        System.out.println(longestEqualSubarray6467.longestEqualSubarray(nums, k));
    }
}

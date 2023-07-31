package com.lime.leetcode.set1000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuHang on 2023/4/23.
 */
public class LongestArithSeqLength1027 {

    public int longestArithSeqLength(int[] nums) {
        List<Map<Integer, Integer>> seqCountList = new ArrayList<>();
        //第一个元素
        seqCountList.add(new HashMap<>());
        int ans = 0;
        for (int i = 1; i < nums.length; i++){
            Map<Integer, Integer> seqCountMap = new HashMap<>();
            for (int j = 0; j < i; j++){
                int seq = nums[i] - nums[j];
                seqCountMap.put(seq, seqCountList.get(j).getOrDefault(seq, 1) + 1);
                ans = Math.max(ans, seqCountMap.get(seq));
            }
            seqCountList.add(seqCountMap);
        }
        return ans;
    }

    public int longestArithSeqLength2(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }


        int ans = 0;
        return ans;
    }

}

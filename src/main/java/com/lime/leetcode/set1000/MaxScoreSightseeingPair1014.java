package com.lime.leetcode.set1000;

/**
 * 功能描述
 * <p>
 * $
 *
 * @author yuh109
 * @return $
 * @date $ $
 */
public class MaxScoreSightseeingPair1014 {
    public int maxScoreSightseeingPair(int[] values) {
        int pre = values[0] - 1;
        int max = 0;
        for (int i = 1; i < values.length; i++){
            max = Math.max(max, pre + values[i]);
            pre = Math.max(pre - 1, values[i] - 1);
        }
        return max;
    }

}

package com.lime.leetcode.set3100;

/**
 * 功能描述
 * <p>
 * $
 *
 * @author yuh109
 * @return $
 * @date $ $
 */
public class CountCompleteDayPairs3185 {

    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        long[] count = new long[24];
        for (int i = 0; i < n; i++){
            count[hours[i] % 24]++;
        }
        long ans = 0;
        for (int i = 1; i < 12; i++){
            ans += count[i] * count[24 - i];
        }
        if(count[0] > 1){
            ans += count[0] * (count[0] - 1) / 2;
        }
        if(count[0] > 1){
            ans += count[12] * (count[12] - 1) / 2;
        }
        return ans;
    }
}

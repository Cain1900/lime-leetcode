package com.lime.leetcode.set2100;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2023/7/6.
 */
public class MaximumEvenSplit2178 {

    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 == 1){
            return ans;
        }
        Long temp = 2L;
        while (finalSum >= temp){
            ans.add(temp);
            finalSum -= temp;
            temp += 2;
        }
        ans.remove(ans.size() - 1);
        ans.add(temp - 2 + finalSum);
        return ans;
    }

}

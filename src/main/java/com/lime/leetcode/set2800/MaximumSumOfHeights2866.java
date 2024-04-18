package com.lime.leetcode.set2800;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by YuHang on 2023/12/21.
 */
public class MaximumSumOfHeights2866 {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        Stack<int[]> stack = new Stack<>();
        long[] left = new long[n + 1];
        for (int i = 0; i < n; i++){
            Integer height = maxHeights.get(i);
            while (!stack.isEmpty() && stack.peek()[0] >= height){
                stack.pop();
            }
            if(stack.isEmpty()){
                left[i + 1] = (long) height * (i + 1);
            }else {
                int pre = stack.peek()[1];
                left[i + 1] = left[pre + 1] + (long) height * (i - pre);
            }
            stack.add(new int[]{height, i});
        }
        stack = new Stack<>();
        long[] right = new long[n + 1];
        for (int i = n - 1; i >= 0; i--){
            Integer height = maxHeights.get(i);
            while (!stack.isEmpty() && stack.peek()[0] >= height){
                stack.pop();
            }
            if(stack.isEmpty()){
                right[i] = (long) height * (n - i);
            }else {
                int pre = stack.peek()[1];
                right[i] = right[pre] + (long) height * (pre - i);
            }
            stack.add(new int[]{height, i});
        }
        long max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, left[i] + right[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        List<Integer> maxHeights = Arrays.asList(314324228,526196638,971780775,141382951,44825730,92939243,869702460,692214717,396184442,271863091,452818943,124554145,194393992,813279621,476977123,291285997,195696382,80619001,296691434,24194433,834306546,337273583,612960339,252148987,498162770,641751698,580675254,66186200,192009966,590634046,590252844,510204257,235020771,606202644,338253570,224352005,183647397,867961176,521468453,365745792,508222499,360685429,851354307,177768509,955097078,227459453,644376561,467834249,594236609,319781404,648225233,524439197,532203513,463002246,498592686,691351312,208635346,155682966,294639403,341617283,604365123,79112831,22440031,809193898,675993946,99928197,644324211,170555722,218906830,782039120,686747235,356537885);
        MaximumSumOfHeights2866 maximumSumOfHeights2866 = new MaximumSumOfHeights2866();
        System.out.println(maximumSumOfHeights2866.maximumSumOfHeights(maxHeights));
    }
}

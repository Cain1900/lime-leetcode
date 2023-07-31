package com.lime.leetcode.set;

/**
 * Created by YuHang on 2023/7/4.
 */
public class MaxArea11 {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right){
            int h = height[left];
            if(height[right] > height[left]){
                left++;
            }else {
                h = height[right];
                right--;
            }
            max = Math.max(max, (right - left) * (height[right] > height[left] ? height[left++]: height[right--]));
        }
        return max;
    }

    public int maxArea2(int[] height) {
        //以i为右边组成最大容器时，左边下标
        int[] dpl = new int[height.length];
        int max = 0;
        int maxNum = height[0];
        for (int i = 1; i < height.length; i++){
            //统计所有数的最大值
            maxNum = Math.max(maxNum, height[i]);
            if(height[i - 1] == height[i]){
                //左边下标相同
                dpl[i] = dpl[i -1];
                //重新计算面积
                max = Math.max(max, (i - dpl[i]) *  Math.min(height[dpl[i]], height[i]));
            }else if(height[i - 1] < height[i]){
                //左边下标相同
                dpl[i] = dpl[i -1];
                //以左边下标开始，低位的最大值下标为dpl[i]
                //向高位遍历乘积
                for (int j = dpl[i]; j < i - max / maxNum; j++){
                    int sum = (i - j) * Math.min(height[i], height[j]);
                    if(sum >= max){
                        max = sum;
                        dpl[i] = j;
                    }
                }
            }else {
                //以左边下标开始，高位的最大值下标为dpl[i]
                //向低位遍历乘积
                dpl[i] = i -1;
                for (int j = dpl[i]; j >= 0; j--){
                    int sum = (i - j) * Math.min(height[i], height[j]);
                    if(sum >= max){
                        max = sum;
                        dpl[i] = j;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = new int[]{2,3,4,5,18,17,6};
        MaxArea11 maxArea11 = new MaxArea11();
        System.out.println(maxArea11.maxArea(height));
    }

    public int maxArea1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++){
            for (int j = 0; j < i; j++){
                max = Math.max(max, (i - j) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }
}

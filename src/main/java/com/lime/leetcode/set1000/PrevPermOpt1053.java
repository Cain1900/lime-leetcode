package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/4/3.
 */
public class PrevPermOpt1053 {

    public int[] prevPermOpt1(int[] arr) {
        if(arr.length <= 1){
            return arr;
        }
        for(int i = arr.length - 2; i >= 0; i--){
            //起始下标越大,越符合要求. 从高位遍历
            if(arr[i] > arr[i + 1]){
                //下标大于i的满足非递减时，没有可替换的；
                //第一次出现替换时，下标i打破非递减
                for(int j = arr.length - 1; j > i; j--){
                    //数据小于arr[i]
                    if(arr[i] > arr[j] && arr[j] != arr[j - 1]){
                        //数值相同，越小越符合要求
                        //数据不同，越大越符合要求
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        return arr;
                    }
                }
            }
        }
        return arr;
    }

    public int[] prevPermOpt2(int[] arr) {
        if(arr.length <= 1){
            return arr;
        }
        int left = 0, right = 0;
        for(int i = arr.length - 1; i >= left + 1; i--){
            // i: right
            for(int j = i - 1; j >= left; j--){
                // j: left
                if(arr[i] < arr[j] && ( right == 0 || j != left || arr[right] == arr[i] )){
                    // left == j 起始下标越大,越符合要求, 不做替换;
                    // left != i && arr[left] != arr[j]  起始下标相同时, 分两种情况:
                    //                     1、结束下标值相同, 下标值越小越符合要求, 不做替换;
                    //                     2、结束下标值不相同, 下标值越大越符合要求, 做替换;
                    left = j;
                    right = i;
                }
            }
        }
        if(left != right && right != 0 ){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        return arr;
    }

    public int[] prevPermOpt3(int[] arr) {
        if(arr.length <= 1){
            return arr;
        }
        int start = 0, end = 0;
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j] && (start != i || arr[end] != arr[j])){
                    // start != i 起始下标越大,越符合要求, 做替换;
                    // start == i && arr[end] != arr[j]  起始下标相同时, 分两种情况:
                    //                     1、结束下标值相同, 下标值越小越符合要求, 不做替换;
                    //                     2、结束下标值不相同, 下标值越大越符合要求, 做替换;
                    start = i;
                    end = j;
                }
            }
        }
        if(start != end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        return arr;
    }


    public static void main(String[] args) {
        PrevPermOpt1053 prevPermOpt1053 = new PrevPermOpt1053();
        int[] arr = new int[]{3,1,1,3};
        System.out.println(prevPermOpt1053.prevPermOpt2(arr));
    }
}

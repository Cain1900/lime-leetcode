package com.lime.leetcode.set3100;

public class WaysToReachStair3154 {


    public int waysToReachStair(int k) {
        if(k == 0){
            //(1, 0), 组合数1
            //(2, 1), 组合数1
            return 2;
        }else if(k == 1){
            //(0, 0), 组合数1
            //(1, 1), 组合数1
            //(3, 2), 组合数1
            return 4;
        }else if(k == 2){
            //(0, 1), 组合数1
            //(2, 2), 组合数3
            // AB AB
            // B AB A
            // AB B A
            return 4;
        }else if(k == 3){
            //(0, 1), 组合数1
            //(2, 2), 组合数3
            // AB AB
            // B AB A
            // AB B A
            return 3;
        }else if(k == 4){
            //(0, 2), 组合数1
            //(4, 3), 组合数1
            return 2;
        }
        //1 + 2^j - 1 - i = k
        //化简  2^j = i + k, 且 i <= j + 1 (第一种操作不能连续使用，但是最后可以额外多使用一次)
        //当k >= 3时， i，j只有一组解
        int temp = k;
        int sum = 1;
        int j = 0;
        while (temp > 0){
            j++;
            temp >>= 1;
            sum <<= 1;
        }
        if(sum == k * 2){
            sum /= 2;
        }
        int i = sum - k;
        if(i > j + 1){
            return 0;
        }
        int count = 0;
        //假设第一种操作为A， 第二种操作为B，那么可能出现的排列组合， B， AB， A（只可能是最后一个）
        if(i > 0){
            //最后一步执行第一种操作A，前面会有 i - 1 个 AB， j - i + 1个 B
            count += combination(j, Math.min(i - 1, j - i + 1));
        }
        if(j >= i){
            //也可以按照 i个AB， j-i 个B进行组合
            count += combination(j, Math.min(i, j - i));
        }
        return count;
    }



    // 计算n的阶乘
    public static long factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    // 计算n个元素中取k个的排列数
    public static long permutation(int n, int k) {
        return factorial(n) / factorial(n - k);
    }
    public static long combination01(int n, int k) {
        return factorial(n) / (factorial(n - k) * factorial(k));
    }

    public static long combination(int n, int k) {
        long ans = 1;
        long div = 1;
        for (int i = 0; i < k; i++){
            ans *= (((n - i) < 1)? 1: (n - i));
            div *= (i + 1);
        }
        return ans / div;
    }



    public int waysToReachStair01(int k) {
        return dp(1, k, 0);
    }

    public int dp(int cur, int k, int jump){
        if(cur > k + 2){
            return 0;
        }
        int ans = 0;
        if(cur == k){
            ans++;
        }
        if(cur > 0){
            //可以执行第一种操作
            if (cur - 1 == k){
                //第一种操作后到达目标
                ans++;
            }
            //再执行第二种操作
            ans += dp((int) (cur - 1 + Math.pow(2, jump)), k, jump + 1);
        }
        //直接执行第二种操作
        ans += dp((int) (cur + Math.pow(2, jump)), k, jump + 1);
        return ans;
    }

    public static void main(String[] args) {
        WaysToReachStair3154 waysToReachStair3154 = new WaysToReachStair3154();
        int k = 11;
        long factorial21 = factorial(21);
        long factorial20 = factorial(20);
        long combination21_20 = combination(21, 1);
        long combination21_21 = combination(21, 0);
        System.out.println(waysToReachStair3154.waysToReachStair(k));
    }
}

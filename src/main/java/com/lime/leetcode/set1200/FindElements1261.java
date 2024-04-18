package com.lime.leetcode.set1200;

import com.lime.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by YuHang on 2024/4/1.
 */
public class FindElements1261 {

    TreeNode root;

    FindElements1261(){}

    public FindElements1261(TreeNode root) {
        this.root = root;
    }

    public boolean find(int target) {
        Stack<Integer> stack = new Stack<>();
        while (target > 0){
            if(target % 2 == 0){
                stack.push(2);
                target = (target - 2) / 2;
            }else {
                stack.push(1);
                target = (target - 1) / 2;
            }
        }
        TreeNode cur  = root;
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            if(pop == 2 && cur.right != null){
                cur = cur.right;
            }else if(pop == 1 && cur.left != null) {
                cur = cur.left;
            }else {
                return false;
            }
        }
        return true;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode>[] array = new List[n + 1];
        for (int i = 0; i <= n; i++){
            array[i] = new ArrayList<>();
        }
        //n = 1
        array[1].add(new TreeNode(0));
        //n = 2
        for(int i = 3; i <= n; i++){
            for(int j = 1; i - 1 - j > 0; j++){
                for (TreeNode left: array[j]){
                    for (TreeNode right: array[i - 1 - j]){
                        array[i].add(new TreeNode(0, left, right));
                    }
                }
            }
        }
        return array[n];
    }

    public int minimumPossibleSum(int n, int target) {
        long mid = (target + 1)/ 2;
        if(mid >= n){
            long max = n;
            return (int)(((max + 1) * max / 2) % 1000000007 );
        }else {
            long max = n + target - (mid + 1);
            return (int)(((((mid + 1) * mid / 2) % 1000000007 ) + (((max + target) * (max - target + 1) / 2) % 1000000007 )) % 1000000007 ) ;
        }
    }

    public static void main(String[] args) {
        FindElements1261 findElements1261 = new FindElements1261();
        System.out.println(findElements1261.minimumPossibleSum(2, 3));
    }



}

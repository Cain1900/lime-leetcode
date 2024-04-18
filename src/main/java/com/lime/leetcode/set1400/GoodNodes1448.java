package com.lime.leetcode.set1400;

/**
 * Created by YuHang on 2023/8/25.
 */
public class GoodNodes1448 {




    public int goodNodes(TreeNode root) {
        return dp(root, Integer.MIN_VALUE);
    }

    private int dp(TreeNode node, int max) {
        if(node == null){
            return 0;
        }
        int res = max <= node.val? 1: 0;
        max = Math.max(max, node.val);
        res += dp(node.left, max);
        res += dp(node.right, max);
        return res;
    }

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){
        }

        TreeNode(int val){
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

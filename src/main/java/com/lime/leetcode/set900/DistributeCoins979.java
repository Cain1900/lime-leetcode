package com.lime.leetcode.set900;

/**
 * Created by YuHang on 2023/7/14.
 */
public class DistributeCoins979 {

    private int moveTotal = 0;

    public int distributeCoins(TreeNode root) {
        dp(root);
        return moveTotal;
    }

    public int dp(TreeNode root){
        if(root == null){
            return 0;
        }
        //root.val - 1 > 0 时，该节点作为子节点需要转给父节点硬币
        int leftNeed = dp(root.left);
        int rightNeed = dp(root.right);
        moveTotal += Math.abs(leftNeed) + Math.abs(rightNeed);
        //当前节点拥有的 root.val
        //leftNeed、rightNeed表示子节点转移给自己的
        //root.val - 1 + leftNeed + rightNeed 本节点多的硬币，需要转给父节点
        return root.val - 1 + leftNeed + rightNeed;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(0, null, node1);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(1, node2, node3);
        DistributeCoins979 distributeCoins979 = new DistributeCoins979();
        System.out.println(distributeCoins979.distributeCoins(node4));
    }







    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

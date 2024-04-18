package com.lime.leetcode.set2600;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by YuHang on 2024/2/7.
 */
public class ReplaceValueInTree2641 {

    public TreeNode replaceValueInTree(TreeNode root) {
        TreeNode node = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        node.val = 0;
        while (!queue.isEmpty()){
            int sum = 0;
            Queue<TreeNode> nextGenerate = new ArrayDeque<>();
            for (TreeNode treeNode: queue){
                if(treeNode.left != null){
                    nextGenerate.offer(treeNode.left);
                    sum += treeNode.left.val;
                }
                if(treeNode.right != null){
                    nextGenerate.offer(treeNode.right);
                    sum += treeNode.right.val;
                }
            }
            for (TreeNode treeNode : queue) {
                int childSum = (treeNode.left != null ? treeNode.left.val : 0) +
                        (treeNode.right != null ? treeNode.right.val : 0);
                if (treeNode.left != null) {
                    treeNode.left.val = sum - childSum;
                }
                if (treeNode.right != null) {
                    treeNode.right.val = sum - childSum;
                }
            }
            queue = nextGenerate;
        }
        return root;
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

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

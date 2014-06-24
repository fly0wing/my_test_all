package com.dudo.dailytest;

/**
 * User: zk
 * Date: 13-8-23
 * Time: 下午2:04
 */

import static com.dudo.dailytest.BTsort.BinarySerch;
import static com.dudo.dailytest.BTsort.BinarySort;
import static com.dudo.dailytest.BTsort.inOrder;

/**
 * 桶排序：桶排序的思想是把区间[0,1)划分成n个相同大小的子区间，称为桶，然后将n个输入数分布到各个桶中去。
 * 因为输入数均匀且独立分布在[0,1)上，所以，一般不会有很多数落在一个桶中的情况。
 * 为了得到结果，先对各个桶中的数进行排序，然后按次序把各桶中的元素列出来。
 * 桶排序的时间复杂度为O(n)
 */
public class T1 {
    public static void main(String[] args) {
        //自定义一个整型的数组;
        int[] array = {19, 12, 3, 22, 6, 7, 21, 11, 43};

        //创建根节点;

        Node root = new Node(array[0]);

        //依次将数组中的元素插入
        for (int i = 1; i < array.length; i++) {
            BinarySort(root, array[i]);
        }

        //查找指定的元素;
        if(BinarySerch(root, 12)) {
            System.out.println( "二叉树中存在此元素." );
        } else {
            System.out.println( "二叉树中不存在该元素." );
        }

        //遍历指定的二叉树并输出--采用中序遍历法;
        inOrder(root);
    }
}

class Node {
    private Node left;
    private int key;
    private Node right;

    public Node(int i) {
        this.key=i;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public int getKey() {
        return key;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void visitNode() {
        System.out.println(this.key);
    }
}

class BTsort {

    public static void main(String[] args) {



    }

    /**
     * 将指定的元素插入二叉排序树中
     *
     * @param root
     * @param key
     */
    public static void BinarySort(Node root, int key) {

        //得到根节点中的元素;
        int value = root.getKey();
        //判断该插入左子树还是右子树;
        if (key < value) {//插入柚子树
            if (root.getLeft() == null) {
                Node node = new Node(key);
                root.setLeft(node);
            } else {
                BinarySort(root.getLeft(), key);
            }

        } else if (key > value) {
            if (root.getRight() == null) {
                Node node = new Node(key);
                root.setRight(node);
            } else {
                BinarySort(root.getRight(), key);
            }
        }
    }

    /**
     * 二叉树搜索树;
     *
     * @param root
     * @param key
     */
    public static boolean BinarySerch(Node root, int key) {
        if (root == null) {
            return false;
        } else if (root.getKey() == key) {
            return true;
        } else if (root.getKey() > key) {
            return BinarySerch(root.getLeft(), key);
        } else {
            return BinarySerch(root.getRight(), key);
        }
    }

    /**
     * 采用中序遍历法遍历一个二叉树
     *
     * @param root
     */
    public static void inOrder(Node root) {
        if (root != null) {
            inOrder(root.getLeft());
            root.visitNode();
            inOrder(root.getRight());
        }
    }

}
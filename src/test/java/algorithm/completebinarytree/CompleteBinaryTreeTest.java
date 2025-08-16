package algorithm.completebinarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompleteBinaryTreeTest {

    @Test
    void test() {
        CompleteBinaryTree completeBinaryTree = new CompleteBinaryTree();
        completeBinaryTree.insertNode('A');
        completeBinaryTree.insertNode('B');
        completeBinaryTree.insertNode('C');
        completeBinaryTree.insertNode('D');
        completeBinaryTree.insertNode('E');
        completeBinaryTree.insertNode('F');
        completeBinaryTree.preOrder(completeBinaryTree.getRootNode());
    }
}
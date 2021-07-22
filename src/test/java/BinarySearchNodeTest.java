import algorithm.binarysearchtree.BinarySearchTree;
import algorithm.binarysearchtree.Node;

public class BinarySearchNodeTest {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insertNode(100);
        bst.insertNode(50);
        bst.insertNode(200);
        bst.insertNode(300);
        bst.insertNode(250);

        bst.insertNode(350);

        bst.insertNode(40);

        bst.insertNode(55);

        bst.insertNode(30);

        bst.insertNode(35);

        bst.insertNode(56);

        bst.insertNode(54);

        bst.insertNode(53);

        bst.insertNode(52);

        bst.insertNode(57);

        //bst.inOrder(bst.getRootNode());

        Node fineNode1 = bst.searchNode(4);
        if (fineNode1 != null) {
            System.out.println("Find data");
            System.out.println("Value is " + fineNode1.getData());
        }

        bst.removeNode(50);

        bst.inOrder(bst.getRootNode());
    }
}

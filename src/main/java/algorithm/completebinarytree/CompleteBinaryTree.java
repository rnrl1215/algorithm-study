package algorithm.completebinarytree;

import java.util.*;

public class CompleteBinaryTree {
    private Set<Integer> learnedSkills = new HashSet<>();
    private Map<String, String> a = new TreeMap<>();
    private List<Integer> a1 = new ArrayList<>();

    public void test() {

    }

    class Node {
        public Node(char data) {
            this.data = data;
        }

        private char data;
        private Node leftNode;
        private Node RightNode;
    }

    private Node rootNode;
    private Queue<Node> queue = new LinkedList<>();
    public void insertNode(char data) {
        if(rootNode == null) {
            rootNode = new Node(data);
            queue.add(rootNode);
            return;
        }

        while (true) {
            Node tmpNode = queue.peek();
            if(tmpNode.leftNode == null) {
                tmpNode.leftNode = new Node(data);
                queue.add(tmpNode.leftNode );
                break;
            } else if(tmpNode.RightNode == null) {
                tmpNode.RightNode = new Node(data);
                queue.add(tmpNode.RightNode);
                queue.remove();
                break;
            } else {
                queue.remove();
            }
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void preOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.leftNode);
        preOrder(node.RightNode);
    }

    public static void main(String[] args) {
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

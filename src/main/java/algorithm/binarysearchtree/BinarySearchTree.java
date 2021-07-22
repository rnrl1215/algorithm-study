package algorithm.binarysearchtree;

public class BinarySearchTree {
    private Node rootNode;

    public void BinarySearchTree() {
        rootNode = null;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void insertNode(int data) {
        if(rootNode == null) {
            rootNode = new Node(data);
            return;
        }

        Node head = rootNode;
        Node parentNode = rootNode;
        boolean isLeftLocation = false;
        while (head != null) {
            parentNode = head;
            if (head.getData() < data) {
                head = head.right;
                isLeftLocation = false;
            } else {
                head = head.left;
                isLeftLocation = true;
            }
        }

        if (isLeftLocation) {
            parentNode.left = new Node(data);
        } else {
            parentNode.right = new Node(data);
        }
    }

    public Node searchNode(int data) {
        if (rootNode == null) {
            System.out.println("Can not find node");
            return null;
        }

        Node head = rootNode;
        Node parent = null;
        while(head.getData() != data) {
            if (head.getData() < data) {
                head = head.right;
            } else {
                head = head.left;
            }

            if (head == null) {
                System.out.println("Can not find node");
                break;
            }
        }

        return head;
    }

    public boolean removeNode(int data) {

        if (rootNode == null) {
            System.out.println("Can not find node");
            return false;
        }

        Node head = rootNode;
        Node parent = null;
        boolean isLeftLocation = false;
        while(head.getData() != data) {
            parent = head;
            if (head.getData() < data) {
                head = head.right;
                isLeftLocation = false;
            } else {
                head = head.left;
                isLeftLocation = true;
            }

            if (head == null) {
                System.out.println("Can not find node");
                break;
            }
        }

        if (head == null) {
            return false;
        }

        Node targetNode = head;
        Node replaceNode = null;
        if (targetNode.left == null && targetNode.right == null) {
            if (isLeftLocation) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (targetNode.left == null) {
            replaceNode = targetNode.right;
            if (isLeftLocation) {
                parent.left = replaceNode;
            } else {
                parent.right = replaceNode;
            }
        } else if (targetNode.right == null) {
            replaceNode = targetNode.left;
            if (isLeftLocation) {
                parent.left = replaceNode;
            } else {
                parent.right = replaceNode;
            }
        } else {
            Node successor = getSuccessor(targetNode, false);
            targetNode.setData(successor.getData());
        }

        return true;
    }

    public Node getSuccessor(Node node, boolean findMode) {
        if (node == null)
        {
            System.out.println("Node is NULL");
            return node;
        }

        if (node.right == null) {
            System.out.println("Cannot find successor");
            return node;
        }

        Node successorNode = node.right;
        Node parentNode = node;
        while (successorNode.left != null) {
            parentNode = successorNode;
            successorNode = successorNode.left;
        }

        if (!findMode) {
            parentNode.left = successorNode.right;
        }
        return successorNode;
    }


    public Node getPredecessor(Node node, boolean findMode)  {
        if (node == null)
        {
            System.out.println("Node is NULL");
            return node;
        }

        if (node.left == null) {
            System.out.println("Cannot find predecessor");
            return node;
        }

        Node predecessorNode = node.left;
        Node parentNode = node;
        while (predecessorNode.right != null) {
            parentNode = predecessorNode;
            predecessorNode = predecessorNode.right;
        }

        if (!findMode) {
            parentNode.right = predecessorNode.left;
        }
        return predecessorNode;
    }

    public void preOrder(Node node)
    {
        if (node == null) {
            return;
        }

        System.out.println(node.getData());
        preOrder(node.left);
        preOrder(node.right);
    }


    public void inOrder(Node node)
    {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.getData());
        inOrder(node.right);
    }


    public void postOrder(Node node)
    {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.getData());
    }
}

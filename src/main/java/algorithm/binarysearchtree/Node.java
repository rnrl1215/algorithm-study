package algorithm.binarysearchtree;

public class Node {
    private int data;
    public Node left = null;
    public Node right = null;


    public Node(){
        this.data = -1;
    }

    public Node(int data){
        this.data = data;
    }
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}


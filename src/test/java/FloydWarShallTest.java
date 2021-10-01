import algorithm.floydwarshall.FloydWarShall;

public class FloydWarShallTest {
    public static void main(String[] args) {

        FloydWarShall floydWarshall = new FloydWarShall(5);
        floydWarshall.addNode(0,1,2);
        floydWarshall.addNode(0,2,10);
        floydWarshall.addNode(1,3,3);
        floydWarshall.addNode(2,3,4);
        floydWarshall.addNode(2,4,2);
        floydWarshall.addNode(3,4,1);
        floydWarshall.addNode(4,1,1);


        floydWarshall.runFloydWarshall();
        System.out.println("Node: 0");
        floydWarshall.printPath(0,4);
        System.out.println("Node: 4");
    }
}

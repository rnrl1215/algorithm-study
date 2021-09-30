import algorithm.bellmanford.BellmanFord;

public class BellmanFordTest {
    public static void main(String[] args) {

        try {
            BellmanFord bellmanFord = new BellmanFord(5,0);

            bellmanFord.addEdge(0, 1, 6);
            bellmanFord.addEdge(0, 2, 7);
            bellmanFord.addEdge(1, 3, 5);
            bellmanFord.addEdge(1, 2, 8);
            bellmanFord.addEdge(1, 4, -4);
            bellmanFord.addEdge(2, 3, -3);
            bellmanFord.addEdge(2, 4, 9);
            bellmanFord.addEdge(3, 1, -2);
            bellmanFord.addEdge(4, 3, 7);
            bellmanFord.addEdge(4, 0, 2);


            if (bellmanFord.runBellmanFord()) {
                System.out.println("SUCCESS");
                bellmanFord.printDistance();
            } else {
                System.out.println("음수싸이클 발생");
                bellmanFord.printDistance();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

import algorithm.dijkstra.Dijkstra;

public class DijkstraTest {
    public static void main(String[] args) {

        try {

            Dijkstra dijkstra = new Dijkstra(5);
            dijkstra.addNode(0, 1, 10);
            dijkstra.addNode(0, 2, 5);
            dijkstra.addNode(1, 2, 3);
            dijkstra.addNode(1, 3, 1);
            dijkstra.addNode(2, 1, 2);
            dijkstra.addNode(2, 3, 9);
            dijkstra.addNode(2, 4, 2);
            dijkstra.addNode(3, 4, 4);
            dijkstra.addNode(4, 3, 6);

            dijkstra.runDijkstra(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

import algorithm.kruskal.Kruskal;

public class KurskalTest {
    public static void main(String[] args) {
        // write your code her
        Kruskal kruskal = new Kruskal();

        kruskal.setNode(0, 1, 4);
        kruskal.setNode(0, 7, 8);
        kruskal.setNode(1, 2, 8);
        kruskal.setNode(1, 7, 11);
        kruskal.setNode(2, 3, 7);
        kruskal.setNode(2, 8, 2);
        kruskal.setNode(2, 5, 4);
        kruskal.setNode(3, 5, 14);
        kruskal.setNode(3, 4, 9);
        kruskal.setNode(4, 5, 10);
        kruskal.setNode(5, 6, 2);
        kruskal.setNode(6, 8, 6);
        kruskal.setNode(6, 8, 6);
        kruskal.setNode(6, 7, 1);
        kruskal.setNode(7, 8, 7);
        kruskal.runKruskal(8);
    }
}

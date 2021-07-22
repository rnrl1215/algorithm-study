import algorithm.graph.ListGraphExample;

public class GraphTest {
    public static void main(String[] args) {
        // write your code here
        int graphSize = 5;
        ListGraphExample listGraph = new ListGraphExample(graphSize);
        listGraph.addVertex(1,2);
        listGraph.addVertex(1,3);
        listGraph.addVertex(2,5);
        listGraph.addVertex(2,4);
        listGraph.addVertex(3,4);
        listGraph.addVertex(4,5);
        listGraph.printGraph();

        listGraph.graphTraversalBFS(listGraph.getGraph(),1);
        listGraph.graphTraversalDFS(listGraph.getGraph(),1);
    }
}

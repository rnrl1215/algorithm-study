import algorithm.dag.DagGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DagGraphTest {
    public static void main(String[] args) {

        DagGraph daggraph = new DagGraph();
        int graphSize = 7;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        daggraph.init(graph, graphSize);

        daggraph.addVertex(graph, 1,4);
        daggraph.addVertex(graph,1,3);
        daggraph.addVertex(graph,2,3);
        daggraph.addVertex(graph,3,5);
        daggraph.addVertex(graph,4,5);
        daggraph.addVertex(graph,4,6);
        daggraph.addVertex(graph,5,6);
        daggraph.addVertex(graph,5,7);




        // daggraph.printGraph(graph);

        Deque<Integer> queue = new ArrayDeque<>();
        daggraph.topologicalSort2(graph, queue);

        while(!queue.isEmpty()) {
            System.out.print(queue.pollFirst());
            if(queue.size() != 0) {
                System.out.print("->");
            }
        }
    }
}

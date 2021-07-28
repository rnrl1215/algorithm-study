import algorithm.dag.DagGraph;

import java.util.ArrayList;

public class DagGraphTest {
    public static void main(String[] args) {

        DagGraph daggraph = new DagGraph();
        int graphSize = 7;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        daggraph.init(graph, graphSize);

        daggraph.addVertex(graph, 1,2);
        daggraph.addVertex(graph,1,4);
        daggraph.addVertex(graph,2,5);
        daggraph.addVertex(graph,2,3);
        daggraph.addVertex(graph,4,5);
        daggraph.addVertex(graph,5,3);
        daggraph.addVertex(graph,5,6);
        daggraph.addVertex(graph,7,4);


       // daggraph.printGraph(graph);

        ArrayList<Integer> itemList = new ArrayList<>();
        daggraph.topologicalSort2(graph, itemList);

        for(int i = 0; i < itemList.size(); i++) {
            System.out.print(itemList.get(i)+"->");
        }
    }
}

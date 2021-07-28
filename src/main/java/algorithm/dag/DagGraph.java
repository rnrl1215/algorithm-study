package algorithm.dag;

import java.util.ArrayList;

public class DagGraph {
    private boolean []visit;

    public void dfsts(int v, ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> list)
    {

        visit[v] = true;
        list.add(v);
        for(int i = 0; i < graph.get(v).size(); i++)
        {
            if (visit[graph.get(v).get(i)] == false) {
                System.out.println(graph.get(v).get(i));
                dfsts(graph.get(v).get(i), graph, list);
            }
        }
    }

    public void topologicalSort2(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> list )
    {
        for (int i = 1; i < graph.size(); i++)
        {
            visit[i] = false;
        }

        ArrayList<Integer> mList;
        for(int i = 1; i < graph.size(); i++) {
            if (visit[i] == false) {
                dfsts(i, graph, list);
            }
        }
    }

    public void printGraph(ArrayList<ArrayList<Integer>> graph) {
        for (int i = 1; i < graph.size(); i++) {
            System.out.print(i);
            for (int j = 0; j <graph.get(i).size(); j++) {
                System.out.print(" -> " + graph.get(i).get(j));
            }
            System.out.println("");
        }
    }

    public void addVertex(ArrayList<ArrayList<Integer>> graph, int v1, int v2)
    {
        graph.get(v1).add(v2);
    }

    public void init(ArrayList<ArrayList<Integer>> graph, int size) {
        visit = new boolean[size + 1];
        for (int i = 0; i < size + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
    }
}

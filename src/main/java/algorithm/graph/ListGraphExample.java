package algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ListGraphExample {
    private ArrayList<ArrayList<Integer>> mListGraph = new ArrayList<ArrayList<Integer>>();;
    private ArrayList<Integer> mVisited = new ArrayList<Integer>();

    public ListGraphExample(int graphSize)
    {
        for(int i = 0; i < graphSize+1; i++) {
            mListGraph.add(new ArrayList<Integer>());
        }
    }

    public void addVertex(int v1, int v2)
    {
        mListGraph.get(v1).add(v2);
        mListGraph.get(v2).add(v1);
    }

    public void addDirectVertex(int v1, int v2)
    {
        mListGraph.get(v1).add(v2);
    }

    public ArrayList<Integer> getVertex(int v1) {
        return mListGraph.get(v1);
    }

    public  ArrayList<ArrayList<Integer>> getGraph() {
        return mListGraph;
    }

    public void printGraph() {
        for (int i = 1; i < mListGraph.size(); i++) {
            System.out.print(i);
            for (int j = 0; j <mListGraph.get(i).size(); j++) {
                System.out.print(" -> " + mListGraph.get(i).get(j));
            }
            System.out.println("");
        }
    }


    public void graphTraversalBFS(ArrayList<ArrayList<Integer>> graph, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty()) {
            int node = queue.poll();

            if(!mVisited.contains(node)) {
                mVisited.add(node);
                System.out.println(node);
            }

            for(int i = 0; i < graph.get(node).size(); i++) {
                if(!mVisited.contains(graph.get(node).get(i))) {
                    queue.add(graph.get(node).get(i));
                }
            }

        }
    }

    public void DFS(ArrayList<ArrayList<Integer>> graph, int node) {
        mVisited.add(node);
        System.out.println(node);
        for(int i = 0; i < graph.get(node).size(); i++) {
            if(!mVisited.contains(graph.get(node).get(i))) {
                DFS(graph, graph.get(node).get(i));
            }
        }
    }

    public void graphTraversalDFS(ArrayList<ArrayList<Integer>> graph, int node) {
        DFS(graph, node);
        
        for( int i = 1; i < graph.size(); i++ ) {
            if(!mVisited.contains(i)) {
                DFS(graph, i);
            }
        }
    }

    public void clearGraph() {
        mListGraph.clear();
    }
    public void clearCheckVisit() {
        mVisited.clear();
    }



    public static void test2() {
        int graphSize = 4;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            graph.add(new ArrayList<>());
        }


        graph.get(1).add(2);
        graph.get(2).add(1);

        graph.get(2).add(4);
        graph.get(4).add(1);
    }


    int [][] graph = new int[4+1][4+1];

    void init() {
        graph[1][2] = 1;
        graph[2][1] = 1;

        graph[1][3] = 1;
        graph[3][1] = 1;

        graph[2][4] = 1;
        graph[4][2] = 1;
        boolean [] visited = new boolean[graph.length];
        dfs(graph, visited, 1);
    }

    void dfs(int [][] graph, boolean []visited, int node) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < graph.length; i++) {
            if(graph[node][i] == 1 && visited[i] == false) {
                dfs(graph, visited, i);
            }
        }
    }
}



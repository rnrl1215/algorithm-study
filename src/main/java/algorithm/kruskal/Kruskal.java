package algorithm.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;

        @Override
        public int compareTo(Edge n) {
            return this.weight - n.weight;
        }

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }


    public List<Edge> nodes = new ArrayList<Edge>();

    private int []parent;
    private int []nodeSize;

    public void setNode(int u, int v, int weight) {
        Edge edge = new Edge(u, v, weight);
        nodes.add(edge);
    }

    public boolean isCycle(int u, int v) {
        int xParent = findSet(u);
        int yParent = findSet(v);

        boolean ret;
        ret = xParent == yParent;

        return ret;
    }

    public int findSet(int node) {

        while (node != parent[node]) {
            parent[node] = parent[parent[node]];
            node = parent[node];
        }

        return node;
    }

    public void weightUnion(int u, int v) {
        int uParent = findSet(u);
        int vParent = findSet(v);

        if (nodeSize[uParent] <= nodeSize[vParent]) {
            parent[vParent] = uParent;
            nodeSize[vParent] = nodeSize[vParent]+nodeSize[uParent];
        }
    }

    public int runKruskal(int size) {
        Collections.sort(nodes);
        parent = new int[size+1];
        nodeSize = new int[size+1];
        for(int i = 0; i <size+1; i++) {
            parent[i] = i;
            nodeSize[i] =1;
        }

        int sum = 0;
        for(Edge node : nodes) {
            if(!isCycle(node.u, node.v)) {
                weightUnion(node.u,node.v);
                System.out.println("U:"+node.u+" V:"+node.v+ " weight:" +node.weight);
                sum += node.weight;
            }
        }

        System.out.println(sum);
        return 1;
    }
}

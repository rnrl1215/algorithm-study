package algorithm.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    private List<List<Node>> graph = new ArrayList<>();
    private int []dist;
    private boolean []visit;
    private int maxValue = 987654321;
    private int nodeCount;

    public Dijkstra(int count) {
        nodeCount = count;
        dist = new int[count];
        visit = new boolean[count];
        for(int i = 0; i < count; i++) {
            dist[i] = maxValue;
            visit[i] = false;
            graph.add(new ArrayList<>());
        }

   }

    public void addNode(int u,int v,int w) {
        graph.get(u).add(new Node(v, w));
    }

    public int findMinDistance() {
        int minValue = this.maxValue;
        int minNodeIndex = 0;
        for (int i = 0; i < dist.length; i++) {
            if (!visit[i]) {
                if (minValue >= dist[i]) {
                    minNodeIndex = i;
                    minValue = dist[i];
                }
            }
        }

        return minNodeIndex;
    }

    public void runDijkstra(int start) {
        dist[start] = 0;
        int visitCount = 0;
        while (visitCount < nodeCount) {
            int currentNode = findMinDistance();
            if(!visit[currentNode]) {
                visit[currentNode] = true;
                for(Node node : graph.get(currentNode)) {
                    relax(currentNode, node.index, node.weight);
                }
            }
        }
    }

    public void relax(int u, int v, int w) {       // 노드에 대해 relax 연산수행
        if(dist[u] == maxValue) {                  // 방문안한 노드는 무시
            return;
        }
        if(dist[v] > dist[u]+w) {                  // delta 값 업데이트
            dist[v] = dist[u]+w;
        }
    }

    class Node {
        private int index;
        private int weight;
        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
}

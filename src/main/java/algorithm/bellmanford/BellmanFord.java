package algorithm.bellmanford;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

    List<List<Edge>> graph = new ArrayList<>();
    int []dist;
    int maxValue = 987654321;

    static class Edge {
        int u;    // 시작 노드
        int v;    // 끝 노드
        int w;    // 가중치

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public BellmanFord(int count, int start) {
        dist = new int[count];
        for(int i = 0; i <= count; i++) {            // 주어진 노드 수만큼 graph 초기화
            graph.add(new ArrayList<Edge>());
        }

        dist[start] = 0;
        for(int i = 1; i < count; i++) {
            dist[i] = maxValue;                     // 최대값으로 각노드의 거리 초기화
        }
    }

    public void addEdge(int u, int v, int w) {
        graph.get(u).add(new Edge(u,v,w));          // 단방향 그래프 생성
    }

    public void relax(int u, int v, int w) {       // 노드에 대해 relax 연산수행
        if(dist[u] == maxValue) {                  // 방문안한 노드는 무시
            return;
        }
        if(dist[v] > dist[u]+w) {                  // delta 값 업데이트
            dist[v] = dist[u]+w;
        }
    }

    public boolean hasMinusCycle() {              // 마이너스 순환 싸이클이 있는지 체크
        boolean ret = true;
        for(int j = 0; j < graph.size(); j++) {
            for (Edge edge : graph.get(j)) {
                if (dist[edge.v] > dist[edge.u] + edge.w) {
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }

    public boolean runBellmanFord() {

        for (int i = 0; i < graph.size() - 1; i++) {
            for (int j = 0; j < graph.size(); j++) {
                for (Edge edge : graph.get(j)) {
                    relax(edge.u, edge.v, edge.w);
                }
            }
        }

        boolean ret = true;
        ret = hasMinusCycle();
        return ret;
    }

    public void printDistance() {
        for(int i = 0; i <dist.length; i++) {
            System.out.println("delta of node("+i+") is "+dist[i]);
        }
    }
}

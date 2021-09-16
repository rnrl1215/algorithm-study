package algorithm.prim;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Prim {
    private ArrayList<ArrayList<Edge>> graph; // 그래프 를 저장하기 위해 List
    private ArrayList<Edge> primMST;          // MST 의 노드를 저장하기 위한 List
    boolean []visit;                          // 방문을 했는지 체크하기 위한 리스트


    public Prim(int count) {
        graph = new ArrayList<ArrayList<Edge>>();    // 그래프 초기화
        primMST = new ArrayList<>();                 // MST 노드를 저장할 List 초기화
        visit = new boolean[count+1];                // visit 초기화
        for(int i = 0; i <= count; i++) {            // 주어진 노드 수만큼 graph 초기화
            graph.add(new ArrayList<Edge>());
        }
    }

    class Edge implements Comparable<Edge> {
        private int u;                              // 시작노드
        private int v;                              // 종료노드
        private int w;                              // 가중치

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w > o.w ? 1 : -1;          // 우선순위 큐에 넣을때
        }                                          // 최소값을 기준으로 정렬하기 위한 비교함수
    }

    public void addEdge(int u,int v,int w) {
        graph.get(u).add(new Edge(u,v,w));         // 양방향으로 추가
        graph.get(v).add(new Edge(v,u,w));         // 양방향으로 추가
    }


    public void runPrim(int startPoint) {

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();    // 현재 노드를 기준으로 연결된 노드를 우선순위 큐에 저장할 용도
        Deque<Integer> queue = new LinkedList<>();                    // 탐색을 위한 queue

        queue.add(startPoint);                                        // 시작점 을 넣어준다.

        while (!queue.isEmpty()) {                                    // 큐가 비워질때까지 반복
            int current = queue.poll();                               // 큐에 있는 노드 번호를 가져온다.
            visit[current] = true;                                    // 방문했다고 표시
            for (Edge edge : graph.get(current)) {                    // 인접한 노드들 중 최소 가중치를 가지는 노드를 찾기 위해 우선순위 큐에 넣는다.
                if (!visit[edge.v]) {
                    priorityQueue.add(edge);
                }
            }

            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();                     // 가장 작은 노드를 꺼낸다.
                if (!visit[edge.v]) {                                 // 방문했는지 체크
                    visit[edge.v] = true;                             // 방문했다고 표기
                    primMST.add(edge);                                // 현재 선택된 노드를 MST 에 넣어준다.
                    queue.add(edge.v);                                // 노드의 번호를 넣어준다. 해당 노드를 기준으로 탐색
                    break;
                }
            }
        }
    }

    public void printNode() {
        for(int i = 0; i < primMST.size(); i++) {
            System.out.println(primMST.get(i).v);
        }
    }
}

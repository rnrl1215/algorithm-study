package algorithm.floydwarshall;

public class FloydWarShall {
    static final int INF = 987653421;
    private int [][]graph;
    private int [][]pi;

    private int count;

    public FloydWarShall(int count) {
        this.count = count;
        graph = new int[count][count];
        pi = new int[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                graph[i][j] = INF;
                pi[i][j] = INF;
            }
        }
    }

    public void addNode(int u, int v, int w) {
        graph[u][v] = graph[v][u] = w;
    }

    public void runFloydWarshall() {
        for (int k = 0; k < count; k++) {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        pi[i][j] = k;
                    }
                }
            }
        }
    }

    public void printPath(int s, int t) {
        if(pi[s][t] == INF) {
            return;
        }

        printPath(s,pi[s][t]);
        System.out.println("Node: "+pi[s][t]);
        printPath(pi[s][t],t);
    }

}

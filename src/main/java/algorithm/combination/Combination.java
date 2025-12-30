package algorithm.combination;

import java.util.Arrays;

public class Combination {

    static int[] result; // 2개를 뽑아서 담을 통
    static boolean[] visited;

    public void solve(int []arr, int start, int r) {
        result = new int[r];
        visited = new boolean[arr.length];
        combi(arr, start, r);
    }

    public void combi(int []arr, int start, int r) {
        if (r == 0) {
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
            return;
        }

        for(int i = start; i < arr.length; i++) {
            visited[i] = true;
            combi(arr, i + 1, r - 1);
            visited[i] = false;
        }
    }
}

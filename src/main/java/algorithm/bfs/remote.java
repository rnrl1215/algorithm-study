package algorithm.bfs;

import java.util.*;
public class remote {


    public static int solve(int start, int target) {
        int[] moves = {1, -1, 5, -5, 7, -7};
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{start,0});

        while (!queue.isEmpty()) {
            int [] current =  queue.poll();
            int value = current[0];
            int depth = current[1];

            if (value == target) {
                return depth;
            }

            for (int move : moves) {
                int next = value + move;
                if (!visited.contains(next)) {
                    queue.add(new int[]{next, depth+1});
                }
            }
        }
        return -1;
    }
}

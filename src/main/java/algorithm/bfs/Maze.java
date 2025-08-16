package algorithm.bfs;

public class Maze {
    private static final int[][] maze = {
            {0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 1, 3}
    };

    private static final int N = maze.length;
    private static final int M = maze[0].length;
    private static final int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    private static final int[] dy = {0, 0, -1, 1};


    public static boolean solveMaze(int x, int y) {

        if (x < 0 || x >= N || y < 0 || y >= M || maze[x][y] == 1 || maze[x][y] == 2) {
            return false;
        }

        if(maze[x][y] == 3) {
            maze[x][y] = 2;
            return true;
        }

        maze[x][y] = 2; // 기록
        for(int i = 0; i < 4; i++) {
            boolean b = solveMaze(x + dx[i], y + dy[i]);
            if (b) {
                return true;
            }
        }
        maze[x][y] = 0; // 백
        return false;
    }


    public static int totalDepth = 0;
    public static int solveMazeWithShortLength(int x, int y, int depth) {
        if (x < 0 || x >= N || y < 0 || y >= M || maze[x][y] == 1 || maze[x][y] == 2) {
            return Integer.MAX_VALUE;
        }

        if(maze[x][y] == 3) {
            return depth;
        }

        maze[x][y] = 2; // 기록

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++) {
            int i1 = solveMazeWithShortLength(x + dx[i], y + dy[i], depth + 1);
            System.out.println("DEPTH : " + i1);
            if (i1 < min) {
                min = i1;
            }
        }

        maze[x][y] = 0; //
        return min;
    }
}

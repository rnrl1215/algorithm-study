package algorithm.bfs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MazeTest {

    @Test
    public void mazeTest() {
        boolean b = Maze.solveMaze(0, 0);
        Assertions.assertTrue(b);
    }

    @Test
    public void mazeTest2() {

        int minDepth = Maze.solveMazeWithShortLength(0, 0, 0);

        System.out.println("최단 경로 길이 = " + minDepth);
        System.out.println(Maze.totalDepth);

    }
}

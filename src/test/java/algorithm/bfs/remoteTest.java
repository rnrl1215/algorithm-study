package algorithm.bfs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class remoteTest {

    @Test
    void solve() {
        int solve = remote.solve(7, 34);
        Assertions.assertEquals(5, solve);
    }

}
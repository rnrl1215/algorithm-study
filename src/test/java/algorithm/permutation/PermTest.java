package algorithm.permutation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermTest {

    @Test
    public void test() {
        int[] arr = {1,2,3,4,5};
        boolean[] visited = new boolean[arr.length];
        List<Integer> current = new ArrayList<>();

        Perm.perm(arr, visited, current, 3);

        int max = Perm.max;
        System.out.println(max);
    }

}
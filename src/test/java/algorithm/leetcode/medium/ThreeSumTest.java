package algorithm.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreeSumTest {
    @Test
    void threeSum() {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        Assertions.assertEquals(2, lists.size());
    }

    @Test
    void tset() {
        LocalDate targetDate = LocalDate.now().minusDays(30);

    }
}
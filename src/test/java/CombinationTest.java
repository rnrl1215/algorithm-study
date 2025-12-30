import algorithm.combination.Combination;
import org.junit.jupiter.api.Test;

public class CombinationTest {

    @Test
    public void test() {
        int arr[] = { 1,2,3,4,5 };
        int n = 5;
        int r = 3;

        Combination combination = new Combination();

        combination.solve(arr, 0, 3);
    }
}

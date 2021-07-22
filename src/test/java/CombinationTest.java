import algorithm.combination.Combination;

public class CombinationTest {
    public static void main(String[] args) {
        int arr[] = { 1,2,3,4,5 };
        int n = 5;
        int r = 3;

        int[] comb = new int[5];

        Combination combination = new Combination();

        combination.solve(arr, 5, 3, 0, 0, comb);
    }

}

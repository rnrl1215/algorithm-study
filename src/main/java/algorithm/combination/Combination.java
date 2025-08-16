package algorithm.combination;

public class Combination {

    public void Combination ()
    {

    }

    public void solve(int arr[], int n, int r, int index, int target, int comb[])
    {
        System.out.println("N: " + n + " R: " + r + " index: " + index + " Target: " + target);

        if (r == 0) {
            for (int i = 0; i < index; i++) {
                System.out.print(comb[i]);
            }

            System.out.println("");
        } else if (target == n) {
            return;
        } else {
            comb[index] = arr[target];
            solve(arr, n, r - 1, index + 1, target + 1, comb);
            solve(arr, n, r, index, target + 1, comb);
        }
    }


    static int[] arr = {1,2,3,5,6};
    static int n = arr.length;
    static int r = 3;
    static int[] result = new int[r];

    public static void combine(int idx, int start) {
        if (idx == r) {
            for (int i = 0; i< r; i++) {
                int i1 = result[i];
                System.out.println("i1: " + i1);
            }
            return;
        }

        for(int i = start; i < n; i++) {
            result[idx] = arr[i];
            combine(idx+1, i+1);
        }
    }
}

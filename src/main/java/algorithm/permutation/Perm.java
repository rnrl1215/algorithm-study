package algorithm.permutation;


import java.util.*;
public class Perm {

    static int max = Integer.MIN_VALUE;

    static void perm(int[] nums,
                     boolean[] visited,
                     List<Integer> current,
                     int r) {

        int number = 0;
        if (r == current.size()) {
            for (int digit : current) {
                number = number * 10 + digit;
            }
            max = Math.max(max, number);
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(nums[i]);
                perm(nums,visited,current,r);
                current.remove(current.size()-1);
                visited[i] = false;
            }
        }
    }
}

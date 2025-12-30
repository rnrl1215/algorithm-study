package algorithm.leetcode.medium;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {

    private String[][] keypad = new String[][] {
            {"a", "b", "c"},
            {"d", "e", "f"},
            {"g", "h", "i"},
            {"j", "k", "l"},
            {"m", "n", "o"},
            {"p", "q", "r", "s"},
            {"t", "u", "v"},
            {"w", "x", "y", "z"}
    };

    private List<String> result = new ArrayList<>();


    public List<String> letterCombinations(String digits) {
        char[] array = digits.toCharArray();
        String [][] selectedAlpha = new String[digits.length()][];
        int idx = 0;
        for (char c : array) {
            String[] strings = keypad[c - '0' - 2];
            selectedAlpha[idx] = strings;
            idx++;
        }

        for (int i = 0; i < selectedAlpha[0].length; i++) {
            solve(selectedAlpha, 1, digits.length() -1, selectedAlpha[0][i]);
        }
        return result;
    }

    public void solve(String [][] selectedAlpha,
                      int depth,
                      int totalDepth,
                      String selectedData) {

        if (depth == totalDepth + 1) {
            result.add(selectedData);
        }

        if (depth > totalDepth) {
            return;
        }

        for (int i = 0; i < selectedAlpha[depth].length; i++) {
            solve(selectedAlpha, depth + 1, totalDepth, selectedData.concat(selectedAlpha[depth][i]));
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 개선
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    private final String[] KEYPAD = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations2(String digits) {

        List<String> result2 = new ArrayList<>();
        solve2(result2, new StringBuilder(), digits, 0);
        return result2;
    }

    public void solve2(List<String> result,
                      StringBuilder current,
                      String digit,
                      int index) {

        if (index == digit.length()) {
            result.add(current.toString());
            return;
        }

        String  letters = KEYPAD[index];

        for (char c: letters.toCharArray()) {
            current.append(c);
            solve2(result, current, digit, index + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }
}

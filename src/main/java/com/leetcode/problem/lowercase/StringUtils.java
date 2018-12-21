package com.leetcode.problem.lowercase;

public class StringUtils {

    private StringUtils() {
    }

    public static String toLowerCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        int delta = 'a' - 'A';
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch > 'A' && ch < 'Z') {
                arr[i] = (char) (ch + delta);
            }
        }
        return new String(arr);
    }
}

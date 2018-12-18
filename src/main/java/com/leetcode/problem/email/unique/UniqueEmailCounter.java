package com.leetcode.problem.email.unique;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailCounter {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> unique = new HashSet<>();
        for (String email : emails) {
            unique.add(getEmail(email));
        }
        return unique.size();
    }

    private String getEmail(String email) {
        char[] emailChars = email.toCharArray();
        StringBuilder result = new StringBuilder();
        boolean skip = false;
        boolean mainPart = true;
        for (char emailChar : emailChars) {
            if (mainPart) {
                if (emailChar == '.') continue;
                if (emailChar == '+') {
                    skip = true;
                }
                if (emailChar == '@') {
                    mainPart = false;
                    skip = false;
                }
                if (skip) continue;
            }
            result.append(emailChar);
        }
        return result.toString();
    }
}

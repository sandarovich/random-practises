package com.sandarovich.agilengine.test;

/**
 * @author Olexander Kolodiazhny
 * @since 2/21/2018
 */
public class StringUtils {

    private static final int INDEX_VALUE_NOT_FOUND = -1;
    private static final int OFFSET_VALUE = 1;

    /**
     * @param source   the characters being searched through.
     * @param searched the characters being searched for..
     * @return the index of the last occurrence of the searched in the source
     * or -1 if the character does not occur or target is empty or one of source or target is null.
     * Draw your attention that in case of searched length greater then source logically we will receive -1.
     * In case when searched is empty, will return source length.
     */
    public static int lastIndexOf(String source, String searched) {
        if (isNull(source) || isNull(searched)
                || searched.length() > source.length()) {
            return INDEX_VALUE_NOT_FOUND;
        }
        if (isEmpty(source) && isEmpty(searched)) {
            return 0;
        }
        if (isEmpty(searched)) {
            return source.length();
        }
        return lastIndexOf(source, searched, 0);
    }

    private static int lastIndexOf(String source, String searched, int start) {
        if (start <= source.length()) {
            int currentPos = indexOf(source, searched, start);
            if (currentPos == INDEX_VALUE_NOT_FOUND) {
                return start - OFFSET_VALUE;
            }
            return lastIndexOf(source, searched, start + OFFSET_VALUE);
        }
        return INDEX_VALUE_NOT_FOUND;
    }


    private static boolean isEmpty(String source) {
        return isNull(source) || source.length() == 0;
    }

    private static boolean isNull(String source) {
        return source == null;
    }

    private static int indexOf(String source, String searched, int start) {
        if (start < source.length()) {
            int firstOccurrence = indexCharOf(source, searched.charAt(0), start);
            if (firstOccurrence == INDEX_VALUE_NOT_FOUND) {
                return INDEX_VALUE_NOT_FOUND;
            }
            if (isSubSequenceMatch(source, searched, firstOccurrence)) {
                return firstOccurrence;
            }
            return indexOf(source, searched, start + OFFSET_VALUE);
        }
        return INDEX_VALUE_NOT_FOUND;
    }

    private static boolean isSubSequenceMatch(String source, String searched, int firstOccurrence) {
        int matched = OFFSET_VALUE;
        for (int i = firstOccurrence + OFFSET_VALUE, j = OFFSET_VALUE; j < searched.length()
                && i < source.length(); i++, j++) {
            if (source.charAt(i) == searched.charAt(j)) {
                matched++;
            }
        }
        return matched == searched.length();
    }

    private static int indexCharOf(String source, char ch, int start) {
        for (int i = start; i < source.length(); i++) {
            if (source.charAt(i) == ch) {
                return i;
            }
        }
        return INDEX_VALUE_NOT_FOUND;
    }
}

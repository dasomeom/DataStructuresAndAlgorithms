import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Dasom Eom
 * @version 1.9
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match temp
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0 || text == null
                || comparator == null) {
            throw new IllegalArgumentException("Pattern, text, comparator "
                    + "should not be null!! and pattern length should be "
                    + "greater than 0.");
        }
        List<Integer> temp = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return temp;
        }
        int[] table = buildFailureTable(pattern, comparator);

        int t = 0;
        int p = 0;
        while (t <= text.length() - pattern.length()) {
            while (p < pattern.length()
                    && comparator.compare(text.charAt(t + p),
                    pattern.charAt(p)) == 0) {
                p++;
            }
            if (p == 0) {
                t++;
            } else {
                if (p == pattern.length()) {
                    temp.add(t);
                }
                int theNext = table[p - 1];
                t += p - theNext;
                p = theNext;

            }
        }
        return temp;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern or comparator is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null || comparator == null) {
            throw new IllegalArgumentException("Pattern and comparator "
                    + "should not be null!!");
        }
        int[] bftTable = new int[pattern.length()];
        if (pattern.length() == 0) {
            return bftTable;
        }
        int i = 0;
        int j = 1;

        bftTable[0] = 0;
        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                bftTable[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    bftTable[j] = 0;
                    j++;
                } else {
                    i = bftTable[i - 1];
                }
            }
        }
        return bftTable;

    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match temp
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                       CharSequence text, CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0 || text == null
                || comparator == null) {
            throw new IllegalArgumentException("Pattern, text, comparator "
                    + "should not be null!! and pattern length should be "
                    + "greater than 0.");
        }
        List<Integer> temp = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return temp;
        }
        Map<Character, Integer> table = buildLastTable(pattern);
        int t = 0;
        while (t <= text.length() - pattern.length()) {
            int p = pattern.length() - 1;
            while (p >= 0 && comparator
                    .compare(text.charAt(t + p), pattern.charAt(p)) == 0) {
                p--;
            }
            if (p == -1) {
                temp.add(t);
                t++;
            } else {
                int shiftedIndex = table.getOrDefault(text.charAt(t + p), -1);
                if (shiftedIndex < p) {
                    t += p - shiftedIndex;
                } else {
                    t++;
                }
            }
        }
        return temp;

    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        Map<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            table.put(pattern.charAt(i), i);
        }
        return table;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 137;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 137 hash
     * = b * 137 ^ 3 + u * 137 ^ 2 + n * 137 ^ 1 + n * 137 ^ 0 = 98 * 137 ^ 3 +
     * 117 * 137 ^ 2 + 110 * 137 ^ 1 + 110 * 137 ^ 0 = 254203747
     *
     * Note that since you are dealing with very large numbers here, your hash
     * will likely overflow, and that is fine for this implementation.
     *
     * Another key step for this algorithm is that updating the hashcode from
     * one substring to the next one must be O(1). To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 137
     * hash("unny") = (hash("bunn") - b * 137 ^ 3) * 137 + y * 137 ^ 0 =
     * (254203747 - 98 * 137 ^ 3) * 137 + 121 * 137 ^ 0 = 302928082
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^{m - 1} is for updating the hash.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match temp
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                      CharSequence text, CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0 || text == null
                || comparator == null) {
            throw new IllegalArgumentException("Pattern, text, comparator "
                    + "should not be null!! and pattern length should be "
                    + "greater than 0.");
        }
        List<Integer> temp = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return temp;
        }
        int[] hashArray = generateHash(text, pattern, pattern.length());
        int pHash = hashArray[1];
        int tHash = hashArray[0];
        int t = 0;
        while (t <= text.length() - pattern.length()) {
            if (pHash == tHash) {
                int p = 0;
                while (p < pattern.length() && comparator
                        .compare(text.charAt(t + p), pattern.charAt(p)) == 0) {
                    p++;
                }
                if (p == pattern.length()) {
                    temp.add(t);
                }
            }
            if (t + 1 <= text.length() - pattern.length()) {
                tHash = updateHash(tHash, pattern.length(), text.charAt(t),
                        text.charAt(t + pattern.length()), hashArray[2]);
            }
            t++;
        }
        return temp;
    }

    /**
     * generate hashvalue for rabinKarp
     *
     * @param current the substring sequence
     * @param length the length of the string you want to generate the hash for
     * @return the hash value
     */
    public static int[] generateHash(CharSequence text, CharSequence pattern, int length) {
        if (text == null || length <= 0 || length > text.length()) {
            throw new IllegalArgumentException("Current cannot be null and "
            + "length should not be out of range");
        }
        int base = 1;
        int textHash = 0;
        int patternHash = 0;
        for (int i = length - 1; i >= 0; i--) {
            textHash += text.charAt(i) * base;
            patternHash += pattern.charAt(i) * base;
            base *= BASE;
        }
        return new int[]{textHash, patternHash, base / BASE};
    }

    /**
     * update hash value
     *
     * @param oldHash the old hash value
     * @param length the length
     * @param oldChar old char to be removed
     * @param newChar new char to be added
     * @param base base
     * @return updated hash value
     */
    public static int updateHash(int oldHash, int length, char oldChar,
                                 char newChar, int base) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be " + length);
        }

        return ((oldHash - oldChar * base) * BASE + newChar);
    }


}
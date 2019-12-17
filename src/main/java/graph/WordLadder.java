package graph;

import javafx.util.Pair;
import princeton.jsl.TrieSET;

import java.util.*;

/**
 * LeetCode. Medium. Tags: BFS (Breadth First Search).
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * <pre>
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * </pre>
 * <p>
 * Output: 5
 * <p>
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Example 2:
 * <pre>
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * </pre>
 * Output: 0
 * <p>
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * <p>
 * <b>Summary:</b>
 * <p>Assume word length is M, number of words is N.
 * <ul>
 * <li>One way or two way BFS with hash map. O(MN) time, O(MN) space.
 * <li>BFS with trie, practically faster, especially on search miss. O(MN) time, O(MN) space.</>
 * </ul>
 */
public class WordLadder {
    public int ladderLengthBFSTrie(String beginWord, String endWord, List<String> wordList) {
        int L = beginWord.length();
        TrieSET trie = new TrieSET();
        for (String word : wordList) {
            trie.add(word);
        }
        if (trie.contains(endWord)) {
            Queue<Pair<String, Integer>> q = new ArrayDeque<>();
            q.add(new Pair<>(beginWord, 1));
            TrieSET visited = new TrieSET();
            visited.add(beginWord);
            while (!q.isEmpty()) {
                Pair<String, Integer> curNode = q.remove();
                String curWord = curNode.getKey();
                int level = curNode.getValue();
                for (int i = 0; i < L; i++) {
                    String wild = curWord.substring(0, i) + "." + curWord.substring(i + 1, L);
                    Iterable<String> matchedWords = trie.keysThatMatch(wild);
                    for (String word : matchedWords) {
                        if (endWord.equals(word)) return level + 1;
                        if (!visited.contains(word)) {
                            visited.add(word);
                            q.add(new Pair<>(word, level + 1));
                        }
                    }
                }
            }
        }
        return 0;
    }


    public int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {
        int L = beginWord.length(); // Since all words are of same length.
        // Dictionary of words that can be formed from any given word. By changing one letter at a time.
        // e.g., h*t -> [hot,hat], *og -> [dog,log]
        HashMap<String, List<String>> wildToReal = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String wild = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations =
                                wildToReal.getOrDefault(wild, new ArrayList<>());
                        transformations.add(word);
                        wildToReal.put(wild, transformations);
                    }
                });
        // Queue for BFS
        Queue<Pair<String, Integer>> q = new LinkedList<Pair<String, Integer>>();
        q.add(new Pair(beginWord, 1));
        // Visited to make sure we don't repeat processing same word.
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        visited.put(beginWord, true);
        while (!q.isEmpty()) {
            Pair<String, Integer> node = q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {
                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : wildToReal.getOrDefault(newWord, new ArrayList<String>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }

    private int L;
    private HashMap<String, ArrayList<String>> allComboDict;

    public WordLadder() {
        this.L = 0;
        // Dictionary of words that can be formed from any given word. By changing one letter at a time.
        this.allComboDict = new HashMap<String, ArrayList<String>>();
    }

    private int visitWordNode(
            Queue<Pair<String, Integer>> Q,
            HashMap<String, Integer> visited,
            HashMap<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < this.L; i++) {
            // Intermediate words for current word
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            // Next states are all the words which share the same intermediate state.
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                // If at any point if we find what we are looking for
                // i.e. the end word - we can return with the answer.
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }
                if (!visited.containsKey(adjacentWord)) {
                    // Save the level as the value of the dictionary, to save number of hops.
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLengthTwoWayBFS(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // Since all words are of same length.
        this.L = beginWord.length();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        ArrayList<String> transformations =
                                this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });

        // Queues for bi-directional BFS
        // BFS starting from beginWord
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<Pair<String, Integer>>();
        // BFS starting from endWord
        Queue<Pair<String, Integer>> Q_end = new LinkedList<Pair<String, Integer>>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));
        // Visited to make sure we don't repeat processing same word.
        HashMap<String, Integer> visitedBegin = new HashMap<String, Integer>();
        HashMap<String, Integer> visitedEnd = new HashMap<String, Integer>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }
            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }
}

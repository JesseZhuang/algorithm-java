package string;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.NonNull;
import princeton.jsl.TrieSET;

import java.util.*;

/**
 * S. Hard. Tags: Trie, Graph.
 * <p>
 * Given a boggle board of characters and dictionary, return all valid words. You can start from any character
 * and go horizontal, vertical, or diagonal.
 */
public class Boggle {
    static final int[][] neighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {0, -1}, {1, 1}, {1, 0}, {1, -1}};
    Set<String> resultSet;
    List<String> resultList;
    List<IndexedPrefix> resultIndexedPrefixList;
    Character[][] characters;
    String[] dictionary;
    TrieSET trieSET;
    int nR;
    int nC;

    @Data
    class Coordinate {
        final int r;
        final int c;
    }

    @Data
    class IndexedPrefix {
        @NonNull Set<Coordinate> coordinateSet;
        @NonNull List<Coordinate> coordinateList;
        final Coordinate coordinate;
        final String prefix;
    }

    public Boggle(Character[][] characters, String[] dictionary) {
        resultList = new ArrayList<>();
        resultSet = new HashSet<>();
        this.characters = characters;
        this.dictionary = dictionary;
        nR = characters.length;
        if (nR != 0) nC = characters[0].length;
        trieSET = new TrieSET();
        resultIndexedPrefixList = new ArrayList<>();
        for (String word : dictionary) trieSET.add(word);
        if (nR != 0) {
            for (int r = 0; r < nR; r++) {
                for (int c = 0; c < nC; c++) {
                    bfs(r, c);
                }
            }
        }
    }

    String[] allValidWords() {
        return resultSet.toArray(new String[0]);
    }

    /**
     * BFS two D matrix.
     *
     * @param r row index.
     * @param c column index.
     */
    private void bfs(int r, int c) {
        if (!trieSET.isAPrefix(String.valueOf(characters[r][c]))) return;
        Queue<IndexedPrefix> queue = new ArrayDeque<>();
        Coordinate coordinate = new Coordinate(r, c);
        queue.add(new IndexedPrefix(Sets.newHashSet(coordinate), Lists.newArrayList(coordinate), coordinate,
                String.valueOf(characters[r][c])));
        while (!queue.isEmpty()) {
            IndexedPrefix ip = queue.remove();
            if (trieSET.contains(ip.prefix)) {
                resultList.add(ip.prefix);
                resultSet.add(ip.prefix);
                resultIndexedPrefixList.add(ip);
            }
            int cR = ip.coordinate.r, cC = ip.coordinate.c;
            for (int[] neighbor : neighbors) {
                int nextR = cR + neighbor[0];
                int nextC = cC + neighbor[1];
                if (inBound(nextR, nextC)) {
                    String prefix = ip.prefix + characters[nextR][nextC];
                    if (trieSET.isAPrefix(prefix)) {
                        Coordinate nCoordinate = new Coordinate(nextR, nextC);
                        Set<Coordinate> cSet = Sets.newHashSet(nCoordinate);
                        cSet.addAll(ip.coordinateSet);
                        List<Coordinate> cList = Lists.newArrayList(coordinate);
                        cList.addAll(ip.coordinateList);
                        queue.add(new IndexedPrefix(cSet, cList, nCoordinate, prefix));
                    }
                }
            }
        }
    }


    private boolean inBound(int r, int c) {
        return r >= 0 && r < nR && c >= 0 && c < nC;
    }
}

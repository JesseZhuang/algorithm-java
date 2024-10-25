package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 1233, medium, tags: array, string, dfs, trie.
 * <p>
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return
 * the answer in any order.
 * <p>
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j]
 * must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a
 * sub-folder of "/a/b/c".
 * <p>
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase
 * English letters.
 * <p>
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 * <p>
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
 * Example 3:
 * <p>
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= folder.length <= 4 * 104
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'.
 * folder[i] always starts with the character '/'.
 * Each folder name is unique.
 * <p>
 * Hint 1
 * Sort the folders lexicographically.
 * Hint 2
 * Insert the current element in an array and then loop until we get rid of all of their subfolders, repeat
 * this until no element is left.
 */
@SuppressWarnings("unused")
public class RemoveSubFolders {

    static class Solution {
        TrieNode root;

        Solution() {
            this.root = new TrieNode();
        }

        public List<String> removeSubfolders(String[] folder) {
            // Build Trie from folder paths
            for (String path : folder) {
                TrieNode currentNode = root;
                String[] folderNames = path.split("/");

                for (String folderName : folderNames) {
                    // Skip empty folder names
                    if (folderName.equals("")) continue;
                    // Create new node if it doesn't exist
                    if (!currentNode.children.containsKey(folderName)) {
                        currentNode.children.put(folderName, new TrieNode());
                    }
                    currentNode = currentNode.children.get(folderName);
                }
                // Mark the end of the folder path
                currentNode.isEndOfFolder = true;
            }

            // Check each path for subfolders
            List<String> result = new ArrayList<>();
            for (String path : folder) {
                TrieNode currentNode = root;
                String[] folderNames = path.split("/");
                boolean isSubfolder = false;

                for (int i = 0; i < folderNames.length; i++) {
                    // Skip empty folder names
                    if (folderNames[i].equals("")) continue;

                    TrieNode nextNode = currentNode.children.get(folderNames[i]);
                    // Check if the current folder path is a subfolder of an
                    // existing folder
                    if (nextNode.isEndOfFolder && i != folderNames.length - 1) {
                        isSubfolder = true;
                        break; // Found a sub-folder
                    }

                    currentNode = nextNode;
                }
                // If not a sub-folder, add to the result
                if (!isSubfolder) result.add(path);
            }

            return result;
        }

        static class TrieNode {

            boolean isEndOfFolder;
            HashMap<String, TrieNode> children;

            TrieNode() {
                this.isEndOfFolder = false;
                this.children = new HashMap<>();
            }
        }
    }
}

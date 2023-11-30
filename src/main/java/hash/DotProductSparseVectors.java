package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1570, LintCode 3691, medium, tags: two pointers, array, hash table.
 * <p>
 * Given two sparse vectors, compute their dot product.
 * <p>
 * Implement class SparseVector:
 * <p>
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * <p>
 * A sparse vector is a vector that has mostly zero values,
 * you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * <p>
 * Follow up: What if only one of the vectors is sparse?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class DotProductSparseVectors {
    // solution 1, map, n time and space. LintCode 672ms, 32.16Mb.
    // solution 2 use list to store non-zero index and val, iterate with two pointers
    class SparseVector {
        private Map<Integer, Integer> indexVal;

        SparseVector(int[] nums) {
            indexVal = new HashMap<>();
            for (int i = 0; i < nums.length; i++)
                if (nums[i] != 0) indexVal.put(i, nums[i]);
        }

        public int dotProduct(SparseVector vec) {
            // follow up, if only one vector is sparse, swap
            if (indexVal.size() > vec.indexVal.size()) return vec.dotProduct(this);
            int res = 0;
            for (int index : indexVal.keySet())
                if (vec.indexVal.containsKey(index)) res += vec.indexVal.get(index) * indexVal.get(index);
            return res;
        }

    }
}

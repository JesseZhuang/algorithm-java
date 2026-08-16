package heap;

import java.util.*;

/**
 * LeetCode 355, medium, tags: hash table, linked list, design, heap.
 * <p>
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user,
 * and is able to see the 10 most recent tweets in the user's news feed.
 * <p>
 * Implement the Twitter class:
 * <p>
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId.
 * Each call to this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed.
 * Each item in the news feed must be posted by users who the user followed or by the user themself.
 * Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user
 * with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user
 * with ID followeeId.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, [5, 6], null, [5]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * All the tweets have unique IDs.
 * At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 */
@SuppressWarnings("unused")
public class DesignTwitter {

    // HashMap + Heap (merge k sorted lists). O(1) post/follow/unfollow, O(N logK) getNewsFeed.
    private final Map<Integer, List<int[]>> tweets; // userId -> list of [time, tweetId]
    private final Map<Integer, Set<Integer>> follows; // followerId -> set of followeeIds
    private int time;

    public DesignTwitter() {
        tweets = new HashMap<>();
        follows = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{time--, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        // max heap by time (smaller time value = more recent since we decrement)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // [time, tweetId, userId, index in that user's tweet list]
        Set<Integer> users = new HashSet<>();
        users.add(userId);
        if (follows.containsKey(userId)) users.addAll(follows.get(userId));

        for (int uid : users) {
            List<int[]> userTweets = tweets.get(uid);
            if (userTweets != null && !userTweets.isEmpty()) {
                int idx = userTweets.size() - 1;
                int[] tweet = userTweets.get(idx);
                pq.offer(new int[]{tweet[0], tweet[1], uid, idx});
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty() && result.size() < 10) {
            int[] top = pq.poll();
            result.add(top[1]);
            int idx = top[3] - 1;
            if (idx >= 0) {
                int[] tweet = tweets.get(top[2]).get(idx);
                pq.offer(new int[]{tweet[0], tweet[1], top[2], idx});
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = follows.get(followerId);
        if (set != null) set.remove(followeeId);
    }
}

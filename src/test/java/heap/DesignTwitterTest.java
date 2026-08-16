package heap;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DesignTwitterTest {

    @Test
    void testLeetCodeExample() {
        DesignTwitter twitter = new DesignTwitter();
        twitter.postTweet(1, 5);
        assertEquals(List.of(5), twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        assertEquals(List.of(6, 5), twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        assertEquals(List.of(5), twitter.getNewsFeed(1));
    }

    @Test
    void testEmptyFeed() {
        DesignTwitter twitter = new DesignTwitter();
        assertEquals(List.of(), twitter.getNewsFeed(1));
    }

    @Test
    void testMax10Tweets() {
        DesignTwitter twitter = new DesignTwitter();
        for (int i = 0; i < 15; i++) twitter.postTweet(1, i);
        List<Integer> feed = twitter.getNewsFeed(1);
        assertEquals(10, feed.size());
        // most recent first: 14, 13, 12, ..., 5
        assertEquals(List.of(14, 13, 12, 11, 10, 9, 8, 7, 6, 5), feed);
    }

    @Test
    void testFollowSelfNoDuplicates() {
        DesignTwitter twitter = new DesignTwitter();
        twitter.postTweet(1, 10);
        twitter.postTweet(1, 20);
        twitter.follow(1, 1); // follow self
        List<Integer> feed = twitter.getNewsFeed(1);
        // should not have duplicates
        assertEquals(List.of(20, 10), feed);
    }

    @Test
    void testUnfollowNonFollowee() {
        DesignTwitter twitter = new DesignTwitter();
        // should not throw
        assertDoesNotThrow(() -> twitter.unfollow(1, 2));
    }

    @Test
    void testMultipleUsersInterleaved() {
        DesignTwitter twitter = new DesignTwitter();
        twitter.postTweet(1, 1);
        twitter.postTweet(2, 2);
        twitter.postTweet(1, 3);
        twitter.postTweet(2, 4);
        twitter.follow(1, 2);
        // most recent first: 4, 3, 2, 1
        assertEquals(List.of(4, 3, 2, 1), twitter.getNewsFeed(1));
    }

    @Test
    void testUnfollowThenRefollow() {
        DesignTwitter twitter = new DesignTwitter();
        twitter.postTweet(2, 100);
        twitter.follow(1, 2);
        assertEquals(List.of(100), twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        assertEquals(List.of(), twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        assertEquals(List.of(100), twitter.getNewsFeed(1));
    }
}

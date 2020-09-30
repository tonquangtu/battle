import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Twitter {
    private static final int MOST_RECENT_TWEET_NUM = 10;

    private Map<Integer, User> users;

    private int timestamp = 0;

    /** Initialize your data structure here. */
    public Twitter() {
        this.users = new HashMap<>();
        String a;
    }

    private User getOrAddIfNotExist(int userId) {
        User user = this.users.get(userId);
        if (user == null) {
            user = new User(userId);
            this.users.put(userId, user);
        }
        return user;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = getOrAddIfNotExist(userId);
        user.addTweet(new Tweet(tweetId, timestamp++));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User user = getOrAddIfNotExist(userId);
        return user.getMostRecentTweets(MOST_RECENT_TWEET_NUM);
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User follower = getOrAddIfNotExist(followerId);
        User followee = getOrAddIfNotExist(followeeId);
        follower.follow(followee);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User follower = getOrAddIfNotExist(followerId);
        User followee = getOrAddIfNotExist(followeeId);
        follower.unfollow(followee);
    }

    static class User {
        public final int userId;
        private final List<Tweet> tweets;
        private final Set<User> followees;

        public User(int userId) {
            this.userId = userId;
            this.tweets = new ArrayList<>();
            this.followees = new HashSet<>();
        }

        public void addTweet(Tweet tweet) {
            tweets.add(tweet);
        }

        public List<Integer> getMostRecentTweets(int tweetNum) {
            PriorityQueue<Tweet> minHeap = new PriorityQueue<>((t1, t2) -> t1.timestamp - t2.timestamp);
            Set<Integer> visited = new HashSet<>();
            filterMostRecentTweets(minHeap, visited, this, tweetNum);
            for (User user : followees) {
                filterMostRecentTweets(minHeap, visited, user, tweetNum);
            }
            Integer[] recentTweetIds = new Integer[minHeap.size()];
            int index = minHeap.size() - 1;
            while(!minHeap.isEmpty()) {
                recentTweetIds[index--] = minHeap.poll().tweetId;
            }
            return Arrays.asList(recentTweetIds);
        }

        private void filterMostRecentTweets(PriorityQueue<Tweet> minHeap, Set<Integer> visited, User user, int tweetNum) {
            int size = user.tweets.size();
            for (int i = size - 1; i >= 0 && i >= size - tweetNum; i--) {
                Tweet tweet = user.tweets.get(i);
                if (visited.contains(tweet.tweetId)) { continue; }
                if (minHeap.size() < tweetNum) {
                    minHeap.add(tweet);
                    visited.add(tweet.tweetId);
                } else if (tweet.timestamp > minHeap.peek().timestamp) {
                    minHeap.poll();
                    minHeap.add(tweet);
                    visited.add(tweet.tweetId);
                }
            }
        }

        public void follow(User user) {
            followees.add(user);
        }

        public void unfollow(User user) {
            followees.remove(user);
        }

        @Override
        public boolean equals(Object o) {
            User u = (User)o;
            return this.userId == u.userId;
        }
    }

    static class Tweet {
        public final int tweetId;
        public final int timestamp;

        public Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
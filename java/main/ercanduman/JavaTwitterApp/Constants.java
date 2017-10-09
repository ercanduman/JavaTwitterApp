package ercanduman.JavaTwitterApp;

/**
 * Created on 06.10.2017.
 */
class Constants {

    // APP RUN CONSTANTS
    static String DB_USER_ID = "2"; //(1: "ercanistd", 2: "bbcturkce") ->(user_id in database eduman.twitter_users)
    static boolean RUN_APP = true; //(true, false)
    static int INTERVAL = 1; // (in minute)

    // TWITTER CONSTANTS
    static String USERNAME;
    static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
    static final String OAUTH_ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";
    static final String OAUTH_CONSUMER_KEY = "oauth.consumerKey";
    static final String OAUTH_CONSUMER_SECRET = "oauth.consumerSecret";
    static final String PROPERTIES_PATHNAME = "java/libs/twitter4j.properties";


    // DATABASE CONSTANTS
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_USERNAME = "EDUMAN";
    static final String DB_PASSWORD = "Erc787dmm";


    // SQL Queries
    static String SEARCH_USER_SQL = "SELECT username FROM eduman.TWITTER_USERS WHERE id = ?";
    static String SEARCH_TWEETID_SQL = "SELECT DISTINCT t.tweet_id FROM eduman.tweets t WHERE t.username = ? AND t.tweet_id IN (SELECT MAX(tweet_id) FROM eduman.tweets t2 WHERE t.username = t2.username)";
    static String INSERT_SQL = "INSERT INTO eduman.tweets (id, username, tweet_id, tweet_data, create_date, user_id) VALUES (eduman.seq_tweets_id.nextval, ?, ?, ?, ?, ?)";

}

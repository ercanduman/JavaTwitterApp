package ercanduman.JavaTwitterApp;

/**
 * Created on 06.10.2017.
 */
class Constants {

    // TWITTER CONSTANTS
    static String USERTWITTERID = "bbcturkce";   //TEST: "ercanistd", REAL APP: "bbcturkce"
    static String USERNAME;
    static String LAST_TWEET_ID = "";

    static final String PROPERTIES_PATHNAME = "java/libs/twitter4j.properties";

    static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
    static final String OAUTH_ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";
    static final String OAUTH_CONSUMER_KEY = "oauth.consumerKey";
    static final String OAUTH_CONSUMER_SECRET = "oauth.consumerSecret";


    // DATABASE CONSTANTS
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_USERNAME = "EDUMAN";
    static final String DB_PASSWORD = "Erc787dmm";

    // SQL Queries
    static String SEARCH_USER_SQL = "SELECT username FROM eduman.TWITTER_USERS WHERE id = ?";
    //    static String SEARCH_TWEETS_SQL = "SELECT * FROM eduman.TWEETS where user_id = ?;";
    static String INSERT_SQL = "INSERT INTO eduman.tweets (id, username, tweet_id, tweet_data, create_date) VALUES (eduman.seq_tweets_id.nextval, ?, ?, ?, ?)";

}

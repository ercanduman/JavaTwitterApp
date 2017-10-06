package ercanduman.JavaTwitterApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainActivity {
    private static String accessToken, accessTokenSecret, consumerKey, consumerSecret;
    private static Twitter twitter;
    static DatabaseHandler db;

    public static void main(String[] strings) {
        getCredentials();

        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        db = new DatabaseHandler();
        if (db.establishConnection())
            db.executeSQLSEARCH("2");

        String username = Constants.USERNAME;
        if (username != null)
            otherUserTimeline(username);
        else System.out.println("USERNAME not found! Please check input id value!");
    }

    private static void otherUserTimeline(String userTwitterId) {
        System.out.println("\nINFO> Twitter API execution started!");
        Paging paging = new Paging(1, 1);
        List<Status> statuses = new ArrayList();
        String username, tweet_id, tweet_data, created_date;

        try {
            statuses.addAll(twitter.getUserTimeline(userTwitterId, paging));
            String currUser = twitter.verifyCredentials().getScreenName();
            for (int i = 0; i < paging.getCount(); i++) {
                username = currUser;
                tweet_id = String.valueOf(statuses.get(i).getId());

                tweet_data = statuses.get(i).getText();
                created_date = String.valueOf(statuses.get(i).getCreatedAt());

/*                System.out.println("\nINFO> " + (i + 1) +
                            "\n ID:\t" + tweet_id +
                            "\n USER_ID:\t" + username +
                            "\n TEXT:\t" + tweet_data +
                            "\n CREATED_AT:\t" + created_date);*/
                if (db.establishConnection()) {
                    if ((Constants.LAST_TWEET_ID.equals(tweet_id.toString()))) {
//                        Constants.LAST_TWEET_ID = tweet_id;
                        System.out.println("\nINFO> Constants.LAST_TWEET_ID #" + Constants.LAST_TWEET_ID + "#");
                        System.out.println("\nINFO> tweet_id #" + tweet_id + "#");
//                        db.executeSQLINSERT(username, tweet_id, tweet_data, String.valueOf(created_date));
                    } else
                        System.out.println("\nINFO> NO NEW tweet published!");

                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println("\nINFO> Twitter API execution finished!");
    }

    private static void getCredentials() {
        File file = new File(Constants.PROPERTIES_PATHNAME);
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                properties.load(inputStream);

                accessToken = properties.getProperty(Constants.OAUTH_ACCESS_TOKEN);
                accessTokenSecret = properties.getProperty(Constants.OAUTH_ACCESS_TOKEN_SECRET);
                consumerKey = properties.getProperty(Constants.OAUTH_CONSUMER_KEY);
                consumerSecret = properties.getProperty(Constants.OAUTH_CONSUMER_SECRET);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}



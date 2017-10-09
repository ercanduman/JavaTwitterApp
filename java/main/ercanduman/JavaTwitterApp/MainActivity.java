package ercanduman.JavaTwitterApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
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
    private static DatabaseHandler db;

    public static void main(String[] strings) {
        int i = 0;

        // all app running configurations applied here
        while (Constants.RUN_APP) {
            i++;
            System.out.println("\n***************************************** ");
            System.out.println("INFO> " + i + ".RUN TIME: " + Instant.now());

            run(Constants.DB_USER_ID);
            try {
                Thread.sleep(Constants.INTERVAL * (60 * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n***************************************** ");
        System.out.println("INFO> App Execution not started!");

    }

    private static void run(String userId) {
        getCredentials();

        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        db = new DatabaseHandler();
        if (DatabaseHandler.establishConnection())
            DatabaseHandler.executeSQLSEARCH(userId);

        String username = Constants.USERNAME;
        if (username != null)
            checkUserTimeline(username);
        else {
            System.out.println("\nERROR> USERNAME not found! Please check input DB_USER_ID value!");
        }
    }

    private static void checkUserTimeline(String userTwitterId) {
        System.out.println("\nINFO> Twitter API execution started!");
        Paging paging = new Paging(1, 1);
        List<Status> statuses = new ArrayList();
        String username, tweet_id, tweet_data, created_date;
        String LAST_TWEET_ID;
        try {
            statuses.addAll(twitter.getUserTimeline(userTwitterId, paging));
            username = Constants.USERNAME;
            for (int i = 0; i < paging.getCount(); i++) {
                tweet_id = String.valueOf(statuses.get(i).getId());

                tweet_data = statuses.get(i).getText();
                created_date = String.valueOf(statuses.get(i).getCreatedAt());

                if (DatabaseHandler.establishConnection()) {
                    LAST_TWEET_ID = db.getLatestTweetID(username);
                    if (LAST_TWEET_ID == null || !(LAST_TWEET_ID.equals(tweet_id))) {
                        db.executeSQLINSERT(username, tweet_id, tweet_data, String.valueOf(created_date), Constants.DB_USER_ID);
                    } else {
                        System.out.println("\nINFO> No NEW tweets found!");
                    }
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
                    ignore.printStackTrace();
                }
            }
        }
    }
}



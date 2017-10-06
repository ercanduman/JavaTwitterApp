package ercanduman.JavaTwitterApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainActivity {
    private static String accessToken, accessTokenSecret, consumerKey, consumerSecret;
    private static Twitter twitter;

    public static void main(String[] strings) {
        getCredentials();

        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        try {
            ResponseList<Status> statuses = twitter.getUserTimeline(new Paging(1, 5));
            String currUser = twitter.verifyCredentials().getScreenName();
            System.out.println(currUser + "'s timeline is showing!");

            //Show user's last 5 tweets
            for (int i = 0; i < 5; i++) {
                System.out.println(i + 1 + " -- " + statuses.get(i).getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    private static void getCredentials() {
        File file = new File("java/libs/twitter4j.properties");
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                properties.load(inputStream);

                accessToken = properties.getProperty("oauth.accessToken");
                accessTokenSecret = properties.getProperty("oauth.accessTokenSecret");
                consumerKey = properties.getProperty("oauth.consumerKey");
                consumerSecret = properties.getProperty("oauth.consumerSecret");
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



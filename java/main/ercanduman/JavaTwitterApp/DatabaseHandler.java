package ercanduman.JavaTwitterApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

/**
 * Created on 06.10.2017.
 */
class DatabaseHandler {
    private static Connection connection;
    private static ResultSet resultSet;

    static boolean establishConnection() {
        try {
            Class.forName(Constants.DB_DRIVER);
            DriverManager.registerDriver(new OracleDriver());

            connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
            System.out.println("\nINFO> Database Connection is successful!");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("\nERROR> Database driver loading failed!...");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nERROR> Error occured while working on SQL!...");
            return false;
        }
    }

    //get username from database
    static void executeSQLSEARCH(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SEARCH_USER_SQL);
            preparedStatement.setString(1, id);

            int resultCount = preparedStatement.executeUpdate();
            if (resultCount > 0) {
                resultSet = preparedStatement.executeQuery();
                int i = 0;
                while (resultSet.next()) {
                    i++;
                    // SET username
                    Constants.USERNAME = resultSet.getString(1);
                    System.out.println("\nINFO> USERNAME: " + Constants.USERNAME);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nERROR> Error occured while working on SEARCH SQL!...");
        } finally {
            closeConnection();
        }
    }

    String getLatestTweetID(String username) {
        String LAST_TWEET_ID = null;
        if (username != null) {
            //get the latest tweet id from db for given username
            try {
                PreparedStatement statement = connection.prepareStatement(Constants.SEARCH_TWEETID_SQL);
                statement.setString(1, username);

                int resultCont = statement.executeUpdate();
                if (resultCont > 0) {
                    resultSet = statement.executeQuery();
                    int i = 0;
                    while (resultSet.next()) {
                        i++;
                        // Set tweet id
                        LAST_TWEET_ID = resultSet.getString(i);
                        System.out.println("\nINFO> LAST_TWEET_ID: " + LAST_TWEET_ID);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("\nERROR> Error occured while working on SEARCH SQL!...");
            }
        } else {
            System.out.println("\nERROR> USERNAME not found! Please check input ID value!");
        }
        return LAST_TWEET_ID;
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nINFO> Database Connection Closed!");
    }

    //insert values into database table user_id, tweet_id, tweet_data, create_date
    void executeSQLINSERT(String username, String tweet_id, String tweet_data, String created_date, String user_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.INSERT_SQL);

            statement.setString(1, username);
            statement.setString(2, tweet_id);
            statement.setString(3, tweet_data);
            statement.setString(4, created_date);
            statement.setString(5, user_id);

            int resultCount = statement.executeUpdate();
            if (resultCount > 0)
                System.out.println("INFO> " + resultCount + " Row(s) " + "Inserted!");
            else {
                System.out.println("\nINFO> No Rows INSERTED!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nERROR> Error occured while working on INSERT SQL!...");
        } finally {
            closeConnection();
        }
    }
}

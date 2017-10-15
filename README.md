# JavaTwitterApp

### JavaTwitterApp (working with Twitter API - TWITTER4J)

The main purpose of this project is "Checking a user's new tweets at certain intervals. If so, insert in a database table."

In this project you can find examples / answers of:
-	JDBC CRUD Operations (for more check [here](https://github.com/ercanduman/OracleDBConnect))
-	How to connect to Twitter API with JAVA ( for more api info : [TWITTER4J](http://twitter4j.org/en/))
-	Read a user's tweets (with Twitter user_id. i.e. “bbcturkce”)
-	Retrieve values from Twitter API and insert into database tables

There are 2 tables in database called eduman.twitter_users and eduman.tweets.

-- All users
SELECT * FROM eduman.twitter_users;
![image](https://user-images.githubusercontent.com/11629459/31339547-e5829538-ad0b-11e7-84e3-19927a19e61d.png)


 

--all tweets that published by users
SELECT * FROM eduman.tweets ORDER BY id ASC;
 
![image](https://user-images.githubusercontent.com/11629459/31339445-90b0cee4-ad0b-11e7-98a1-a6b7675a3bdd.png)


The app every given times (Constants.INTERVAL) checks for new tweets. If there is a new tweet published by user then the tweet inserted in eduman.tweets table with the information of    

-	id        		 -> primary key of table
-	username  		 -> the username of twitter user
-	tweet_id  	 	-> id that generated by Twitter app
-	tweet_data 	 -> the text/data value of tweet
-	create_date 	-> the tweet published timestamp
-	user_id    		-> database user id in eduman.twitter_users table

ANDROID STUDIO System OUTPUT:
```
***************************************** 
INFO> 1.RUN TIME: 2017-10-09T11:59:49.924Z

INFO> Database Connection is successful!

INFO> USERNAME: bbcturkce

INFO> Database Connection Closed!

INFO> Twitter API execution started!

INFO> Database Connection is successful!

INFO> LAST_TWEET_ID: 917355251950346240

INFO> No NEW tweets found!

INFO> Twitter API execution finished!

Process finished with exit code 1
```
OUTPUT: 
![image](https://user-images.githubusercontent.com/11629459/31339274-f5e87c22-ad0a-11e7-98f8-d83a167fc518.png)

 

If a new tweet found then the output looks as:

```
***************************************** 
INFO> 2.RUN TIME: 2017-10-09T12:14:58.069Z

INFO> Database Connection is successful!

INFO> USERNAME: bbcturkce

INFO> Database Connection Closed!

INFO> Twitter API execution started!

INFO> Database Connection is successful!

INFO> LAST_TWEET_ID: 917355251950346240
INFO> 1 Row(s) Inserted!

INFO> Database Connection Closed!

INFO> Twitter API execution finished!
Process finished with exit code 1
```	
OUTPUT: 

![image](https://user-images.githubusercontent.com/11629459/31339512-c1815afc-ad0b-11e7-8eb1-6c4c6b904949.png)


 


For twitter api TWITTER4J library used

For interval _Thread.sleep(INTERVAL);_  method used

## Using project

If you would like to use this project firstly run all db_files with given order of file name.
Secondly, you have to have a twitter account, and make a new app [here](https://apps.twitter.com))  
After creation of app then you will have OAUTH_ACCESS_TOKEN, OAUTH_ACCESS_TOKEN_SECRET, OAUTH_CONSUMER_KEY and OAUTH_CONSUMER_SECRET, just put the value of these variables in Constants class with related names

_static final String OAUTH_ACCESS_TOKEN = "Your access token here"; etc_

With this simple project you can download all tweets of a user in database and do analyses you would like (this can be used for Data Mining).

(Android Studio 2.3.3 IDE and PLSQL DEVELOPER 12.0.4 tools had been used while working on this project)

-- all users
SELECT * FROM eduman.twitter_users;

--all tweets that published by users
SELECT * FROM eduman.tweets ORDER BY id ASC;

--the latest tweet ids for each username
SELECT *
	FROM eduman.tweets t
 WHERE t.tweet_id IN (SELECT MAX(tweet_id)
												FROM eduman.tweets t2
											 WHERE t.username = t2.username);
TWITTER4J

--delete from eduman.tweets;
--alter table eduman.tweets add user_id number;

/*UPDATE eduman.tweets t
   SET user_id =
       (SELECT id
          FROM eduman.twitter_users tu
         WHERE t.username = tu.username);*/

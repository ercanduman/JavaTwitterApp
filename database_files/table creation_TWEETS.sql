create table EDUMAN.TWEETS  (
   ID  NUMBER PRIMARY KEY, -- USERNAME, TWEET, CREATE_DATE)
   USERNAME        VARCHAR2(25),
   TWEET_ID        VARCHAR2(50),
   TWEET_DATA      VARCHAR2 (1000),
   CREATE_DATE     VARCHAR2(50),
   USER_ID         NUMBER
);

--table comment
comment on table EDUMAN.TWEETS  is 'Stores all tweets which are published by users.';

--column comments
comment on column EDUMAN.TWEETS.ID is 'ID attribute defines unique identifier of TWEETS.';
comment on column EDUMAN.TWEETS.USERNAME is 'USERNAME attribute defines the username that is used in Twitter App. Refers to TWITTER_USERS.USERNAME';
comment on column EDUMAN.TWEETS.TWEET_ID is 'TWEET_ID attribute defines the identifier of each tweet created from Twitter.';
comment on column EDUMAN.TWEETS.TWEET_DATA is 'TWEET_DATA attribute defines all text/data available in a tweet.';
comment on column EDUMAN.TWEETS.CREATE_DATE is 'CREATE_DATE attribute defines time that the TWEETS has been published.';
comment on column EDUMAN.TWEETS.USER_ID is 'USER_ID attribute defines unique identifier of USERS in TWITTER_USERS table.';

--index
create index EDUMAN.i_TWEETS_id on EDUMAN.TWEETS(ID);

--instead of writing Operand_id values one by one, a sequence created
CREATE sequence EDUMAN.seq_tweets_id start with 1 increment by 1 cache 10 order nocycle;


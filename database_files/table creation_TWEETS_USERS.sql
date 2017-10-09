create table EDUMAN.TWITTER_USERS (
   ID  NUMBER PRIMARY KEY,
   USERNAME       VARCHAR2(25),
   CREATE_DATE    DATE
);

--table comment
comment on table EDUMAN.TWITTER_USERS  is 'Stores all USERS which have an account on Twitter App.';

--column comments
comment on column EDUMAN.TWITTER_USERS.ID is 'ID attribute defines unique identifier of TWITTER_USERS.';
comment on column EDUMAN.TWITTER_USERS.USERNAME is 'USERNAME attribute defines the username that is used in Twitter App.';
comment on column EDUMAN.TWITTER_USERS.CREATE_DATE is 'CREATE_DATE attribute defines time that the USER has been created in database table.';

--instead of writing Operand_id values one by one, a sequence created
CREATE sequence EDUMAN.seq_users_id start with 1 increment by 1 cache 10 order nocycle;


--SELECT * FROM eduman.TWITTER_USERS;

INSERT INTO eduman.twitter_users (id, username, create_date) values (eduman.seq_users_id.nextval, 'ercanistd' ,sysdate);
INSERT INTO eduman.twitter_users (id, username, create_date) values (eduman.seq_users_id.nextval, 'bbcturkce' ,sysdate);
commit;

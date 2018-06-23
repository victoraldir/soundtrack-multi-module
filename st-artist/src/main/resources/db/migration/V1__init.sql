DROP TABLE IF EXISTS ARTIST;

CREATE TABLE ARTIST (
   ID             VARCHAR(255) PRIMARY KEY NOT NULL,
   NAME           TEXT    NOT NULL,
   DESCRIPTION    TEXT    NOT NULL,
   ALBUMS_IDS     TEXT[]
);
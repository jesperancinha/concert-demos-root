DROP TABLE IF EXISTS Artist;
CREATE TABLE IF NOT EXISTS Artist
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50)        NOT NULL,
    gender       VARCHAR(50)        NOT NULL,
    birth_city   VARCHAR(50)        NOT NULL,
    country      VARCHAR(50)        NOT NULL,
    career_start VARCHAR(50)        NOT NULL,
    birth_date   VARCHAR(50)        NOT NULL,
    keywords     VARCHAR(500)       NOT NULL
);

DROP TABLE IF EXISTS Music;
CREATE TABLE IF NOT EXISTS Music
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(50) UNIQUE NOT NULL,
    lyrics VARCHAR(180)       NOT NULL
);

DROP TABLE IF EXISTS Listing;
CREATE TABLE IF NOT EXISTS Listing
(
    id                 SERIAL PRIMARY KEY,
    artist_id          BIGINT,
    reference_music_id BIGINT
);

DROP TABLE IF EXISTS Listing_Music;
CREATE TABLE IF NOT EXISTS Listing_Music
(
    id         SERIAL PRIMARY KEY,
    listing_id BIGINT,
    music_id   BIGINT
);

DROP TABLE IF EXISTS Concert;
CREATE TABLE IF NOT EXISTS Concert
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50) UNIQUE NOT NULL,
    location VARCHAR(50) UNIQUE NOT NULL,
    date     VARCHAR(50) UNIQUE NOT NULL

);


DROP TABLE IF EXISTS Concert_Listing;
CREATE TABLE IF NOT EXISTS Concert_Listing
(
    id         SERIAL PRIMARY KEY,
    concert_id BIGINT,
    listing_id BIGINT
);
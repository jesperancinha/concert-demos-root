DROP TABLE IF EXISTS Artist;
CREATE TABLE IF NOT EXISTS Artist
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(50) UNIQUE NOT NULL,
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
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(50) UNIQUE NOT NULL,
    lyrics VARCHAR(180)       NOT NULL
)
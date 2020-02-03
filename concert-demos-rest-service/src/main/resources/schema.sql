DROP TABLE Artist;
CREATE TABLE IF NOT EXISTS Artist
(
    id           INT PRIMARY KEY,
    name         VARCHAR(50) UNIQUE NOT NULL,
    gender       VARCHAR(50) UNIQUE NOT NULL,
    career_start TIMESTAMP          NOT NULL,
    birth_date   TIMESTAMP          NOT NULL,
    keywords     VARCHAR(500)       NOT NULL
);
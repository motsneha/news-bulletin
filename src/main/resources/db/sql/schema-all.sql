DROP TABLE NEWS IF EXISTS;

CREATE TABLE NEWS(
    id INT PRIMARY KEY,
    news_headline VARCHAR(255),
    url VARCHAR(255),
    source VARCHAR(200),
    news_type VARCHAR(1),
    link VARCHAR(100),
    date_of_happening VARCHAR)
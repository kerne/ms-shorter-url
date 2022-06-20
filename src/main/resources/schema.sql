CREATE TABLE IF NOT EXISTS shorter_url
(
    id            VARCHAR(255) PRIMARY KEY ,
    url_full VARCHAR(255),
    url_shorter  VARCHAR(255)
);

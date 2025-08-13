// sql 백업용
DROP DATABASE IF EXISTS board_proj;

CREATE DATABASE IF NOT EXISTS board_proj;
use board_proj;

CREATE TABLE article (
                         id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                         title varchar(100) NOT NULL,
                         content text NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE `member` (
                          id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                          username varchar(50) NOT NULL,
                          password varchar(100) NOT NULL,
                          name varchar(50) NOT NULL,
                          PRIMARY KEY (id)
);

INSERT INTO article(title,content)
VALUES
    ("제목1", "내용1"),
    ("제목2", "내용2"),
    ("제목3", "내용3");
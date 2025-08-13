// sql 백업용
DROP DATABASE IF EXISTS board_proj;

CREATE DATABASE IF NOT EXISTS board_proj;
use board_proj;

CREATE TABLE `members` (
                           id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                           username varchar(50) NOT NULL,
                           password varchar(100) NOT NULL,
                           name varchar(50) NOT NULL,
                           regDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (id)
);

CREATE TABLE articles (
                          id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                          title varchar(100) NOT NULL,
                          content text NOT NULL,
                          count bigint UNSIGNED NOT NULL DEFAULT 0,
                          regDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          author_id bigint UNSIGNED,
                          PRIMARY KEY (id),
                          CONSTRAINT fk_articles_author
                              FOREIGN KEY (author_id) REFERENCES members(id)
                                  ON DELETE SET NULL
                                  ON UPDATE CASCADE
);

INSERT INTO members(username, password, name, regDate)
VALUES
    ("user1", SHA2("1234", 256), "유저1", NOW()),
    ("user2", SHA2("1234", 256), "유저2", NOW()),
    ("user3", SHA2("1234", 256), "유저3", NOW());

INSERT INTO articles(title,content, regDate, author_id)
VALUES
    ("제목1", "내용1", NOW(), 1),
    ("제목2", "내용2", NOW(), 1),
    ("제목3", "내용3", NOW(), 1),
    ("제목4", "내용4", NOW(), 2);

SELECT * FROM articles;
SELECT * FROM members;
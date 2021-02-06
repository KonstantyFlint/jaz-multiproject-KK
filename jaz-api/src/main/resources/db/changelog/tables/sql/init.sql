CREATE TABLE trains (
    id              INT AUTO_INCREMENT,
    station         INT NOT NULL,
    cooldown        INT NOT NULL,
    forward         BOOL NOT NULL,
    PRIMARY KEY     (id)
);

CREATE TABLE carts (
    id              INT AUTO_INCREMENT,
    ordinal         INT NOT NULL,
    train_id        INT NOT NULL,
    capacity        INT NOT NULL,
    PRIMARY KEY     (id),
    FOREIGN KEY (train_id)
        REFERENCES trains(id)
        ON DELETE CASCADE
);

CREATE TABLE users (
    id          int auto_increment,
    username varchar(300),
    password varchar(300),
    authorities varchar(300),
    PRIMARY KEY (id)
);
CREATE TABLE users (
    id INT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_email_unique UNIQUE (email)
);

create table games
(
    id          INT auto_increment,
    name       varchar(255) not null
        constraint games_pk_2
            unique,
    description varchar(200000),
    rules       varchar(200000),
    max_players  INT,
    primary key (id)
);

CREATE TABLE tournaments (
                             id INT AUTO_INCREMENT,
                             title VARCHAR(255) NOT NULL,
                             date timestamp NOT NULL,
                             location VARCHAR(100) NOT NULL,
                             type VARCHAR(50) NOT NULL,
                             status VARCHAR(50) NOT NULL,
                             free_places INT NOT NULL,
                             game_id INT NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);

create table results
(
    id         INT auto_increment,
    user_id  INT not null,
    tournament_id INT not null,
    position   INT not null,
    score      INT,
    primary key (id),
    foreign key (tournament_id) references TOURNAMENTS (ID) ON DELETE CASCADE,
    foreign key (user_id) references USERS (ID) ON DELETE CASCADE
);

create table registrations
(
    id INT AUTO_INCREMENT,
    date       datetime    not null,
    status     ENUM('ČEKAJÍCÍ', 'POTVRZENO', 'ZRUŠENO') NOT NULL,
    note        VARCHAR(255),
    user_id     INT not null,
    tournament_id INT not null,
    PRIMARY KEY (id),
    foreign key (tournament_id) references TOURNAMENTS (ID) ON DELETE CASCADE,
    foreign key (user_id) references USERS (ID) ON DELETE CASCADE
);




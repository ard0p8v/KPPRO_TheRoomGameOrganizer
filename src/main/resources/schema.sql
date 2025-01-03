CREATE TABLE users (
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT user_email_unique UNIQUE (email)
);

create table games
(
    id          INTEGER auto_increment,
    name       varchar(255) not null
        constraint games_pk_2
            unique,
    description varchar(200000),
    rules       varchar(200000),
    max_players  INT,
    constraint games_pk
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
                             FOREIGN KEY (game_id) REFERENCES games(id)
);

create table results
(
    id         INTEGER auto_increment,
    user_id  INTEGER not null,
    tournament_id INTEGER not null,
    position   INTEGER not null,
    score      INTEGER,
    constraint results_pk
        primary key (id),
    constraint results_TOURNAMENTS_ID_fk
        foreign key (tournament_id) references TOURNAMENTS (ID) ON DELETE CASCADE,
    constraint results_USERS_ID_fk
        foreign key (user_id) references USERS (ID) ON DELETE CASCADE
);

create table registrations
(
    id INT AUTO_INCREMENT,
    date       datetime    not null,
    status     ENUM('ČEKAJÍCÍ', 'POTVRZENO', 'ZRUŠENO') NOT NULL,
    note        VARCHAR(255),
    user_id     INTEGER not null,
    tournament_id INTEGER not null,
    PRIMARY KEY (id),
    constraint registrations_TOURNAMENTS_ID_fk
        foreign key (tournament_id) references TOURNAMENTS (ID) ON DELETE CASCADE,
    constraint registrations_USERS_ID_fk
        foreign key (user_id) references USERS (ID) ON DELETE CASCADE
);




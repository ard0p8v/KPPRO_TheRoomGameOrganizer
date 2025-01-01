CREATE TABLE users (
                       id INTEGER AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       surname VARCHAR(100) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       CONSTRAINT user_pk PRIMARY KEY (id),
                       CONSTRAINT user_email_unique UNIQUE (email)
);

create table games
(
    id          INTEGER auto_increment,
    title       varchar(255) not null
        constraint games_pk_2
            unique,
    description varchar(200000),
    rules       varchar(200000),
    max_players  INTEGER,
    constraint games_pk
        primary key (id)
);

create table tournaments
(
    id       INTEGER auto_increment,
    name     varchar(255) not null,
    date     date    not null,
    location varchar(100) not null,
    type     varchar(50) not null,
    status   varchar(50) not null,
    game     INTEGER not null,
    constraint tournaments_pk
        primary key (id),
    constraint tournaments_GAMES_ID_fk
        foreign key (game) references GAMES
);

create table results
(
    id         INTEGER auto_increment,
    player       INTEGER not null,
    tournament INTEGER not null,
    position   INTEGER not null,
    score      INTEGER,
    constraint results_pk
        primary key (id),
    constraint results_TOURNAMENTS_ID_fk
        foreign key (tournament) references TOURNAMENTS (ID),
    constraint results_USERS_ID_fk
        foreign key (player) references USERS (ID)
);

create table registrations
(
    id         INTEGER auto_increment,
    date       date    not null,
    status     varchar not null,
    player     INTEGER not null,
    tournament INTEGER not null,
    constraint registrations_pk
        primary key (id),
    constraint registrations_TOURNAMENTS_ID_fk
        foreign key (tournament) references TOURNAMENTS,
    constraint registrations_USERS_ID_fk
        foreign key (player) references USERS
);




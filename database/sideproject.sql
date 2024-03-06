drop table if exists tv_show, app_user cascade;

create table tv_show (
tv_show_id serial,
tv_show_name varchar(100) not null,
tv_show_streaming_service varchar(100) not null,
tv_show_genre1 varchar(100) not null,
tv_show_genre2 varchar(100),
tv_show_description varchar(10000),
tv_show_image varchar(1000),
episode_count int,
season_count int,

constraint PK_tv_show primary key (tv_show_id),
constraint UQ_tv_show_name unique (tv_show_name)
);

create table app_user (
user_id serial,
username varchar(50) NOT NULL,
password_hash varchar(200) NOT NULL,
role varchar(50) NOT NULL,
user_choices varchar(1000),
CONSTRAINT PK_app_user PRIMARY KEY (user_id),
CONSTRAINT UQ_username UNIQUE (username)
);


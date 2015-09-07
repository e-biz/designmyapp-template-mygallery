create database `mygallery-db`;

use `mygallery-db`;

create table image(
       id bigint not null auto_increment primary key,
       full_size_path varchar(255),
       thumbnail_path varchar(255)
);

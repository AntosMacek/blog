# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint not null,
  title                     varchar(255),
  author                    varchar(255),
  content                   varchar(255),
  date                      varchar(255),
  constraint pk_post primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence post_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists post;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists post_seq;

drop sequence if exists user_seq;


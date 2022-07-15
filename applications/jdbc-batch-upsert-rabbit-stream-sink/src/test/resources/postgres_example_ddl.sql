drop TABLE blackAt.members;

create schema IF NOT EXISTS blackAt;

create table blackAt.members(
    MEMBER_ID varchar(20),
    MEMBER_NM varchar(50)
);

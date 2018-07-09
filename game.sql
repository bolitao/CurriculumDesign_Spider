-- auto-generated definition
create table basic_information
(
  mainKey        varchar(32)  not null primary key,
  name           varchar(200) not null,
  metaScore      double       not null,
  releaseDate    varchar(30)  not null,
  maturityRating varchar(50)  null,
  publisher      varchar(100) null,
  genre          varchar(300) null,
  userScore      double       null,
  platform       varchar(30)  not null,
  imgUrl         varchar()    not null
);


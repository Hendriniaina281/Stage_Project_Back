TRUNCATE TABLE users RESTART IDENTITY cascade;
TRUNCATE TABLE academy RESTART IDENTITY cascade;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE academy_id_seq RESTART WITH 1;



drop database educom;
create database educom;
\c educom

----------   Token ----------------

create table token (
    id serial primary key,
    token text not null,
    cle text not null,
    datecreation timestamp not null,
    dateexpiration timestamp not null
);


----------   Users et academy -----------------------
create table profil(
    id serial primary key,
    profil varchar(50)
);

insert into profil values (default,'Ecole');
insert into profil values (default,'Universite');
insert into profil values (default,'Professeur');
insert into profil values (default,'Parent');
insert into profil values (default,'Eleve');

create table users(
    id serial primary key,
    nom varchar(50),
    prenom varchar,
    adresse varchar,
    dateNaissance date,
    adminNom varchar(50),
    email varchar(50),
    mdp varchar(50),
    profil_id int,
    foreign key (profil_id) references profil(id)
);

--insert into users values (default,'Twelve','twelve@gmail.com','222',1);

create table types(
    id serial primary key,
    types varchar(50)
);

insert into types values (default,'Ecole');
insert into types values (default,'Universite');

create table academy(
    id serial primary key,
    nom varchar,
    adresse text,
    description text,
    numTel varchar,
    email varchar,
    dateCreationEcole date,
    dateInscriptionEcole date default now(),
    prefixe varchar default '',
    logo text,
    idtype int,
    foreign key (idtype) references types(id)
);

create table academyusers (
    id serial primary key,
    users_id int,
    academy_id int,
    foreign key (academy_id) references academy(id),
    foreign key (users_id) references users(id)
);




--------  Professeur ---------------

create table Professeur (
    id serial primary key,
    users_id int,
    nomPrenom Text,
    numTel varchar,
    foreign key (users_id) references users(id)
);
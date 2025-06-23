create database educom;
\c educom;

---------ECOLE-----------
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
    users_id int,
    nom varchar(50),
    adresse varchar(50),
    description text,
    numTel varchar(50),
    email varchar(50),
    dateCreationEcole date,
    dateInscriptionEcole date,
    prefixe varchar(50),
    logo varchar(50),
    idtype int,
    foreign key (idtype) references types(id),
    foreign key (users_id) references users(id)
);

insert into academy values (default,3,'t','t','tt','021','e','01-01-2023','01-02-2001','s','cdn',1);

create table anneeScolaire(
    id serial primary key,
    idecole int,
    debutAnneeScolaire date,
    finAnneeScolaire date,
    foreign key (idecole) references academy(id)
);

create table classe(
    id serial primary key,
    classe varchar(50)
);

insert into classe values (default,'6eme');
insert into classe values (default,'5eme');
insert into classe values (default,'4eme');
insert into classe values (default,'3eme');
insert into classe values (default,'2nde');
insert into classe values (default,'1ere S');
insert into classe values (default,'1ere L');
insert into classe values (default,'Tle S');
insert into classe values (default,'Tle L');

create table ecoleClasse(
    id serial primary key,
    idecole int,
    idclasse int,
    foreign key (idecole) references academy(id),
    foreign key (idclasse) references classe(id)
);
---------view--------
create view v_ecoleClasse as
select ecoleclasse.id,academy.id as idecole,academy.users_id as iduser,academy.nom,academy.adresse,academy.description,classe.id as idclasse,classe.classe from ecoleclasse
join academy on ecoleclasse.idecole = academy.id
join classe on ecoleclasse.idclasse = classe.id;


----NEW---
create table matiere(
    id serial primary key,
    matiere varchar(50)
);

insert into matiere values (default,'HG');
insert into matiere values (default,'MLG');
insert into matiere values (default,'FR');
insert into matiere values (default,'MTH');

create table professeur(
    id serial primary key,
    users_id int,
    nomPrenom text,
    numTel varchar(20),
    foreign key (users_id) references users(id)
);

create table matiereClasse(
    id serial primary key,
    ecoleClasse_id int,
    matiere_id int,
    dureeSemaine time,
    coefficient decimal(10,2),
    professeur_id int,
    foreign key (ecoleClasse_id) references ecoleClasse(id),
    foreign key (matiere_id) references matiere(id),
    foreign key (professeur_id) references professeur(id)
);

insert into matiereClasse values (default,1,1,'01:00:00',3,1);

----------TABLE TEMPORAIRE
create table csvMatiereClasse(
    id serial primary key,
    matiere varchar(150),
    professeur varchar(250),
    coefficient int,
    dureeSemaine time
);




create table elevesEcole(
    id serial primary key,
    nom varchar(50),
    prenom varchar(50),
    dateNaissance date,
    pere varchar(50),
    mere varchar(50),
    numTelParent varchar(50),
    tuteur varchar(50),
    numTelTuteur varchar(50),
    adresse varchar(50)
);

create table elevesClasse(
    id serial primary key,
    ecoleClasse_id int,
    elevesEcole_id int,
    anneeScolaire_id int,
    foreign key (ecoleClasse_id) references ecoleClasse(id),
    foreign key (elevesEcole_id) references elevesEcole(id),
    foreign key (anneeScolaire_id) references anneeScolaire(id)
);




create table anneesuniversitaire (
    id serial primary key,
    universite_id int references academy(id),
    debut date,
    fin date
);

insert into anneesuniversitaire VALUES 
    (default , 1 , '2023-11-01' , '2024-07-08');

ALTER TABLE academy
ALTER COLUMN dateinscriptionecole SET DEFAULT NOW();

create table diplomeExistant (
    id serial primary key,
    universite_id int references academy(id),
    diplome varchar
);

insert INTO diplomeExistant VALUES
(default , 1 , 'Licence'),
(default , 1 , 'Masteur');

create table date_semestrielle (
    id serial primary key,
    anneesuniversitaire_id int references anneesuniversitaire(id),
    debut date default now(),
    fin date default now()
);

create table filiere_universite(
    id serial primary key,
    universite_id int,
    filiere text,
    description text,
    foreign key (universite_id) references academy(id)
);

create table semestre(
    id serial primary key,
    universite_id int references academy(id),
    semestre varchar
);

create table matiere_semestre_par_filiere(
    id serial primary key,
    filiere_universite_id int,
    num_matiere int,
    nom_matiere text,
    code_matiere TEXT UNIQUE,
    credit double precision,
    semestre_id int,
    foreign key (filiere_universite_id) references filiere_universite(id),
    foreign key (semestre_id) references semestre(id)
);

----------- Creation du table modele de l importation --------------------
create table importation_matiere (
    id serial primary key,
    filiere text,
    semestre varchar,
    num_matiere int,
    matiere text,
    code_matiere varchar,
    credits int
);

---------  demande inscription et inscrite ----------

create table etudiant(
    id serial primary key,
    nom varchar,
    prenom varchar,
    datenaissance date,
    adresse varchar,
    email varchar,
    contact varchar,
    Parent_tuteur text,
    photo text,
    motivation text,
    status int default 0,
    classe_filiere text,
    annee_semestre text default '',
    user_incripteur int references users(id)
);
create table fileEtudiant (
    id serial primary key,
    etudiant_id int references etudiant(id),
    file_url text,
    description text default 'Aucun Description'
);

create table etudiant_universite (
    id serial primary key,
    etudiant_id int references etudiant(id),
    numUnique varchar UNIQUE,
    universite_id int references academy(id),
    filiere_universite_id int references filiere_universite(id),
    anneesuniversitaire_id int references anneesuniversitaire(id),
    semestre_id int references semestre(id)
);

create table etudiant_ecole (
    id serial primary key,
    etudiant_id int references etudiant(id),
    numUnique varchar UNIQUE,
    ecole_id int references academy(id),
    classe_id int references classe(id),
    anneescolaire_id int references anneescolaire(id)
);

---------- Professeur --------

create table academy_enseignement (
    id serial primary key,
    professeur_id int references professeur(id),
    academy_id int references academy(id),
    code_matiere Text references matiere_semestre_par_filiere(code_matiere),
    date_recrutement date default now()
);

create table cours_instruit (
    id serial primary key,
    academy_enseignement_id int references academy_enseignement(id),
    date_semestrielle_id int references date_semestrielle(id),
    description text,
    files BYTEA,
    date_du_cours date  
);
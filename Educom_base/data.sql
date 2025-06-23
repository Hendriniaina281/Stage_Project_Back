-- Insérer des données dans la table users
INSERT INTO users (nom, prenom, adresse, dateNaissance, adminNom, email, mdp, profil_id) VALUES 
('Doe', 'John', '123 Main St', '1990-01-01', 'Admin', 'johndoe@example.com', '123', 1),
('Smith', 'Jane', '456 Oak Ave', '1985-05-15', 'Admin', 'janesmith@example.com', '456', 2),
('Brown', 'Charlie', '789 Pine Rd', '1992-10-20', 'Admin', 'charliebrown@example.com', '789', 3);


INSERT INTO users (nom, prenom, adresse, dateNaissance, adminNom, email, mdp, profil_id) VALUES 
('Mark', 'Jonson', '123 Antananarivo', '1990-01-01', 'eleve', 'mark@example.com', 'E123', 5);

-- Insérer des données dans la table academy
INSERT INTO academy (nom, adresse, description, numTel, email, dateCreationEcole, dateInscriptionEcole, prefixe, logo, idtype) VALUES 
('ABC University', '123 College St', 'Leading university in sciences', '123-456-7890', 'info@abcuniversity.edu', '2000-09-01', '2024-08-23', 'ABC', 'abc_logo.png', 2),
('XYZ School', '456 School Ln', 'Top-rated high school', '098-765-4321', 'contact@xyzschool.org', '1995-09-01', '2024-08-23', 'XYZ', 'xyz_logo.png', 1);

-- Insérer des données dans la table academyusers
INSERT INTO academyusers (users_id, academy_id) VALUES 
(1, 1),
(2, 1),
(3, 2);


-- Insérer des données dans la table filiere_universite
INSERT INTO filiere_universite (universite_id, filiere, description)
VALUES (1, 'Informatique', 'Filière d études en informatique'),
       (1, 'Mathematiques', 'Filière d études en mathématiques');

-- Insérer des données dans la table semestre
INSERT INTO semestre (semestre)
VALUES ('Semestre 1'), ('Semestre 2'), ('Semestre 3'), ('Semestre 4');

-- Insérer des données dans la table matiere_semestre_par_filiere
INSERT INTO matiere_semestre_par_filiere (filiere_universite_id , num_matiere, nom_matiere, code_matiere, credit, semestre_id)
VALUES (1, 1, 'Programmation Java','INF101', 6.0, 1),
       (1, 2, 'Algèbre Linéaire' , 'MTH101', 5.0, 1),
       (2, 3, 'Analyse Mathématique' , 'MTH102', 4.5, 2);

-- Insérer des données dans la table importation_matiere
INSERT INTO importation_matiere (filiere, semestre, num_matiere, matiere, code_matiere, credits)
VALUES ('Informatique', 'Semestre 1', 1, 'Programmation Java', 'INF101', 6),
       ('Informatique', 'Semestre 1', 2, 'Algèbre Linéaire', 'MTH101', 5),
       ('Mathematiques', 'Semestre 2', 3, 'Analyse Mathématique', 'MTH102', 4.5);

UPDATE matiere_semestre_par_filiere
SET filiere_universite_id = 2
WHERE filiere_universite_id = 1;

DELETE FROM filiere_universite
WHERE id NOT IN (
    SELECT MIN(id)
    FROM filiere_universite
    GROUP BY filiere
);


INSERT INTO semestre (universite_id, semestre)
           SELECT DISTINCT 1, im.semestre
           FROM importation_matiere im
           JOIN filiere_universite fu ON im.filiere = fu.filiere
           WHERE fu.universite_id = 1;


ALTER TABLE academy
DROP COLUMN users_id;


insert into ecoleclasse VALUES 
(default , 1 , 2),
(default , 2 , 2),
(default , 3 , 2),
(default , 4 , 2),
(default , 5 , 2),
(default , 6 , 2),
(default , 7 , 2),
(default , 8 , 2),
(default , 9 , 2);
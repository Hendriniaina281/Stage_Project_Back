-- Création de la table nos_service
CREATE TABLE nos_service (
    id SERIAL PRIMARY KEY,
    services TEXT,
    categorie VARCHAR(100),
    description TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Création de la table politique
CREATE TABLE politique (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    categorie VARCHAR(100),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Création de la table a_propos
CREATE TABLE a_propos (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telephone VARCHAR(20),
    adresse VARCHAR(255),
    description TEXT NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Insertion des données dans nos_service
INSERT INTO nos_service (services, categorie, description)
VALUES
    ('Communication simplifiee', 'Parents et eleves', 'Une plateforme qui simplifie les echanges entre les parents et l ecole.'),
    ('Suivi des performances', 'Parents et eleves', 'Un suivi detaille des notes et absences des enfants.'),
    ('Acces aux ressources', 'Parent et eleve', 'Une bibliotheque numerique pour acceder à des ressources pedagogiques.'),
    ('Activites parascolaires', 'Parent et eleve', 'Suivi de l engagement des eleves dans les activites parascolaires.'),
    ('Suivi des etudiants', 'Academy', 'Une plateforme pour suivre le parcours academique des etudiants.'),
    ('Progres academiques', 'Academy', 'Un tableau de bord pour les progres des etudiants.'),
    ('Relations professeur-etudiant', 'Professeur', 'Renforcement des interactions entre professeurs et etudiants.'),
    ('Proposition de cours', 'Professeurs', 'Outil pour proposer et gerer les cours.');

-- Insertion des données dans a_propos
INSERT INTO a_propos (titre, email, telephone, adresse, description)
VALUES
    ('Support Technique', 'support@academie.com', '0987654321', '45 Avenue du Savoir, Lyon', 'Nous fournissons une assistance technique 24/7 pour garantir la continuité des services.'),
    ('Développement et Innovation', 'innovation@academie.com', '0156789345', '56 Boulevard des Sciences, Marseille', 'Nous sommes à la pointe de l innovation pour améliorer continuellement notre plateforme.');

-- Insertion des données dans politique
INSERT INTO politique (titre, description, categorie)
VALUES
    ('Politique de confidentialité', 'Nous respectons votre vie privée et protégeons vos informations personnelles.', 'Confidentialité'),
    ('Conditions d utilisation', 'En utilisant notre plateforme, vous acceptez nos conditions générales d utilisation.', 'Utilisation'),
    ('Politique de cookies', 'Nous utilisons des cookies pour améliorer votre expérience utilisateur.', 'Cookies'),
    ('Politique de remboursement', 'Les remboursements sont traités selon nos conditions et sous certaines circonstances.', 'Remboursement');

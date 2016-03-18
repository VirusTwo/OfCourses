DELETE FROM ob_classe;
DELETE FROM ob_connexion;
DELETE FROM ob_cours;
DELETE FROM ob_edt;
DELETE FROM ob_matiere;
DELETE FROM ob_note;
DELETE FROM ob_personne;
DELETE FROM ob_pointbonus;
DELETE FROM ob_salle;

/* OB_CONNEXION */
INSERT INTO ob_connexion
VALUES(1,'guizmo', '123');

INSERT INTO ob_connexion
VALUES(2,'cyril', '1234');

INSERT INTO ob_connexion
VALUES(3,'lydia', '123');

INSERT INTO ob_connexion
VALUES(4,'alexis', '123');

INSERT INTO ob_connexion
VALUES(5,'simon', '123');

INSERT INTO ob_connexion
VALUES(6,'antoine', '123');

INSERT INTO ob_connexion
VALUES(7,'bastien', '123');

INSERT INTO ob_connexion
VALUES(8,'julien', '123');

INSERT INTO ob_connexion
VALUES(9,'valentin', '123');

INSERT INTO ob_connexion
VALUES(10,'alain', '123');

INSERT INTO ob_connexion
VALUES(11,'jonathan', '123');

INSERT INTO ob_connexion
VALUES(12,'clement', '123');

/*OB_SALLE */
INSERT INTO ob_salle
VALUES(1,'I208');

/*OB_MATIERE */
INSERT INTO ob_matiere
VALUES(1, 'Java', 8);

/*OB_CLASSE */
INSERT INTO ob_classe
VALUES(1, '3C1');

INSERT INTO ob_classe
VALUES(2, '3C2');

/*classe C1   */
INSERT INTO ob_personne
SELECT 1, 'MARTINEZ', 'Guizmo', 'guizmo@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 1;

INSERT INTO ob_personne
SELECT 1, 'LEMONIER', 'Simon', 'l.simon@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 5;

INSERT INTO ob_personne
SELECT 1, 'TOUTIN', 'Antoine', 't.antoine@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 6;


INSERT INTO ob_personne
SELECT 1, 'GY', 'Bastien', 'g.bastien@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 7;

INSERT INTO ob_personne
SELECT 1, 'GAUTIER', 'Valentin', 'g.valentin@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 9;

INSERT INTO ob_personne
SELECT 1, 'MARIATHAS', 'Alain', 'm.alain@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 10;

INSERT INTO ob_personne
SELECT 1, 'NADARAJAH', 'Jonathan', 'n.jonathan@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 1
AND Co.id_co = 11;



/*classe C2   */
INSERT INTO ob_personne
SELECT 1, 'CREBOUW', 'Cyril', 'c.cyril@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 2
AND Co.id_co = 2;

INSERT INTO ob_personne
SELECT 1, 'OULD OUALI', 'Lydia', 'o.lydia@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 2
AND Co.id_co = 3;

INSERT INTO ob_personne
SELECT 1, 'Pokorski', 'Alexis', 'p.alexis@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 2
AND Co.id_co = 4;

INSERT INTO ob_personne
SELECT 1, 'LACROIX', 'Julien', 'l.julien@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 2
AND Co.id_co = 8;

INSERT INTO ob_personne
SELECT 1, 'SAUVARD', 'Clement', 'l.julien@mail.fr', 'etudiant', ref(Cl), null, ref(Co)
FROM ob_classe Cl, ob_connexion Co
WHERE Cl.id_classe = 2
AND Co.id_co = 8;



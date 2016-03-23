DELETE FROM ob_classe;
DELETE FROM ob_connexion;
DELETE FROM ob_cours;
DELETE FROM ob_edt;
DELETE FROM ob_matiere;
DELETE FROM ob_note;
DELETE FROM ob_personne;
DELETE FROM ob_pointbonus;
DELETE FROM ob_salle;

INSERT INTO ob_salle
VALUES(1,'I208');

INSERT INTO ob_matiere
VALUES(1, 'Java', 8);

INSERT INTO ob_classe
VALUES(1, '3C');

INSERT INTO ob_personne
SELECT 1, 'MARTINEZ', 'Guizmo', 'guizmo@mail.fr', 'enseignant', ref(Cl), null
FROM ob_classe Cl
WHERE Cl.id_classe = 1;
/

INSERT INTO ob_connexion
SELECT 1,'guizmo', 'coucou', ref(E)
FROM ob_personne E
WHERE id_personne = 1;
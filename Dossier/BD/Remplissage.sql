DELETE FROM ob_classe;
DELETE FROM ob_connexion;
DELETE FROM ob_cours;
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
SELECT 1, 'MARTINEZ', 'Guizmo', 'guizmo@mail.fr', 'enseignant', ref(Cl)
FROM ob_classe Cl
WHERE Cl.id_classe = 1;

INSERT INTO ob_connexion
SELECT 1,'guizmo', 'coucou', ref(E)
FROM ob_personne E
WHERE id_personne = 1;

INSERT INTO ob_cours
SELECT 1, TO_DATE('2016/03/24 8:00:00', 'YYYY/MM/DD HH:MI:SS'), TO_DATE('2016/03/24 9:00:00', 'YYYY/MM/DD HH:MI:SS'), ref(M), ref(C), ref(S), ref(E)
FROM ob_matiere M, ob_classe C, ob_salle S, ob_personne E
WHERE M.id_matiere = 1
AND C.id_classe = 1
AND S.id_salle = 1
AND E.id_personne = 1;

INSERT INTO ob_note
SELECT 1, 1, 20, 'CC', ref(M), ref(E)
FROM ob_matiere M, ob_personne E
WHERE id_matiere = 1
AND id_personne = 1;
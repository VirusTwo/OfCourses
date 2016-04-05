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
VALUES(1, 'Poo', 8);

INSERT INTO ob_matiere
VALUES(2, 'Maths', 8);

INSERT INTO ob_classe
VALUES(1, '3C');

INSERT INTO ob_personne
SELECT 1, 'MARTINEZ', 'Guizmo', 'guizmo@mail.fr', 'enseignant', ref(Cl)
FROM ob_classe Cl
WHERE Cl.id_classe = 1;

INSERT INTO ob_personne
SELECT 2, 'MARTINEZ', 'GuizmoEtudiant', 'guizmo@mail.fr', 'etudiant', ref(Cl)
FROM ob_classe Cl
WHERE Cl.id_classe = 1;

INSERT INTO ob_personne
SELECT 3, 'LAMERE', 'SimonEtudiant', 'guizmo@mail.fr', 'etudiant', ref(Cl)
FROM ob_classe Cl
WHERE Cl.id_classe = 1;

INSERT INTO ob_connexion
SELECT 1,'a', 'a', ref(E)
FROM ob_personne E
WHERE id_personne = 1;

INSERT INTO ob_cours
SELECT 1, TO_DATE('2016/03/21 8:00:00', 'YYYY/MM/DD HH24:MI:SS'), 9, 10, ref(M), ref(C), ref(S), ref(E)
FROM ob_matiere M, ob_classe C, ob_salle S, ob_personne E, dual
WHERE M.id_matiere = 1
AND C.id_classe = 1
AND S.id_salle = 1
AND E.id_personne = 1;

INSERT INTO ob_cours
SELECT 2, TO_DATE('2016/03/24 14:00:00', 'YYYY/MM/DD HH24:MI:SS'), 14, 15, ref(M), ref(C), ref(S), ref(E)
FROM ob_matiere M, ob_classe C, ob_salle S, ob_personne E, dual
WHERE M.id_matiere = 1
AND C.id_classe = 1
AND S.id_salle = 1
AND E.id_personne = 1;

INSERT INTO ob_note
SELECT 1, 1, 20, 'CC1', ref(M), ref(E)
FROM ob_matiere M, ob_personne E
WHERE id_matiere = 1
AND id_personne = 2;

INSERT INTO ob_note
SELECT 2, 1, 3.5, 'CC1', ref(M), ref(E)
FROM ob_matiere M, ob_personne E
WHERE id_matiere = 1
AND id_personne = 3;

INSERT INTO ob_note
SELECT 3, 1, 19, 'DS1', ref(M), ref(E)
FROM ob_matiere M, ob_personne E
WHERE id_matiere = 1
AND id_personne = 2;

INSERT INTO ob_note
SELECT 4, 1, 3, 'DS1', ref(M), ref(E)
FROM ob_matiere M, ob_personne E
WHERE id_matiere = 1
AND id_personne = 3;

INSERT INTO ob_pointBonus
SELECT 1, '', ref(E), -1, ref(M)
FROM ob_personne E, ob_matiere M
WHERE id_personne = 2
and id_matiere = 1;

INSERT INTO ob_pointBonus
SELECT 2, 'abc', ref(E), 5, ref(M)
FROM ob_personne E, ob_matiere M
WHERE id_personne = 3
and id_matiere = 1;

COMMIT;
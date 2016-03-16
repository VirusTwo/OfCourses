DROP table ob_connexion;
/
DROP table ob_matiere;
/
DROP table ob_classe;
/
DROP table ob_note;
/
DROP table ob_cours;
/
DROP table ob_edt;
/
DROP table ob_personne;
/
DROP table ob_salle;
/
DROP table ob_pointBonus;
/

DROP type ob_EDT_ty FORCE;
/
DROP TYPE ob_cours_ty FORCE;
/
DROP TYPE ob_classe_ty FORCE;
/
DROP type ob_salle_ty FORCE;
/
DROP TYPE ob_note_ty FORCE;
/
DROP type ob_matiere_ty FORCE;
/
DROP type ob_pointBonus_ty FORCE;
/
DROP TYPE ob_personne_ty FORCE;
/


Create or replace Type ob_connexion_ty AS Object (
    id_co NUMBER(5),
    login VARCHAR2(20),
    mdp VARCHAR2(20),
    sonEnseignant ref ob_personne_ty
);
/

Create or replace Type ob_salle_ty AS Object (
    id_salle INTEGER,
    num VARCHAR2(20)
);
/

Create or replace Type ob_matiere_ty AS Object (
    id_matiere INTEGER,
    nom VARCHAR2(20),
    coeff FLOAT
);
/

Create or replace Type ob_EDT_ty AS Object (
    id_Edt INTEGER,
    MEMBER PROCEDURE getSesCours(cours out SYS_REFCURSOR)
);
/

Create or replace Type ob_classe_ty AS Object (
    id_classe INTEGER,
    nom VARCHAR2(20),
    MEMBER FUNCTION getMoyenne RETURN FLOAT
);
/

Create or replace Type ob_personne_ty AS Object (
    id_personne INTEGER,
    nom VARCHAR2(20),
    prenom VARCHAR2(20),
    email VARCHAR2(20),
    fonction VARCHAR2(20),
    saClasse REF ob_classe_ty,   
    sonEdt REF ob_EDT_ty
    /*MEMBER FUNCTION getMoyenne RETURN FLOAT,
    MEMBER PROCEDURE getSesNotes(notes out SYS_REFCURSOR),
    MEMBER PROCEDURE gererPointBonus(etudiant in ob_personne_ty, point in INTEGER, description in VARCHAR2),
    MEMBER PROCEDURE ajouterNote(etudiant in ob_personne_ty, matiere in ob_matiere_ty, note in float)*/
);
/

Create or replace Type ob_connexion_ty AS Object (
    id_co NUMBER(5),
    login VARCHAR2(20),
    mdp VARCHAR2(20),
    sonEnseignant REF ob_personne_ty
);
/

Create or replace Type ob_cours_ty AS Object (
    id_cours INTEGER,
    dateDebut DATE,
    dateFin  DATE,
    saMatiere REF ob_matiere_ty,
    saClasse REF ob_classe_ty,
    saSalle REF ob_salle_ty,
    sonEDT REF ob_EDT_ty
);
/

Create or replace Type ob_pointBonus_ty AS Object (
    id_pointBonus INTEGER,
    description VARCHAR2(20),
    sonEtudiant REF ob_personne_ty,
    point INTEGER
);
/

Create or replace Type ob_note_ty AS Object (
    id_note INTEGER,
    coeff FLOAT,
    note FLOAT,
    saMatiere REF ob_matiere_ty,
    sonEtudiant REF ob_personne_ty
);
/
    
          
CREATE TABLE ob_connexion of ob_connexion_ty
(id_co PRIMARY KEY);
/

CREATE TABLE ob_salle of ob_salle_ty
(id_salle PRIMARY KEY);
/

CREATE Table ob_matiere of ob_matiere_ty
(id_matiere PRIMARY KEY);
/

CREATE Table ob_pointBonus of ob_pointBonus_ty
(id_pointBonus PRIMARY KEY);
/

CREATE Table ob_note of ob_note_ty
(id_note PRIMARY KEY);
/

CREATE TABLE ob_classe of ob_classe_ty
(id_classe PRIMARY KEY);
/

CREATE TABLE ob_cours of ob_cours_ty
(id_cours PRIMARY KEY);
/

CREATE TABLE ob_personne of ob_personne_ty
(id_personne PRIMARY KEY);
/

CREATE Table ob_edt of ob_edt_ty
(id_edt PRIMARY KEY);
/
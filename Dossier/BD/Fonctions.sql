create or replace function getNomSaClasse(id in ob_personne.id_personne%type) return ob_classe.nom%type is
  res ob_classe.nom%type;
  begin
    select (deref(saClasse)).nom into res
    from ob_personne P
    where id_personne = id;
    return res;
  end;
/

create or replace function isLogin(l in ob_connexion.login%type, m in ob_connexion.mdp%type) return ob_personne.id_personne%type is
  res ob_personne.id_personne%type := -1;
  begin
    select (deref(sonEnseignant)).id_personne into res
    from ob_connexion C
    where login = l
    and mdp = m;
    
    return res;
  end;
/

create or replace procedure getCours(id in ob_personne.id_personne%type, res out sys_refcursor) as
  begin
    open res for
      select dateDebut, dateFin, (deref(saMatiere)).nom, (deref(saClasse)).nom, (deref(saSalle)).num
      from ob_cours C
      where (deref(sonEnseignant)).id_personne = id;
  end;
/

create or replace procedure getNotesClasse(id

create or replace procedure addUneMatiere(numMatiere in INTEGER, nomMatiere in VARCHAR, coeffMatiere in FLOAT) AS 
BEGIN
  INSERT INTO ob_matiere
    VALUES(numMatiere, nomMatiere, coeffMatiere);
END;
/

create or replace procedure addUnCours(numCours in INTEGER, debCours in DATE,  finCours in DATE, matiere in ob_matiere%type , classeCours in ob_classe%type, salle in ob_salle%type, edt in ob_edt%type ) AS
BEGIN
    INSERT INTO ob_cours
    VALUES(numCours, debCours, finCours, matiere, classeCours, salle, edt);
END;
/

create or replace procedure addSalle(idSalle in INTEGER, numSalle in INTEGER ) AS
BEGIN
    INSERT INTO ob_salle
    VALUES(idSalle, numSalle);
END;
/

create or replace procedure addEdt(idEdt in INTEGER) AS
BEGIN
    INSERT INTO ob_salle
    VALUES(idEdt);
END;
/

create or replace procedure addEtudiant( idEt in INTEGER,nomEt in VARCHAR , prenomEt in VARCHAR , emailPEt in VARCHAR ,saClasse in OB_CLASSE.ID_CLASSE%type , edt in ob_edt%type ) AS
BEGIN
    INSERT INTO ob_personne
    VALUES(idEt, nomPEt, prenomEt, emailEt,'etudiant', saClasse, edt );
END;
/

create or replace procedure addEnseignant(idEt in INTEGER,nomEt  in VARCHAR , prenomEt  in VARCHAR ,  emailPEt  in VARCHAR ,saClasse in OB_CLASSE.ID_CLASSE%type, edt in OB_EDT.ID_EDT%type ) AS
BEGIN
    INSERT INTO ob_personne
    VALUES(idEt, nomPEt, prenomEt, emailEt,'enseignant', saClasse, edt);
END;
/


create or replace procedure addConnexion( intCo in INTEGER,login in VARCHAR ,  mdp  in VARCHAR , sonEns in ob_personne%type) AS
BEGIN
    INSERT INTO ob_connexion
    VALUES(intCo, login, mdp, sonEns);
END;
/

create or replace procedure addPointsBonus(idPointB in INTEGER, descritpion in VARCHAR, sonEtudiant in ob_pointBonus%type) AS
BEGIN
    INSERT INTO ob_pointBonus
    VALUES(idPointB, descritpion, sonEtudiant);
END;
/

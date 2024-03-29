 create or replace function getNomClasse(id in ob_personne.id_personne%type) return ob_classe.nom%type is
  res ob_classe.nom%type;
  begin
    select (deref(saClasse)).nom into res
    from ob_personne P
    where id_personne = id;
    return res;
  end;
/

create or replace function isLogin(l in ob_connexion.login%type, m in ob_connexion.mdp%type) return ob_personne.id_personne%type is
  res ob_personne.id_personne%type;
  begin
    select (deref(sonEnseignant)).id_personne into res
    from ob_connexion C
    where login = l
    and mdp = m;
    
    if res is null then
      res := -1;
    end if;
    
    return res;
  end;
/

create or replace function getNomPersonne(id in ob_personne.id_personne%type) return varchar2 is
  res varchar2(50);
  begin
    select nom||' '||prenom into res
    from ob_personne
    where id_personne = id;
    
    return res;
  end;
/

create or replace procedure getMatieres(id in ob_personne.id_personne%type, res out sys_refcursor) as
  begin
    open res for
      select (deref(C.saMatiere)).nom, (deref(C.saMatiere)).id_matiere
      from ob_cours C
      where (deref(C.sonEnseignant)).id_personne = id;
  end;
/

create or replace procedure getClasses(id in ob_personne.id_personne%type, res out sys_refcursor) as
  begin
    open res for
      select distinct(deref(C.saClasse)).nom, (deref(C.saClasse)).id_classe
      from ob_cours C
      where (deref(C.sonEnseignant)).id_personne = id;
  end;
/


create or replace procedure getCours(id in ob_personne.id_personne%type, res out sys_refcursor) as
  begin
    open res for
      select dateDebut, heureDebut, heureFin, (deref(saMatiere)).nom, (deref(saClasse)).nom, (deref(saSalle)).num
      from ob_cours C
      where (deref(sonEnseignant)).id_personne = id;
  end;
/

create or replace procedure getStudentFromClass(id in ob_classe.id_classe%type, res out sys_refcursor) as
  begin
    open res for
      select id_personne, nom, prenom
      from ob_personne P
      where (deref(saClasse)).id_classe = id
      and fonction = 'etudiant';
  end;
/

create or replace procedure getPointBonus(id in ob_personne.id_personne%type, id_m in ob_matiere.id_matiere%type, res out sys_refcursor) as
  begin
    open res for
      select point, description
      from ob_pointBonus B
      where (deref(B.sonEtudiant)).id_personne = id
      and (deref(saMatiere)).id_matiere = id_m;
  end;
/


create or replace function getMaxCC(id_c in ob_classe.id_classe%type, id_m in ob_matiere.id_matiere%type) return integer is
  res integer;
  begin
    select max(TO_NUMBER(SUBSTR(type, 3, 1))) into res
    from ob_personne P, ob_note N
    where (deref(P.saClasse)).id_classe = id_c
    and (deref(N.saMatiere)).id_matiere = id_m
    and (deref(N.sonEtudiant)).id_personne = P.id_personne
    and REGEXP_LIKE (N.type, '^CC(*)');
    
    return res;
  end;
/

create or replace function getMaxDS(id_c in ob_classe.id_classe%type, id_m in ob_matiere.id_matiere%type) return integer is
  res integer;
  begin
    select max(TO_NUMBER(SUBSTR(type, 3, 1))) into res
    from ob_personne P, ob_note N
    where (deref(P.saClasse)).id_classe = id_c
    and (deref(N.saMatiere)).id_matiere = id_m
    and (deref(N.sonEtudiant)).id_personne = P.id_personne
    and REGEXP_LIKE (N.type, '^DS(*)');
    
    return res;
  end;
/

create or replace procedure addCC(id_c in ob_classe.id_classe%type, id_m in ob_matiere.id_matiere%type) as
  idNoteMax integer;
  nbCC integer;
  id integer;
  CURSOR id_student IS
    SELECT id_personne
    from ob_personne P
    WHERE (deref(P.saClasse)).id_classe = id_c
    and fonction = 'etudiant';
  begin
  idNoteMax := getIdNoteMax;
  nbCC := getNbNoteMaxCC(id_c, id_m);
  OPEN id_student;
    LOOP
      FETCH id_student INTO id;
        EXIT WHEN id_student%NOTFOUND;
        INSERT INTO ob_note
        select idNoteMax + 1, 1, -1, 'CC'||(nbCC + 1), ref(M), ref(E)
        from ob_matiere M, ob_personne E
        where id_matiere = id_m
        and id_personne = id;
        idNoteMax := idNoteMax + 1;
    END LOOP;
    CLOSE id_student; 
  end;
/

create or replace procedure addDS(id_c in ob_classe.id_classe%type, id_m in ob_matiere.id_matiere%type) as
  idNoteMax integer;
  nbDS integer;
  id integer;
  CURSOR id_student IS
    SELECT id_personne
    from ob_personne P
    WHERE (deref(P.saClasse)).id_classe = id_c
    and fonction = 'etudiant';
  begin
  idNoteMax := getIdNoteMax;
  nbDS := getNbNoteMaxDS(id_c, id_m);
  OPEN id_student;
    LOOP
      FETCH id_student INTO id;
        EXIT WHEN id_student%NOTFOUND;
        INSERT INTO ob_note
        select idNoteMax + 1, 1, -1, 'DS'||(nbDS + 1), ref(M), ref(E)
        from ob_matiere M, ob_personne E
        where id_matiere = id_m
        and id_personne = id;
        idNoteMax := idNoteMax + 1;
    END LOOP;
    CLOSE id_student; 
  end;
/

create or replace function getIdNoteMax return integer is
  res integer;
  begin
    select max(id_note) into res
    from ob_note;
    return res;
  end;
/

create or replace procedure getNotesDS(id in ob_personne.id_personne%type, id_m in ob_matiere.id_matiere%type, res out sys_refcursor) as
  begin
    open res for
      select id_note, note, type
      from ob_note N
      where (deref(N.sonEtudiant)).id_personne = id
      and REGEXP_LIKE (N.type, '^DS(*)')
      and (deref(saMatiere)).id_matiere = id_m
      order by id_note;
      end;
/

create or replace procedure getNotesCC(id in ob_personne.id_personne%type, id_m in ob_matiere.id_matiere%type, res out sys_refcursor) as
  begin
    open res for
      select id_note, note, type
      from ob_note N
      where (deref(N.sonEtudiant)).id_personne = id
      and REGEXP_LIKE (N.type, '^CC(*)')
      and (deref(saMatiere)).id_matiere = id_m
      order by id_note;
      end;
/

create or replace procedure setPointBonus(id_p in ob_personne.id_personne%type, po in ob_pointBonus.id_pointBonus%type, descr in ob_pointBonus.description%type, id_m in ob_matiere.id_matiere%type) as
  begin
    Update ob_pointBonus
    set point = po, description = descr
    where (deref(sonEtudiant)).id_personne = id_p
    and (deref(saMatiere)).id_matiere = id_m;
  end;
/

create or replace procedure setNote(id_n in ob_note.id_note%type, n in ob_note.note%type) as
  begin
    update ob_note N
    set note = n
    where id_note = id_n;
  end;
/

create or replace function getNbNoteMaxCC(idClasse in ob_classe.id_classe%type, idMatiere in ob_matiere.id_matiere%type) return integer is
  res integer;
  id integer;
  cursor curseur is 
    select COUNT(*) into res
    from ob_personne P, ob_note N
    where (deref(P.saClasse)).id_classe = idClasse
    and (deref(N.saMatiere)).id_matiere = idMatiere
    and (deref(N.sonEtudiant)).id_personne = P.id_personne
    and REGEXP_LIKE (N.type, '^CC(*)')
    group by id_note;
  begin
    res := 0;
    OPEN curseur;
    LOOP
      FETCH curseur INTO id;
      EXIT WHEN curseur%NOTFOUND;
      res := res + 1;
    END loop;
    close curseur;
    return res / 2;
  end;
/

create or replace function getNbNoteMaxDS(idClasse in ob_classe.id_classe%type, idMatiere in ob_matiere.id_matiere%type) return integer is
  res integer;
  id integer;
  cursor curseur is 
    select COUNT(*) into res
    from ob_personne P, ob_note N
    where (deref(P.saClasse)).id_classe = idClasse
    and (deref(N.saMatiere)).id_matiere = idMatiere
    and (deref(N.sonEtudiant)).id_personne = P.id_personne
    and REGEXP_LIKE (N.type, '^DS(*)')
    group by id_note;
  begin
    res := 0;
    OPEN curseur;
    LOOP
      FETCH curseur INTO id;
      EXIT WHEN curseur%NOTFOUND;
      res := res + 1;
    END loop;
    close curseur;
    return res / 2;
  end;
/

create or replace procedure getNotesFromEtudiant(idPersonne in ob_personne.id_personne%type, idMatiere in ob_matiere.id_matiere%type, res out sys_refcursor) as
  begin
    open res for
      select N.id_note, N.note, P.description, N.type
      from ob_note N, ob_pointBonus P
      where (deref(N.saMatiere)).id_matiere = idMatiere
      and (deref(N.sonEtudiant)).id_personne = idPersonne
      and (deref(P.sonEtudiant)).id_personne = idPersonne
      and (deref(P.saMatiere)).id_matiere = idMatiere;
  end;
/

/*
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
*/

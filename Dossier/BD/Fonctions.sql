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
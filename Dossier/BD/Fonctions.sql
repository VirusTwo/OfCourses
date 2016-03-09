create or replace function getNomSaClasse(id in ob_personne.id_personne%type) return ob_classe.nom%type is
  res ob_classe.nom%type;
  begin
    select (deref(saClasse)).nom into res
    from ob_personne P
    where id_personne = id;
    return res;
  end;
/

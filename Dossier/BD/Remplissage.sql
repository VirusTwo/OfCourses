DELETE FROM ob_classe;
DELETE FROM ob_connexion;
DELETE FROM ob_cours;
DELETE FROM ob_matiere;
DELETE FROM ob_note;
DELETE FROM ob_personne;
DELETE FROM ob_pointbonus;
DELETE FROM ob_salle;

/*GUIZMO il faut rajouter un lien entre matiere et prof */


BEGIN 

  /* ajout des salles */
  addSalle(1, 'I208');
  addSalle(2, 'I209');
  
END;
/

BEGIN
  /* ajout des classes */
  addClasse(2, '4c');
  addClasse(1, '3C');
END;
/

BEGIN
  /* ajout des matières */
  addUneMatiere(1, 'JAVA', 8);
  addUneMatiere(2,'CODAGE', 3);
  
END;
/


BEGIN
  /* ajout des Enseigants  idEn, nomEn, prenomEn, emailEn,'enseignant', ref(C)*/
  addEnseignant(1, 'MARTINEZ', 'Guizmo', 'guizmo@mail.fr', 1);
  addEnseignant(2, 'POKORSKI', 'Alexis', 'p-alexis@mail.fr', 2);
  
END;
/

BEGIN

  addConnexion(1, 'guizmo','coucou',1);
  addConnexion(2, 'palexis','hello',2);
  
END;
/

BEGIN

  /* classe 4C idClasse = 1 */
  addEtudiant(3, 'CREBOUW', 'Cyril', 'c-cyril@mail.fr',1);
  addEtudiant(4, 'SAUVARD', 'Clément', 's-clement@mail.fr',1);
  addEtudiant(5, 'LEMONIER', 'Simon', 'l-lemonier@mail.fr',1);
  addEtudiant(6, 'TOUTIN', 'Antoine', 't-antoine@mail.fr',1);
  
  /* classe 3C idClasse = 2 */
  addEtudiant(7, 'LACROIX', 'Julien', 'l-julien@mail.fr',2);
  addEtudiant(8, 'MOULE', 'Martin', 'm-martin@mail.fr',2);
  addEtudiant(9, 'SKY', 'Anastasia', 's-anastasia@mail.fr',2);
  addEtudiant(10, 'LACOU', 'Sandra', 'l-sandra@mail.fr',2);
  
END;
/

BEGIN
  
  /* addNote(idNote in INTEGER, coeffNote in FLOAT, note in FLOAT, matiere in ob_matiere.id_matiere%type, etudiant in ob_personne.id_personne%type)*/
  
  /* notes pour JAVA idNote = 1 */
  addNote(1, 2, 13, 1, 3);
  addNote(2, 2, 17, 1, 4);
  addNote(3, 2, 15, 1, 5);
  addNote(4, 2, 11, 1, 6);
  addNote(5, 2, 11, 1, 7);
  addNote(6, 2, 18, 1, 8);
  addNote(7, 2, 16, 1, 9);
  addNote(8, 2, 13, 1, 10);
  
  /* nots pour CODAGE idNote = 2 */
  addNote(9, 3, 14, 2, 3);
  addNote(10, 3, 16, 2, 4);
  addNote(3, 3, 14, 2, 5);
  addNote(4, 3, 15, 2, 6);
  addNote(5, 3, 17, 2, 7);
  addNote(6, 3, 12, 2, 8);
  addNote(7, 3, 11, 2, 9);
  addNote(8, 3, 16, 2, 10);
  
END;
/



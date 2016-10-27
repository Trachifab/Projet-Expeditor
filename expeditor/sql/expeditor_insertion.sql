USE expeditor;

INSERT INTO client (idExterne, raisonSociale, adresse1, cp, ville) VALUES ('BAMBOO', 'BAMBOO', '112 avenues Delacroix','44800', 'SAINT HERBLAIN');
INSERT INTO client (idExterne, raisonSociale, adresse1, cp, ville) VALUES ('PYRENS', 'PYRENS', '23 boulevard Descartes','31000', 'TOULOUSE');
INSERT INTO client (idExterne, raisonSociale, adresse1, cp, ville) VALUES ('COTON BLANC', 'COTON BLANC', '7 Place Racine','44000', 'NANTES');
INSERT INTO client (idExterne, raisonSociale, adresse1, cp, ville) VALUES ('KIASOIF', 'KIASOIF', '2 Place de Monaco','06000', 'NICE');

INSERT INTO article (idExterne, libelle, description, poids) VALUES ('Carte graphique','Carte graphique','Superbe carte graphique 256Mo de RAM DDR2, fera tourner vos plus gros jeux du moments ! (SimCity 2000, Alex Kidd)',8785);
INSERT INTO article (idExterne, libelle, description, poids) VALUES ('Carte mère','Carte mère','Superbe carte mère, elle n''attends plus qu''un père pour fonctionner, lol mdr xddd',5541);
INSERT INTO article (idExterne, libelle, description, poids) VALUES ('Clavier USB','Clavier USB','Clavier très simple qui fait son boulot',1005);

INSERT INTO role (code,libelle) VALUES ('MANA', 'Manager');
INSERT INTO role (code,libelle) VALUES ('EMPL', 'Employé');

INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('EMPLOYE', 'Mickael', 'emp.mic@expeditor.com','employe','EMPL'); 
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('EMPLOYE', 'Corentin', 'emp.cor@expeditor.com','employe','EMPL');
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('EMPLOYE', 'Fabien', 'emp.fab@expeditor.com','employe','EMPL');
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('EMPLOYE', 'Florent', 'emp.flo@expeditor.com','employe','EMPL');
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('EMPLOYE', 'Joffrey', 'emp.jof@expeditor.com','employe','EMPL');

INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('MANAGER', 'Mickael', 'man.mic@expeditor.com','manager','MANA'); 
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('MANAGER', 'Corentin', 'man.cor@expeditor.com','manager','MANA');
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('MANAGER', 'Fabien', 'man.fab@expeditor.com','manager','MANA');
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('MANAGER', 'Florent', 'man.flo@expeditor.com','manager','MANA');
INSERT INTO collaborateur(nom, prenom,email,motDePasse,role_code) VALUES ('MANAGER', 'Joffrey', 'man.jof@expeditor.com','manager','MANA');

INSERT INTO etat(code,libelle) VALUES ('ATTE','En attente');
INSERT INTO etat(code,libelle) VALUES ('ENCO','En cours de traitement');
INSERT INTO etat(code,libelle) VALUES ('TRAI','Traitée');

INSERT INTO commande(numero, dateCommande, dateTraitement, collaborateur_id, client_id, etat_code) VALUES (101,'02/01/2014 08:00:00','05/01/2014 18:00:00',1,1,'TRAI');
INSERT INTO commande(numero, dateCommande, dateTraitement, collaborateur_id, client_id, etat_code) VALUES (105,'15/01/2014 05:12:00','22/01/2014 17:00:00',2,3,'TRAI');
INSERT INTO commande(numero, dateCommande, dateTraitement, collaborateur_id, client_id, etat_code) VALUES (106,'18/01/2014 09:52:35','05/02/2014 14:00:00',5,4,'TRAI');

INSERT INTO commande(numero, dateCommande, collaborateur_id, client_id, etat_code) VALUES (102,'03/01/2014 08:35:17',3,2,'ENCO');
INSERT INTO commande(numero, dateCommande, collaborateur_id, client_id, etat_code) VALUES (103,'05/01/2014 08:33:28',2,2,'ENCO');

INSERT INTO commande(numero, dateCommande, client_id, etat_code) VALUES (104,'14/01/2014 08:05:55',1,'ATTE');
INSERT INTO commande(numero, dateCommande, client_id, etat_code) VALUES (107,'20/01/2014 09:04:12',2,'ATTE');
INSERT INTO commande(numero, dateCommande, client_id, etat_code) VALUES (108,'22/01/2014 08:15:01',3,'ATTE');
INSERT INTO commande(numero, dateCommande, client_id, etat_code) VALUES (109,'22/01/2014 10:58:59',3,'ATTE');

INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (101,1,5); 
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (101,2,2);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (101,3,1);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (102,1,12);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (102,3,6);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (103,3,5);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (104,1,1);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (104,2,2);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (105,1,4);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (106,1,9);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (106,2,8);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (106,3,7);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (107,3,2);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (108,2,4);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (109,2,5);
INSERT INTO ligneCommande(commande_numero, article_id, quantite) VALUES (109,3,1);
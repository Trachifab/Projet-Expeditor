USE expeditor;

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
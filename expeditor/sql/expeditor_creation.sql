/*------------------------------------------------------------
*        Script SQLSERVER 
------------------------------------------------------------*/
USE master
IF EXISTS(select * from sys.databases where name='expeditor')
DROP DATABASE expeditor;

USE MASTER;

CREATE DATABASE expeditor;

USE expeditor;

/*------------------------------------------------------------
-- Table: collaborateur
------------------------------------------------------------*/
CREATE TABLE collaborateur(
	id         INT IDENTITY (1,1) NOT NULL ,
	nom        VARCHAR (50)  ,
	prenom     VARCHAR (50)  ,
	email      VARCHAR (100) NOT NULL UNIQUE,
	motDePasse VARCHAR (50)  ,
	role_code       CHAR (4)   ,
	CONSTRAINT prk_constraint_collaborateur PRIMARY KEY NONCLUSTERED (id)
);


/*------------------------------------------------------------
-- Table: article
------------------------------------------------------------*/
CREATE TABLE article(
	id          INT IDENTITY (1,1) NOT NULL ,
	idExterne   VARCHAR (50) NOT NULL UNIQUE,
	libelle     VARCHAR (50)  ,
	description TEXT   ,
	poids       INT   ,
	CONSTRAINT prk_constraint_article PRIMARY KEY NONCLUSTERED (id)
);


/*------------------------------------------------------------
-- Table: client
------------------------------------------------------------*/
CREATE TABLE client(
	id            INT IDENTITY (1,1) NOT NULL ,
	idExterne     VARCHAR (100) NOT NULL UNIQUE,
	raisonSociale VARCHAR (100)  ,
	adresse1      VARCHAR (100)  ,
	adresse2      VARCHAR (100)  ,
	adresse3      VARCHAR (100)  ,
	cp            CHAR (5)   ,
	ville         VARCHAR (100)  ,
	CONSTRAINT prk_constraint_client PRIMARY KEY NONCLUSTERED (id)
);


/*------------------------------------------------------------
-- Table: role
------------------------------------------------------------*/
CREATE TABLE role(
	code    CHAR (4)  NOT NULL ,
	libelle VARCHAR (50) NOT NULL UNIQUE,
	CONSTRAINT prk_constraint_role PRIMARY KEY NONCLUSTERED (code)
);


/*------------------------------------------------------------
-- Table: commande
------------------------------------------------------------*/
CREATE TABLE commande(
	numero         INT  NOT NULL ,
	dateCommande   DATETIME  ,
	dateTraitement DATETIME  ,
	collaborateur_id             INT   ,
	client_id      INT   ,
	etat_code           CHAR (4)   ,
	CONSTRAINT prk_constraint_commande PRIMARY KEY NONCLUSTERED (numero)
);


/*------------------------------------------------------------
-- Table: etat
------------------------------------------------------------*/
CREATE TABLE etat(
	code    CHAR (4)  NOT NULL ,
	libelle VARCHAR (50) NOT NULL UNIQUE,
	CONSTRAINT prk_constraint_etat PRIMARY KEY NONCLUSTERED (code)
);


/*------------------------------------------------------------
-- Table: ligneCommande
------------------------------------------------------------*/
CREATE TABLE ligneCommande(
	quantite INT   ,
	commande_numero   INT  NOT NULL ,
	article_id       INT  NOT NULL ,
	CONSTRAINT prk_constraint_ligneCommande PRIMARY KEY NONCLUSTERED (commande_numero,article_id)
);



ALTER TABLE collaborateur ADD CONSTRAINT FK_collaborateur_code FOREIGN KEY (role_code) REFERENCES role(code);
ALTER TABLE commande ADD CONSTRAINT FK_commande_id FOREIGN KEY (collaborateur_id) REFERENCES collaborateur(id);
ALTER TABLE commande ADD CONSTRAINT FK_commande_id_client FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE commande ADD CONSTRAINT FK_commande_code FOREIGN KEY (etat_code) REFERENCES etat(code);
ALTER TABLE ligneCommande ADD CONSTRAINT FK_ligneCommande_numero FOREIGN KEY (commande_numero) REFERENCES commande(numero);
ALTER TABLE ligneCommande ADD CONSTRAINT FK_ligneCommande_id FOREIGN KEY (article_id) REFERENCES article(id);

--Archivage article
ALTER TABLE Article ADD dateArchive datetime;



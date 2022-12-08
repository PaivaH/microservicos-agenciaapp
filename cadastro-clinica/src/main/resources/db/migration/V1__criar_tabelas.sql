CREATE TABLE clinica (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 nome varchar(100) NOT NULL,
 endereco varchar(100) NOT NULL,
 responsavel varchar(100) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE profissional (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 nome varchar(100) NOT NULL,
 profissao varchar(19) NOT NULL,
 especialidades varchar(100) DEFAULT NULL,
 conselhoProfissional varchar(15) NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE profissional
ADD id_clinica BIGINT(20) NOT null;

ALTER TABLE profissional
ADD CONSTRAINT fk_clinica 
FOREIGN KEY(id_clinica) REFERENCES clinica(id);
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
 conselho_profissional varchar(15) NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE profissional
ADD clinica_id BIGINT(20) NOT null;

ALTER TABLE profissional
ADD CONSTRAINT fk_clinica 
FOREIGN KEY(clinica_id) REFERENCES clinica(id);

CREATE TABLE agenda (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    profissional_id bigint(20) NOT NULL,
    dia_hora datetime NOT NULl UNIQUE,
    disponivel boolean default 1,
    PRIMARY KEY (id),
    CONSTRAINT fk_profissional 
    FOREIGN KEY (profissional_id)  
    REFERENCES profissional(id)  
);
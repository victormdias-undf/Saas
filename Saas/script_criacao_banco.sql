
CREATE DATABASE IF NOT EXISTS gerenciamentoEstudantil
    CHARACTER SET utf8
    COLLATE utf8_unicode_ci;

USE gerenciamentoEstudantil;

-- Relacionamento 1:N: um professor pode ensinar vários cursos
-- Relacionamento N:N: um estudante pode estar em vários cursos e um curso pode ter vários estudantes

CREATE TABLE professor (
    matricula       VARCHAR(20)  PRIMARY KEY,
    nome_completo   VARCHAR(150) NOT NULL,
    data_nascimento DATE         NOT NULL,
    especialidade   VARCHAR(100) NOT NULL
);


CREATE TABLE estudante (
    matricula       VARCHAR(20)  PRIMARY KEY,
    nome_completo   VARCHAR(150) NOT NULL,
    data_nascimento DATE         NOT NULL
);


CREATE TABLE curso (
    codigo                  VARCHAR(20)  PRIMARY KEY,
    nome_curso              VARCHAR(150) NOT NULL,
    carga_horaria           INT          NOT NULL,
    professor_matricula     VARCHAR(20),
    CONSTRAINT fk_curso_professor
        FOREIGN KEY (professor_matricula)
        REFERENCES professor(matricula)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);


CREATE TABLE matricula (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    estudante_matricula VARCHAR(20) NOT NULL,
    curso_codigo        VARCHAR(20) NOT NULL,
    data_matricula      DATE        NOT NULL,
    CONSTRAINT fk_matricula_estudante
        FOREIGN KEY (estudante_matricula)
        REFERENCES estudante(matricula)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_matricula_curso
        FOREIGN KEY (curso_codigo)
        REFERENCES curso(codigo)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT uq_estudante_curso
        UNIQUE (estudante_matricula, curso_codigo)
);

CREATE USER IF NOT EXISTS 'app_estudantil'@'%' IDENTIFIED BY 'estud@nte';
GRANT ALL PRIVILEGES ON gerenciamentoEstudantil.* TO 'app_estudantil'@'%';
FLUSH PRIVILEGES;
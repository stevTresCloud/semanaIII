CREATE
DATABASE catalogo_cursos;
USE
catalogo_cursos;

CREATE TABLE categorias
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE cursos
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    titulo         VARCHAR(255) NOT NULL,
    descripcion    TEXT,
    categoria_id   INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categorias (id)
);

CREATE TABLE modulos
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    curso_id    INT,
    titulo      VARCHAR(255) NOT NULL,
    descripcion TEXT,
    duracion    INT, -- en minutos
    FOREIGN KEY (curso_id) REFERENCES cursos (id)
);

CREATE TABLE materiales
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    modulo_id   INT,
    tipo        ENUM('video', 'lectura', 'quiz'),
    url         VARCHAR(255),
    descripcion TEXT,
    FOREIGN KEY (modulo_id) REFERENCES modulos (id)
);

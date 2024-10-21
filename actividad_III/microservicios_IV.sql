CREATE
DATABASE recomendaciones;
USE
recomendaciones;

-- Tabla de usuarios sincronizados (datos mínimos)
CREATE TABLE usuarios_sincronizados
(
    id                   INT PRIMARY KEY,
    nombre               VARCHAR(100),
    apellido             VARCHAR(100),
    email                VARCHAR(100),
    fecha_sincronizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de cursos sincronizados (datos mínimos)
CREATE TABLE cursos_sincronizados
(
    id                   INT PRIMARY KEY,
    titulo               VARCHAR(255),
    fecha_sincronizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Progreso del aprendizaje y recomendaciones basadas en IA
CREATE TABLE progreso_aprendizaje
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id          INT,
    curso_id            INT,
    modulo_id           INT,
    progreso_porcentaje DECIMAL(5, 2),
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios_sincronizados (id),
    FOREIGN KEY (curso_id) REFERENCES cursos_sincronizados (id)
);

CREATE TABLE recomendaciones
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    curso_id   INT,
    razon      VARCHAR(255),
    fecha      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios_sincronizados (id),
    FOREIGN KEY (curso_id) REFERENCES cursos_sincronizados (id)
);

CREATE
DATABASE evaluacion;
USE
evaluacion;

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

-- Evaluaciones y preguntas/respuestas asociadas
CREATE TABLE evaluaciones
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    curso_id    INT,
    titulo      VARCHAR(255) NOT NULL,
    descripcion TEXT,
    FOREIGN KEY (curso_id) REFERENCES cursos_sincronizados (id)
);

CREATE TABLE preguntas
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    evaluacion_id INT,
    texto         VARCHAR(500) NOT NULL,
    tipo          ENUM('multiple_choice', 'true_false', 'open_ended'),
    FOREIGN KEY (evaluacion_id) REFERENCES evaluaciones (id)
);

CREATE TABLE respuestas
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    pregunta_id INT,
    texto       VARCHAR(255),
    correcta    BOOLEAN,
    FOREIGN KEY (pregunta_id) REFERENCES preguntas (id)
);

CREATE TABLE resultados
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id    INT,
    evaluacion_id INT,
    puntaje       DECIMAL(5, 2),
    fecha         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios_sincronizados (id),
    FOREIGN KEY (evaluacion_id) REFERENCES evaluaciones (id)
);

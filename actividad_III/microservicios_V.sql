CREATE
DATABASE notificaciones;
USE
notificaciones;

-- Tabla de usuarios sincronizados (datos mínimos)
CREATE TABLE usuarios_sincronizados
(
    id                   INT PRIMARY KEY,
    nombre               VARCHAR(100),
    apellido             VARCHAR(100),
    email                VARCHAR(100),
    fecha_sincronizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de notificaciones enviadas a los usuarios
CREATE TABLE notificaciones
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id  INT,
    tipo        ENUM('email', 'push'),
    mensaje     TEXT,
    leido       BOOLEAN   DEFAULT FALSE,
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios_sincronizados (id)
);

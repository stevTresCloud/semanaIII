CREATE
DATABASE gestion_usuarios;
USE
gestion_usuarios;

CREATE TABLE roles
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE permisos
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100)        NOT NULL,
    apellido       VARCHAR(100)        NOT NULL,
    email          VARCHAR(100) UNIQUE NOT NULL,
    contrasena     VARCHAR(255)        NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rol_id         INT,
    activo         BOOLEAN   DEFAULT TRUE,
    FOREIGN KEY (rol_id) REFERENCES roles (id)
);

CREATE TABLE roles_permisos
(
    rol_id     INT,
    permiso_id INT,
    PRIMARY KEY (rol_id, permiso_id),
    FOREIGN KEY (rol_id) REFERENCES roles (id),
    FOREIGN KEY (permiso_id) REFERENCES permisos (id)
);

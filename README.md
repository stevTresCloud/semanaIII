# Plataforma de Aprendizaje Personalizado con IA

## Descripción
El proyecto es una **plataforma de aprendizaje en línea** que utiliza **inteligencia artificial** para ofrecer cursos y materiales personalizados a cada usuario según su progreso, preferencias y estilo de aprendizaje. Los microservicios manejarían diferentes aspectos de la plataforma como la gestión de usuarios, el catálogo de cursos, la evaluación y retroalimentación, y el motor de recomendaciones impulsado por IA.

## Arquitectura de Microservicios

### 1. Microservicio de Gestión de Usuarios
- **Descripción**: Maneja el registro, autenticación, autorización y perfiles de usuarios.
- **Base de datos**: MySQL/PostgreSQL (tabla de usuarios, roles, permisos).
- **Tecnologías**: Spring Security, JWT para la autenticación, OAuth 2.0.

### 2. Microservicio de Catálogo de Cursos
- **Descripción**: Gestiona los cursos disponibles en la plataforma, categorías y módulos.
- **Base de datos**: MySQL/PostgreSQL (tabla de cursos, módulos, materiales).
- **Tecnologías**: Spring Data JPA, Hibernate.

### 3. Microservicio de Evaluación
- **Descripción**: Permite crear evaluaciones, gestionar resultados y generar retroalimentación en función de las respuestas de los usuarios.
- **Base de datos**: MySQL/PostgreSQL (tabla de evaluaciones, resultados, retroalimentación).
- **Tecnologías**: Spring Batch (para procesamiento de evaluaciones masivas).

### 4. Microservicio de Recomendación de Contenido (IA)
- **Descripción**: Utiliza un motor de recomendación basado en machine learning para sugerir cursos y materiales personalizados.
- **Base de datos**: MySQL/PostgreSQL (datos de progreso de los usuarios, preferencias, histórico de aprendizaje).
- **Tecnologías**: Spring Boot, con integración a librerías de IA (TensorFlow o PyTorch).

### 5. Microservicio de Notificaciones
- **Descripción**: Envía notificaciones de correo electrónico o push a los usuarios sobre nuevas recomendaciones, resultados de evaluaciones o actualizaciones de la plataforma.
- **Base de datos**: MySQL/PostgreSQL (cola de mensajes, historial de notificaciones).
- **Tecnologías**: Spring Cloud Stream, RabbitMQ/Kafka para manejo de mensajería.

## Flujo del Proyecto
1. El usuario se registra en la plataforma a través del Microservicio de Gestión de Usuarios.
2. Al acceder al catálogo de cursos, el Microservicio de Catálogo de Cursos le presenta las diferentes opciones disponibles.
3. El usuario comienza a tomar cursos y realizar evaluaciones gestionadas por el Microservicio de Evaluación.
4. El Microservicio de Recomendación de Contenido analiza los datos de uso y las evaluaciones del usuario, utilizando un algoritmo de IA para sugerir contenido personalizado.
5. A lo largo del proceso, el Microservicio de Notificaciones envía actualizaciones sobre recomendaciones, recordatorios y resultados.

## Tecnologías Clave
- **Spring Boot**: Para la creación de los microservicios.
- **Spring Cloud**: Para gestionar la comunicación entre microservicios, configuración centralizada, descubrimiento de servicio

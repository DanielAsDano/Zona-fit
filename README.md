# ZonaFit

Sistema de gestión de clientes para gimnasio desarrollado con Spring Boot. Permite realizar operaciones CRUD sobre los clientes desde la consola.

---

## Tecnologias utilizadas

- Java 25
- Spring Boot 4.0.5
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

## Estructura del proyecto

```
src/
└── main/
    ├── java/dano/ZonaFit/
    │   ├── modelo/
    │   │   └── Cliente.java
    │   ├── repositorio/
    │   │   └── IClienteRepositorio.java
    │   ├── servicio/
    │   │   ├── IClienteServicio.java
    │   │   └── ClienteServicio.java
    │   └── ZonaFitApplication.java
    └── resources/
        └── application.properties
```

---

## Base de datos

### Esquema

```sql
CREATE TABLE `zona_fit_db`.`cliente` (
  `id`        INT         NOT NULL AUTO_INCREMENT,
  `nombre`    VARCHAR(45) NOT NULL,
  `apellido`  VARCHAR(45) NOT NULL,
  `membresia` INT         NOT NULL,
  PRIMARY KEY (`id`)
);
```

### Diagrama

```
+---------------------------+
|          cliente          |
+---------------------------+
| PK  id         INT        |
|     nombre     VARCHAR(45)|
|     apellido   VARCHAR(45)|
|     membresia  INT        |
+---------------------------+
```

---

## Configuracion

1. Clona el repositorio
2. Copia el archivo de ejemplo y llena tus credenciales:

```bash
cp application.properties.example src/main/resources/application.properties
```

3. Edita `application.properties` con tus datos de conexion:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zona_fit_db
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```

4. Crea la base de datos en MySQL y ejecuta el esquema de arriba

5. Corre el proyecto desde IntelliJ o con Maven:

```bash
./mvnw spring-boot:run
```

---

## Funcionalidades

- Listar todos los clientes
- Buscar cliente por ID
- Agregar nuevo cliente
- Modificar cliente existente
- Eliminar cliente

---

## Autor

Dano

# ZonaFit - Interfaz Grafica

Sistema de gestion de clientes para gimnasio desarrollado con Spring Boot y Java Swing. Permite realizar operaciones CRUD sobre los clientes mediante una interfaz grafica de escritorio.

---

## Tecnologias utilizadas

- Java 25
- Spring Boot 4.0.5
- Spring Data JPA
- Java Swing
- FlatLaf (tema FlatDarculaLaf)
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
    │   ├── gui/
    │   │   ├── Form.java
    │   │   └── Form.form
    │   └── ZonaFitSwing.java
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

- Listar todos los clientes en una tabla
- Seleccionar un cliente de la tabla para editar sus datos
- Agregar nuevo cliente mediante formulario
- Modificar cliente existente
- Eliminar cliente seleccionado
- Limpiar formulario

---

## Nota tecnica

El contexto de Spring Boot se inicializa en modo no-headless para permitir la integracion con Swing. El bean Form es administrado por Spring, lo que permite inyectar servicios directamente en la capa de presentacion.

```java
ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ZonaFitSwing.class)
    .headless(false)
    .web(WebApplicationType.NONE)
    .run(args);
```

---

## Autor

Dano

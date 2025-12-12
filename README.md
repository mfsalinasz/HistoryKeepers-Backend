# History Keepers - Backend

Este es el repositorio del servidor (API REST) para el proyecto "History Keepers". Provee la lógica de negocio, la seguridad y la conexión a base de datos para el museo virtual.

El proyecto está construido con **Java Spring Boot** y utiliza **PostgreSQL** como base de datos.

## Enlaces del Despliegue (Producción)

De acuerdo con los requisitos de la entrega final, aquí se encuentran los servicios activos:

* **URL Base del Backend (Railway):**
    `https://historykeepers-backend-production.up.railway.app/`
* **Base de Datos (Cadena de conexión):**
    `jdbc:postgresql://ep-spring-union-a4bnt13g-pooler.us-east-1.aws.neon.tech/neondb`
    *(Hospedada en Neon Tech / AWS)*

---

## Instalación y Ejecución Local

Prerrequisitos:
* Java JDK 17 o superior
* Maven
* PostgreSQL (o conexión a internet para usar la BD remota configurada)

### Pasos para ejecutarlo:

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/mtsalinasz/HistoryKeepers-Backend.git](https://github.com/mtsalinasz/HistoryKeepers-Backend.git)
    ```

2.  **Configuración:**
    El archivo `src/main/resources/application.properties` ya contiene la configuración necesaria para conectarse a la base de datos en la nube. No es necesario instalar una BD local si se tiene internet.

3.  **Compilar y Ejecutar:**
    Abrir una terminal en la carpeta raíz del proyecto y ejecutar:
    ```bash
    mvn spring-boot:run
    ```

    El servidor iniciará en el puerto **8080** (`http://localhost:8080`).

---

## Guía de Despliegue en la Nube (Railway)

El backend se encuentra desplegado en **Railway**. Pasos para replicar el despliegue:

1.  Crear una cuenta en [Railway.app](https://railway.app/).
2.  Seleccionar "New Project" > "Deploy from GitHub repo".
3.  Seleccionar este repositorio (`HistoryKeepers-Backend`).
4.  En la sección de "Variables", configurar las credenciales de la base de datos (Spring Datasource URL, Username, Password) tal como están en el archivo `application.properties`.
5.  Railway detectará automáticamente el archivo `pom.xml` y construirá la aplicación usando Maven.
6.  Una vez finalizado, generará un dominio público (el que se menciona al inicio de este documento).

---

## Documentación de la API (Ejemplos cURL)

A continuación se presentan los comandos para probar el CRUD de Productos desde una terminal o consola.

**Nota:** Reemplazar `localhost:8080` por la URL de producción si se desea probar en la nube.

### 1. Listar todos los productos (GET)
Recupera la lista completa del archivo histórico.

```bash
curl -X GET "http://localhost:8080/api/products"
```

### 2. Obtener un producto por ID (GET)

```bash
curl -X GET "http://localhost:8080/api/products/1"
```

### 3. Crear un nuevo producto (POST)
Agrega una pieza a la colección. El cuerpo debe ser un JSON.

```bash
curl -X POST "http://localhost:8080/api/products" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Balón Azteca 86",
    "description": "Balón oficial utilizado en el mundial de México 1986.",
    "category": "Futbol",
    "imageUrl": "[https://ejemplo.com/balon-azteca.jpg](https://ejemplo.com/balon-azteca.jpg)"
  }'
```

### 4. Actualizar un producto (PUT)
Modifica los datos de una pieza existente (ejemplo ID 1).

```bash
curl -X PUT "http://localhost:8080/api/products/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Balón Azteca (Edición Final)",
    "description": "Corrección: Se utilizó en la final del torneo.",
    "category": "Futbol Retro",
    "imageUrl": "[https://ejemplo.com/balon-final.jpg](https://ejemplo.com/balon-final.jpg)"
  }'
```

### 5. Eliminar un producto (DELETE)
Borra una pieza del museo permanentemente.

```bash
curl -X DELETE "http://localhost:8080/api/products/1"
```

### 6. Listar todos los usuarios (GET)
```bash
curl -X GET http://localhost:8080/api/usuarios
```

### 7. Obtener un usuario por ID (GET)
```bash
curl -X GET http://localhost:8080/api/usuarios/1
```

### 8. Crear usuario nuevo (POST)
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "username": "superadmin",
    "password": "admin123",
    "correo": "superadmin@historykeepers.com",
    "nombreCompleto": "Super Administrador"
  }'
```

### 9. Actualizar usuario (PUT)
Modifica los datos de un usuario existente (ejemplo ID 1).
```bash
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "username": "prueba",
    "password": "123",
    "correo": "prueba@prueba.com",
    "nombreCompleto": "PruebaCURL"
  }'
```

### 10. Eliminar usuario (DELETE)
```bash
curl -X DELETE http://localhost:8080/api/usuarios/1
```

## Documentación del Código (Estructura)

El proyecto sigue una arquitectura en capas clásica de Spring Boot:

### `src/main/java/com/historykeepers`

* **`/controller`**: Contiene los puntos de entrada de la API (Endpoints).
    * **ProductController.java**: Maneja el CRUD de las piezas del museo.
    * **AuthController.java**: Gestiona el inicio de sesión de los administradores.
    * **UsuarioController.java**: Permite la gestión de los usuarios del sistema (Staff).

* **`/model`**: Definición de las entidades que mapean la base de datos.
    * **Product.java**: Representa el objeto físico (id, nombre, descripción, url imagen, categoría).
    * **User.java**: Representa a los administradores del sistema.

* **`/repository`**: Interfaces que extienden de `JpaRepository`.
    * Se encargan de la comunicación directa con PostgreSQL usando Hibernate. Permiten funciones como `save()`, `findAll()`, `findById()`, etc., sin escribir SQL manual.

* **`/service`**: Capa de Lógica de Negocio.
    * Aquí se procesan los datos antes de guardarlos o enviarlos al controlador (ej. validaciones extra o lógica del "dato curioso").

### `src/main/resources`

* **`application.properties`**: Archivo de configuración crítica. Define el puerto del servidor, las credenciales de la base de datos Neon (PostgreSQL) y las configuraciones de JPA/Hibernate.
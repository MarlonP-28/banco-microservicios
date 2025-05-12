#   Aplicación Bancaria de Microservicios

    Esta proyecto implementa una aplicación bancaria simplificada utilizando una arquitectura de microservicios con Spring Boot y Spring Kafka.

    ##  Índice

    * [Estructura del Proyecto](#estructura-del-proyecto)
    * [Tecnologías Utilizadas](#tecnologías-utilizadas)
    * [Prerrequisitos](#prerrequisitos)
    * [Configuración](#configuración)
        * [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
        * [Configuración de Kafka](#configuración-de-kafka)
    * [Construcción de la Aplicación](#construcción-de-la-aplicación)
    * [Ejecución de la Aplicación](#ejecución-de-la-aplicación)
        * [Ejecución con Docker Compose (Recomendado)](#ejecución-con-docker-compose-recomendado)
        * [Ejecución Manual](#ejecución-manual)
    * [Pruebas](#pruebas)
        * [Pruebas Unitarias](#pruebas-unitarias)
        * [Pruebas de Integración](#pruebas-de-integración)
    * [Endpoints de la API](#endpoints-de-la-api)
    * [Colección de Postman](#colección-de-postman)
    * [Script de la Base de Datos](#script-de-la-base-de-datos)
    * [Notas Importantes](#notas-importantes)

    ##  Estructura del Proyecto

    El proyecto está organizado como un proyecto Maven multi-módulo:

    ```
    parent-project/
    ├── cliente-persona-service/  #   Microservicio para Cliente y Persona
    │   ├── src/main/java/
    │   │   └── com/example/clientpersona/
    ├── cuenta-movimiento-service/ #   Microservicio para Cuenta y Movimiento
    │   ├── src/main/java/
    │   │   └── com/example/accountmovement/
    ├── common/                 #   Clases compartidas (DTOs, excepciones)
    │   ├── src/main/java/
    │   │   └── com/example/common/
    ├── docker-compose.yml    #   Orquestación de Docker
    ├── pom.xml              #   POM principal
    ```

    ##  Tecnologías Utilizadas

    * Java 17
    * Spring Boot 3.1.5
    * Spring Data JPA
    * Spring Kafka
    * PostgreSQL
    * Maven
    * Docker

    ##  Prerrequisitos

    Antes de comenzar, asegúrate de tener instalado lo siguiente:

    * Java Development Kit (JDK) 17 o superior
    * Maven
    * Docker y Docker Compose (si se ejecuta con Docker)
    * PostgreSQL (si se ejecuta manualmente)

    ##  Configuración

    ###   Configuración de la Base de Datos

    1.  **Instalación de PostgreSQL:** Si no tienes PostgreSQL instalado, descárgalo e instálalo desde el sitio web oficial de PostgreSQL.
    2.  **Crear Bases de Datos:** Crea dos bases de datos llamadas `client_persona` y `account_movement`. Puedes usar una herramienta como pgAdmin o la interfaz de línea de comandos de PostgreSQL (psql):

        ```sql
        CREATE DATABASE client_persona;
        CREATE DATABASE account_movement;
        ```

    ###   Configuración de Kafka

    Este proyecto utiliza Kafka para la comunicación asíncrona entre el `cliente-persona-service` y el `cuenta-movimiento-service`.

    1.  **Docker (Recomendado):** La forma más sencilla de configurar Kafka es utilizando el `docker-compose.yml` proporcionado, que incluye Zookeeper y Kafka. Si usas Docker, puedes omitir la configuración manual de Kafka.
    2.  **Configuración Manual:** Si prefieres instalar y ejecutar Kafka manualmente:
        * Descarga Kafka desde el sitio web de Apache Kafka.
        * Sigue la guía de inicio rápido de Kafka para iniciar Zookeeper y Kafka.

    ##  Construcción de la Aplicación

    Puedes construir la aplicación utilizando Maven. Abre una terminal en el directorio `parent-project` y ejecuta:

    ```bash
    mvn clean install
    ```

    Esto compilará el código, ejecutará las pruebas unitarias y empaquetará cada microservicio en un archivo JAR en sus respectivos directorios `target/`.

    ##  Ejecución de la Aplicación

    ###   Ejecución con Docker Compose (Recomendado)

    La forma más sencilla de ejecutar toda la aplicación es con Docker Compose.

    1.  **Navega al directorio `parent-project`.**
    2.  **Ejecuta Docker Compose:**

        ```bash
        docker-compose up -d
        ```

        Este comando:

        * Construirá las imágenes de Docker para `client-persona-service` y `account-movement-service`.
        * Iniciará la base de datos PostgreSQL.
        * Iniciará Zookeeper y Kafka.
        * Ejecutará los dos microservicios, conectándolos a la base de datos y Kafka.

    3.  **Accede a los servicios:**
        * `client-persona-service`: http://localhost:8081
        * `account-movement-service`: http://localhost:8082

    ###   Ejecución Manual

    Si prefieres ejecutar los microservicios manualmente (sin Docker):

    1.  **Asegúrate de que PostgreSQL y Kafka estén en ejecución.**
    2.  **Actualiza `application.properties`:**
        * En `client-persona-service/src/main/resources/application.properties` y `account-movement-service/src/main/resources/application.properties`, actualiza los detalles de conexión de la base de datos (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`) y los servidores de arranque de Kafka (`spring.kafka.bootstrap-servers`) para que coincidan con tu configuración local.
    3.  **Ejecuta cada aplicación Spring Boot:**
        * Navega al directorio de cada microservicio (por ejemplo, `client-persona-service/`) y ejecuta:

            ```bash
            mvn spring-boot:run
            ```

        * O, puedes ejecutar los archivos JAR creados durante el proceso de construcción:

            ```bash
            java -jar target/*.jar
            ```

        * Asegúrate de ejecutar tanto `client-persona-service` (en el puerto 8081) como `account-movement-service` (en el puerto 8082).

    ##  Pruebas

    ###   Pruebas Unitarias

    Para ejecutar las pruebas unitarias de cada servicio, navega al directorio del servicio y ejecuta:

    ```bash
    mvn test
    ```

    Esto ejecutará las pruebas JUnit en el directorio `src/test/java/`.

    ###   Pruebas de Integración

    Las pruebas de integración generalmente requieren que los servicios y las dependencias externas (como Kafka) estén en ejecución. Puedes ejecutarlas de forma similar a las pruebas unitarias usando Maven, pero asegúrate de que tu entorno esté configurado correctamente.

    ##  Endpoints de la API

    **Client-Persona-Service (8081)**

    * `/clientes` (GET, POST, PUT, DELETE)
    * `/clientes/{id}` (GET, PUT, DELETE)

    **Account-Movement-Service (8082)**

    * `/cuentas` (GET, POST, PUT, DELETE)
    * `/cuentas/{id}` (GET, PUT, DELETE)
    * `/cuentas/cliente/{clienteId}` (GET)
    * `/movimientos` (GET, POST, PUT, DELETE)
    * `/movimientos/{id}` (GET, PUT, DELETE)
    * `/movimientos/cuenta/{numeroCuenta}` (GET)
    * `/movimientos/reporte` (GET) -   Requiere `numeroCuentas`, `startDate`, `endDate` como parámetros de consulta.

    ##  Colección de Postman

    Se proporciona un archivo de colección de Postman (`BankingApp.postman_collection.json` - *nombre de ejemplo*) en el directorio raíz para ayudarte a probar los endpoints de la API. Importa esta colección en Postman.

    ##  Script de la Base de Datos

    El esquema de la base de datos se puede crear utilizando el script `BaseDatos.sql` ubicado en el directorio raíz.

    ##  Notas Importantes

    * **Seguridad:** Esta implementación carece de características de seguridad como autenticación y autorización. En un entorno de producción, debes agregar Spring Security o un framework similar.
    * **Manejo de Errores:** Si bien se implementa un manejo básico de excepciones, considera agregar un registro y manejo de errores más robustos.
    * **Escalabilidad y Resiliencia:** Esta es una implementación básica. Para la escalabilidad y la resiliencia, explora conceptos como:
        * Balanceo de carga
        * Particionamiento de la base de datos (sharding)
        * Caché
        * Interruptores de circuito (Circuit Breaker) (Resilience4j)
    * **Idempotencia:** Para operaciones críticas (especialmente en `account-movement-service`), asegura la idempotencia para evitar transacciones duplicadas.
    * **Transacciones Distribuidas:** Para transacciones complejas que abarcan servicios, considera los patrones Saga.
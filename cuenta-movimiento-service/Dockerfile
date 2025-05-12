# Utiliza una imagen base con Java 17 (puedes cambiar la versión si es necesario)
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
# Asegúrate de que 'account-movement-service-0.0.1-SNAPSHOT.jar' sea el nombre de tu JAR
COPY target/cuenta-movimiento-service-1.0-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación escucha (configurado en tu application.properties o application.yml)
EXPOSE 8082

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
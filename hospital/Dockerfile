# --- ETAPA 1: Construcción (Multi-stage build) ---
# En esta primera etapa utilizamos una imagen base de Maven que incluye el JDK 21. 
# Esto nos permite compilar el código fuente sin empaquetar herramientas innecesarias en la imagen final.
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor a /app donde ejecutaremos los comandos.
WORKDIR /app

# Copiamos el archivo pom.xml primero descargar las dependencias. 
# Hacer esto antes del código fuente aprovecha la capa de caché de Docker, acelerando los builds subsecuentes.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente del proyecto al directorio actual.
COPY src ./src

# Ejecutamos Maven para empaquetar nuestra aplicación en un archivo .jar ejecutable.
# Se salta la ejecución de pruebas para agilizar el tiempo del pipeline.
RUN mvn clean package -DskipTests

# --- ETAPA 2: Tiempo de Ejecución (Runtime) ---
# Usamos una imagen de JRE más ligera (Alpine) para mantener el peso total de nuestro contenedor al mínimo,
# mejorando la seguridad, velocidad de descarga y el despliegue.
FROM eclipse-temurin:21-jre-alpine

# Definimos el directorio de nuestra app.
WORKDIR /app

# Copiamos ÚNICAMENTE el archivo .jar generado desde la etapa "build".
# Así dejamos del lado todas las herramientas de compilación y código fuente, manteniendo la imagen lista para producción.
COPY --from=build /app/target/*.jar app.jar

# Informamos a Docker y a los desarrolladores que nuestra aplicación Spring Boot usa el puerto 8080.
EXPOSE 8080

# Definimos el punto de entrada, que contiene el comando que levantará nuestro servidor Java.
ENTRYPOINT ["java", "-jar", "app.jar"]

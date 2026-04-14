# --- ETAPA 1: Compilación (Multi-stage build evaluado) ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos el XML y descargamos dependencias (caché)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos el archivo .jar
COPY src ./src
RUN mvn clean package -DskipTests

# --- ETAPA 2: Tiempo de Ejecución (Runtime evaluado) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Traemos únicamente el ejecutable .jar de nuestra etapa previa
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de la aplicación
EXPOSE 8080

# Definimos el punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]

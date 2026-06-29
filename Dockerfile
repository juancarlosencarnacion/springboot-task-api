# Etapa 1: Compilación interna en Docker
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Compilar usando el caché nativo para la carpeta .m2
RUN --mount=type=cache,target=/root/.m2 ./mvnw package -DskipTests

# Etapa 2: Imagen final de ejecución (Liviana)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT [ "java", "-jar", "app.jar" ]
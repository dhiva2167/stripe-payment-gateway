# ---- Build stage ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy Maven wrapper and pom first (layer caching for faster rebuilds)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy source and build the jar (skip tests for faster deploys)
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---- Run stage ----
FROM eclipse-temurin:17-jre AS run
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/Payment-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENV SERVER_PORT=8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]

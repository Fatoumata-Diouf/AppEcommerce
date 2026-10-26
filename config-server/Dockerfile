# Étape 1 : construire l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# Étape 2 : exécuter l'application
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/commande-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
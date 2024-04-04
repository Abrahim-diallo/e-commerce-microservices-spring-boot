# Étape de construction
FROM adoptopenjdk:17-jdk-slim AS builder

# Définition du répertoire de travail
WORKDIR /app

# Copie de l'application JAR dans l'image Docker
COPY target/api-gateway-v0.0.1-SNAPSHOT.jar app.jar

# Étape d'exécution
FROM adoptopenjdk:17-jdk-slim

# Définition du répertoire de travail
WORKDIR /app

# Copie de l'application JAR depuis l'étape de construction
COPY --from=builder /app/app.jar .

# Commande par défaut pour lancer l'application
CMD ["java", "-jar", "app.jar"]

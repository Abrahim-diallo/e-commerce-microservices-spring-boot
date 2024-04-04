# Étape de construction
FROM openjdk:17-alpine AS builder

# Définition du répertoire de travail
WORKDIR /app

# Copie de l'application JAR dans l'image Docker
COPY target/ms-discovery-service-v0.0.1-SNAPSHOT.jar app.jar

# Étape d'exécution
FROM openjdk:17-alpine

# Définition du répertoire de travail
WORKDIR /app

# Copie de l'application JAR depuis l'étape de construction
COPY --from=builder /app/app.jar .

# Commande par défaut pour lancer l'application
CMD ["java", "-jar", "app.jar"]

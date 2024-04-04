# Utilisation d'une image de base OpenJDK 17 slim
FROM openjdk:17-jdk-slim AS builder

# Définition du répertoire de travail
WORKDIR /app

# Copie de l'application JAR dans l'image Docker
COPY target/ms-cloud-config-v0.0.1-SNAPSHOT.jar app.jar

# Configuration de l'utilisateur non privilégié
USER nobody:nogroup

# Commande par défaut pour lancer l'application
CMD ["java", "-jar", "app.jar"]

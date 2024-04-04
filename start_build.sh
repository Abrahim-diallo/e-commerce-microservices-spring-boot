#!/bin/bash

services=("ms-discovery-service" "ms-cloud-config" "api-gateway" "ms-customer-service" "ms-commande-service" "ms-produit-service")


# iterate through each service and build
for service in "${services[@]}"
do
    cd ${service}
    mvn clean package -DskipTests
    cd ..
done
# Credit-Service
Full architecture with Microservices, Java 11, Spring-boot 2.6.4, RabbitMQ, Keycloak, Eureka, Gateway e H2

# What is it?
The Credit Microservice is responsible for evaluating credit card issuance based on specific criteria. It allows customers to request a credit card issuance and provide a response with an approval or receipt decision based on the information provided.

# How to run
Local:
```bash
# First: Run the RabbitMQ
docker run -it --rm --name cursorabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management

# Second: Run the Keycloak
docker run --name cursomskeycloak -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.3 start-dev

# Third: In the Keycloak, import the realm file located in "Credit-Service\Keycloak" folder and get the token

# Fourth: Run the Eureka -> mscliestes -> mscartoes -> msavaliadorcredito -> msavaliadorcredito -> mscloudgateway
```

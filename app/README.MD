
# Development

>[!NOTE]
> The main branch of this repository is automatically deployed to the vps behind flitsfiets.nl

To get started in developing this project first build the common dependencies:
```bash
./mvnw clean install

```
You can then run the services using:
```bash
./mvnw spring-boot:run -pl services/frontend
./mvnw spring-boot:run -pl services/onboarding
./mvnw spring-boot:run -pl services/appointments
...
```
You can also run multiple services at once:
```bash
./mvnw spring-boot:start -pl services/frontend,services/onboarding,services/users,services/appointments
```
They can then be stopped by running:

```bash
./mvnw spring-boot:stop -pl services/frontend,services/onboarding,services/users,services/appointments
```
## Docker
To run the tools ran in docker you can use docker-compose with the dev profile:
```bash
docker compose --profile dev up
```

## Ports
| Type          | Name         | Responsibilities                                                     | Port | JMX Port | UI    |
|---------------|--------------|----------------------------------------------------------------------|------|----------|-------|
| Proxy         | Caddy        | Connecting the right services                                        | 80   | -        |       |
| Event Channel | RabbitMQ     |                                                                      | 5672 | -        | 15672 |
| Auth          | Keycloak     |                                                                      | 8080 | -        |       |
| Service       | Frontend     | Serving the frontend                                                 | 8000 | 9100     |       | 
| Service       | Onboarding   | Serving the onboarding and handling the correct onboarding api calls | 8010 | 9101     |       | 
| Service       | Users        | User data and subscription management.                               | 6001 | 9102     |       | 
| Service       | Appointments | Appointment planning.                                                | 6000 | 9103     |       |

# Deployment

To deploy the services the docker images need to be built. This can be done using:
```bash
mvn clean install -Pimage
```

The application can them be started using docker-compose:
```bash
docker compose --profile prod up --wait
```

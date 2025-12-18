# Development

> [!NOTE]
> The main branch of this repository is automatically deployed to the vps behind flitsfiets.nl

To get started in developing this project first build the common dependencies:

```bash
./mvnw clean install

```

You can then run the services using:

```bash
./mvnw spring-boot:run -pl services/frontend -Pdev
./mvnw spring-boot:run -pl services/onboarding -Pdev
./mvnw spring-boot:run -pl services/appointments -Pdev
...
```

You can also run multiple services at once:

```bash
./mvnw spring-boot:start -pl services/frontend,services/onboarding,services/users,services/appointments,services/mailing -Pdev
```

They can then be stopped by running:

```bash
./mvnw spring-boot:stop -pl services/frontend,services/onboarding,services/users,services/appointments,services/mailing -Pdev
```

> [!TIP]
> The dev profiles changes the base-url of the services. When running in docker, the services use docker dns for
> discovery. As dev is not ran in docker the services are discovered by port. For security purposes those ports are not
> exposed in the docker environment.

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
| Service       | Mailing      | Mailing                                                              | 6002 | 9104     |       |

# Deployment

To deploy the services the docker images need to be built. This can be done using:

```bash
mvn clean install -Pimage
```

The application can them be started using docker-compose:

```bash
docker compose --profile prod up --wait
```

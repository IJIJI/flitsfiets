# FlitsFiets - Een fiets in een flits!
To gain more experience in developing microservice applications, I am building FlitsFiets: **the** application to get a well-built bike in a flash.

> [!CAUTION]
> This is a personal project and is not intended for production use. It is built for learning purposes only, and there are definitely some security issues.

## Brief description
FlitsFiets is a bicycle subscription service. You can choose a subscription plan and pick up the corresponding bike the same day. Within your plan, maintenance is included, for which you can make appointments in the app. For long repairs, you can get a loaner bike within a flash, which you can use while yours is being repaired. FlitsFiets offers its users the guarantee of a functioning bike, always.

## Technology
This project is built using a microservice architecture using Spring Boot services. 

These are the following services:
- Onboarding: Handles user registration and onboarding. There is no authentication on onboarding, hence the separation.
- Frontend: Serves the main and onboarding web application.
- Appointments: Manages all appointments.
- Subscriptions: Manages sunscription plans and user subscriptions.
- Fleet: Manages the fleet of bikes, including loaner bikes.
- Authentication: Handles authentication and authorization using JWT tokens.


# Development
To get started in developing this project first build the common dependencies:
```bash
./mvnw clean install

```
You can then run the services using:
```bash
./mvnw spring-boot:run -pl services/frontend
./mvnw spring-boot:run -pl services/onboarding
./mvnw spring-boot:run -pl services/appointments
```
You can also run multiple services at once:
```bash
./mvnw spring-boot:start -am -pl services/appointments,services/frontend,services/onboarding
```


## Ports
| Type     | Name         | Port | JMX Port |
|----------|--------------|------|----------|
| Database | Postgress    | 5432 | -        |
| Auth     | Keycloak     | 8080 | -        |
| Service  | Frontend     | 8000 | 9101     |
| Service  | Subscription | 6001 | 9102     |
| Service  | Appointments | 6000 | 9103     |
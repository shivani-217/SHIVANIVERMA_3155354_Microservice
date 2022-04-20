# Microservice_Urban_Clap_Assignment

1. Eureka Server is Netflix Eureka which requires self registration from client.

2. Api Gateway
- As you can see there are multiple services which expose external API to client. The client can make calls directly to all the available services but there are challenges like need to know all the endpoints addresses etc. The better approach is to use API Gateway which will act as a single entry point into the system and will handle requests by routing them to the appropriate backend service.
I have used Spring Cloud Gateway to implement the same.

3. Consumer Service 
- Contains API related to creating and retrieving all consumer information.
- Register this service to eureka server.

4 Product Service
- Contains API related to creating and retrieving all product information.
- Register this service to eureka server.	

5 Provider Service
- Contains API related to creating and retrieving providers available based on service.
- Register this service to eureka server.
6 Order Service
- Register this service to eureka server.
- Have exposed endpoint to place order, assign order to service provider and update order according to provider decision
- Act as a producer to trigger notifications at rabbitmq
- Internal calls to consumer service to fetch consumer details
- Internal api calls to provider service to fetch provides for the service requested
- Implemented Circuit Breaker Pattern at api level in case consumer service is down

7 Notification Service
- Act as a consumer to messages from rabbitmq.


Tech Stack:-
1. Java
2. Spring boot
3. Spring Cloud Gateway


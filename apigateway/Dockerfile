FROM mcr.microsoft.com/openjdk/jdk:11-ubuntu

EXPOSE 8080

RUN mkdir /opt/app
COPY build/libs/apigateway.jar /opt/app
CMD ["java", "-jar", "/opt/app/apigateway.jar"]
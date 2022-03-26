FROM mcr.microsoft.com/openjdk/jdk:11-ubuntu

EXPOSE 8181

RUN mkdir /opt/app
COPY build/libs/echo.jar /opt/app
CMD ["java", "-jar", "/opt/app/echo.jar"]
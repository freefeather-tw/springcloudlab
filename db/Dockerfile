FROM mcr.microsoft.com/openjdk/jdk:11-ubuntu

EXPOSE 8282

RUN mkdir /opt/app
COPY build/libs/db.jar /opt/app
CMD ["java", "-jar", "/opt/app/db.jar"]
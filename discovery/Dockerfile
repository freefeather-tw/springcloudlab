FROM mcr.microsoft.com/openjdk/jdk:11-ubuntu

EXPOSE 8383

RUN mkdir /opt/app
COPY build/libs/discovery.jar /opt/app
CMD ["java", "-jar", "/opt/app/discovery.jar"]
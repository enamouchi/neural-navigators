FROM openjdk:11-jdk-alpine
EXPOSE 8080
ADD target/tp-foyer-5.0.0.jar tp-foyer.jar
ENTRYPOINT ["java","-jar","/tp-foyer.jar"]


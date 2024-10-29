FROM openjdk:11
EXPOSE 8084
ADD target/tp-foyer-5.0.0.jar tp-foyer.jar
ENTRYPOINT ["java", "-jar", "/tp-foyer.jar"]
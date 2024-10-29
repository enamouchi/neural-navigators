FROM oumou/tp-foyer:1.0.0
EXPOSE 8083
ADD target/tp-foyer-5.0.0.jar tp-foyer.jar
ENTRYPOINT ["java", "-jar", "/tp-foyer.jar"]
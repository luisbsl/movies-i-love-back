FROM openjdk:8
ADD target/movies-i-love.jar movies-i-love.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "movies-i-love.jar"]
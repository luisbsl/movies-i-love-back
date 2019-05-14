# Movies I love backend

Spring boot application for wrapping TMDB API and import upcoming movies and supply a REST API to be consumed by the **movies-i-love-front** app

Once imported these movies, its are filtered by current date, so that app just shows movies that will be released

The imported movies are storaged on the memory database H2 as a strategy to delivery movies's list as fast as possible 

Every day at 00:00h the app will run a Job to reimport the movies from TMDB so that it will be always updated

## Stack

* Java 8
* Spring Boot
* H2  
* Docker
* Git

## Packagin

```mvn clean -U package``

## Run in docker conainter

### Creating image
```docker build -t movies-i-love .```

### Running container
```docker run -d -p 8081:8081 --name movies-i-love-back movies-i-love```
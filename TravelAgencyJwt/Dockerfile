FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/

RUN mvn package


FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/TravelAgencyJwt-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "TravelAgencyJwt-0.0.1-SNAPSHOT.jar"]



#  heroku jar:deploy target/TravelAgencyJwt-0.0.1-SNAPSHOT.jar --app travel-agency-jwt
#  heroku plugins:install java   ---> Heroku + java pluggins
#  git push heroku master  ---> Deploying the App with Docker 


#  docker ps    ---> See the Containers List
#  docker inspect 4c0e8794d356    ---> Container ID (to know IP address of container)
#  docker run --rm -it 27039ccd10bc (image ID)  ---> Container ID
#  docker images    ---> See the images List in the Machine
#  docker build -t travelagencyjwt .   ---> Compile and generate image for Docker
#  docker run --rm -it 27039ccd10bc (image ID)  ---> Container ID

FROM openjdk:8
ADD build/libs/toyproject-0.0.1-SNAPSHOT.jar toyproject-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","toyproject-0.0.1-SNAPSHOT.jar"]

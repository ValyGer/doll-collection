FROM amazoncorretto:20-alpine-jdk
COPY target/*.jar user-app-24.1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/user-app-24.1.1-SNAPSHOT.jar"]
FROM amazoncorretto:20-alpine-jdk
COPY target/*.jar manager-app-24.1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/manager-app-24.1.1-SNAPSHOT.jar"]
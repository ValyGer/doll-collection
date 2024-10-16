FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar manager-app-24.1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/manager-app-24.1.1-SNAPSHOT.jar"]
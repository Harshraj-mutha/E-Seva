FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8069

ENTRYPOINT ["java","-jar","app.jar"]
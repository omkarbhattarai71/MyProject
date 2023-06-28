FROM openjdk:11-ea-9-jdk-slim
COPY target/MyProject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar", "/app.jar"]

#docker build . -t omkarbhattarai/testimage:0.1
#
#docker run omkarbhattarai/testimage:0.1

#docker push omkarbhattarai/testimage:0.1
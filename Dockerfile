FROM openjdk:8
RUN mkdir app
ADD target/productview-0.0.1-SNAPSHOT.jar app/docker-product-view-service.jar
WORKDIR app
RUN "pwd"
RUN "ls"
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "docker-product-view-service.jar"]




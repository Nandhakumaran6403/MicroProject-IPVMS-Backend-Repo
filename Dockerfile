FROM openjdk:18
WORKDIR /app
COPY ./target/ItParkVisitorManagementSystem-0.0.1-SNAPSHOT.jar /app
EXPOSE 1010
CMD ["java", "-jar", "ItParkVisitorManagementSystem-0.0.1-SNAPSHOT.jar"]
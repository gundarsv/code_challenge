# syntax=docker/dockerfile:1

FROM amazoncorretto:8u292

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

CMD ["chmod +x mvnw"]

COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
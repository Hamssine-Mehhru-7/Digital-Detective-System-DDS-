FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /workspace
COPY digital-detective/pom.xml ./pom.xml
COPY digital-detective/src ./src
RUN mvn -B package

FROM eclipse-temurin:25-jre

WORKDIR /app
RUN useradd --system --uid 10001 --create-home app \
    && chown app:app /app
COPY --from=build --chown=app:app \
    /workspace/target/digital-detective-1.0-SNAPSHOT.jar ./app.jar

USER app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM maven:3.9.9 as maven_builder
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:21-slim
#RUN apt-get update && apt-get install
# copy from maven_builder
COPY --from=maven_builder app/target/*.jar /app/api.jar
WORKDIR /app
# run app
EXPOSE 8080
ENTRYPOINT ["java","-jar","api.jar"]

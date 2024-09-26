FROM maven:3.9.6-sapmachine-17 as build

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests

FROM tomcat:jdk17-openjdk-slim-buster

COPY --from=build target/SenlaCourses-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
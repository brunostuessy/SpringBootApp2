####
# This Dockerfile is used in order to build a container that runs the Spring Boot application
#
# Build the image with:
#
# docker build -f docker/Dockerfile -t springboot/sample-demo .
#
# Then run the container using:
#
# docker run -i --rm -p 8081:8081 springboot/sample-demo
#
# If you want to include the debug port into your docker image
# you will have to expose the debug port (default 5005 being the default) like this :  EXPOSE 8080 5005.
# Additionally you will have to set -e JAVA_DEBUG=true and -e JAVA_DEBUG_PORT=*:5005
# when running the container
#
# Then run the container using :
#
# docker run -i --rm -p 8080:8080 quarkus/getting-started-async-jvm
#
# This image uses the `run-java.sh` script to run the application.
# This script computes the command line to execute your Java application, and
# includes memory/GC tuning.
# You can configure the behavior using the following environment properties:
# - JAVA_OPTS: JVM options passed to the `java` command (example: "-verbose:class")
# - JAVA_OPTS_APPEND: User specified Java options to be appended to generated options
#   in JAVA_OPTS (example: "-Dsome.property=foo")
#
# See https://developers.redhat.com/articles/2024/03/14/how-use-java-container-awareness-openshift-4#conclusion
#
# Openshift S2I example:
# java -XX:MaxRAMPercentage=80.0 -XX:+UseParallelGC -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=20 -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -XX:+ExitOnOutOfMemoryError -cp "." -jar /deployments/spring-boot-app.jar --server.port=8081
####
#FROM registry.access.redhat.com/ubi8/openjdk-21:1.20-3.1730773765 AS builder
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Build dependency offline to streamline build
RUN mkdir project
WORKDIR /home/jboss/project
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -Dmaven.test.skip=true
# compute the created jar name and put it in a known location to copy to the next layer.
# If the user changes pom.xml to have a different version, or artifactId, this will find the jar
RUN grep version target/maven-archiver/pom.properties | cut -d '=' -f2 >.env-version
RUN grep artifactId target/maven-archiver/pom.properties | cut -d '=' -f2 >.env-id
RUN mv target/$(cat .env-id)-$(cat .env-version).jar target/spring-boot-app.jar

FROM registry.access.redhat.com/ubi8/openjdk-21-runtime:1.20-2.1730773767
COPY --from=builder /home/jboss/project/target/spring-boot-app.jar /deployments/spring-boot-app.jar

EXPOSE 8081

ENV JAVA_OPTS_APPEND=""
ENV JAVA_APP_DIR="/deployments"
ENV JAVA_APP_JAR="/deployments/spring-boot-app.jar"

ENTRYPOINT ["/opt/jboss/container/java/run/run-java.sh", "--server.port=8081"]

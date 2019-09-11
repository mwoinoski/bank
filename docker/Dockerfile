FROM openjdk:8-jdk-alpine


# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/mservice/lib
# Add the service itself
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/mservice/mservice.jar

ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/usr/share/mservice/mservice.jar"]
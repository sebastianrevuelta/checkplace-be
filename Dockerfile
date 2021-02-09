FROM openjdk:14-jdk-alpine
ARG JAR_FILE=./target/checkplace-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/checkplace-be.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
EXPOSE 8080

CMD ["java", "-jar", "/opt/checkplace-be.jar"]

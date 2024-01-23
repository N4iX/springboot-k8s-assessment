FROM openjdk:17-oracle
EXPOSE 9090
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} springboot-k8s.jar
ENTRYPOINT ["java", "-jar", "/springboot-k8s.jar"]
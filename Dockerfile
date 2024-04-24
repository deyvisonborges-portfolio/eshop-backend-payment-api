FROM eclipse-temurin:21

COPY build/libs/*.jar /opt/app/application.jar

RUN groupadd -r spring && \
  useradd -r -g spring -s /bin/false spring

USER spring:spring

CMD ["java", "-jar", "/opt/app/application.jar"]

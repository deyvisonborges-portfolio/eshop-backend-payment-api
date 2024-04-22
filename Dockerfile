FROM eclipse-temurin:21

COPY build/libs/*.jar /opt/app/application.jar

# Adiciona o grupo spring
RUN groupadd -r spring && \
    # Adiciona o usuário spring ao grupo spring
    useradd -r -g spring -s /bin/false spring

USER spring:spring

CMD ["java", "-jar", "/opt/app/application.jar"]

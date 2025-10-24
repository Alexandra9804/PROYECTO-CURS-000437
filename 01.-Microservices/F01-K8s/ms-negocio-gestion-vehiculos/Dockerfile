FROM openjdk:21-jdk-slim
LABEL build_date="24-10-2025"
LABEL name="Microservicio de gestión vehiculos"
LABEL version="1.0.0"
LABEL autor="Galaxy Training"
LABEL description="Microservicio de gestión vehiculos"
COPY build/libs/ms-negocio-gestion-vehiculos-*.jar ms-negocio-gestion-vehiculos.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/ms-negocio-gestion-vehiculos.jar"]
FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=./target/*.jar

COPY ${JAR_FILE} /app/cliente-emprestimo.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8082

CMD ["java", "-jar", "cliente-emprestimo.jar"]
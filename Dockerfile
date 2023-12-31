FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# copia todo o conteudo do diretorio para a imagem
COPY . .

# instala o maven
RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

#Dockerfile

# //aqui é o run da aplicacao
ENTRYPOINT [ "java", "-jar", "app.jar" ]
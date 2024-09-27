# Use a imagem do JDK como base
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho no container
COPY Projeto-Final-Backend/target/pos-backend-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação irá rodar
EXPOSE 8080

# Define o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
# Usar a imagem base do OpenJDK
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar todos os arquivos do projeto para o contêiner
COPY . .

# Compilar o projeto
RUN ./mvnw clean install -DskipTests

# Expõe a porta usada pela aplicação
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "target/ConsumoEnergiaAPI-0.0.1-SNAPSHOT.jar"]

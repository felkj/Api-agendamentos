FROM gradle:8.11.1-jdk-21-and-23 AS builder

WORKDIR /app
COPY . .

# Dá permissão de execução ao gradlew
RUN chmod +x gradlew

# Builda o projeto com Gradle
RUN ./gradlew clean build --no-daemon

# Nova imagem só com JDK pra rodar
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado para a imagem final
COPY --from=builder /app/build/libs/*.jar app.jar

# Railway usa a porta 8080 por padrão
EXPOSE 8080

# Executa o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM gradle:8.11.1-jdk-21-and-23 as builder

ENV INSTALL_PATH=/barber-shop-api
WORKDIR $INSTALL_PATH

COPY . .

# Faz o build usando o Gradle wrapper (ou você pode mudar pra `gradle build`)
RUN ./gradlew build --no-daemon

# Agora começa uma nova imagem, mais leve
FROM eclipse-temurin:21-jdk
ENV INSTALL_PATH=/barber-shop-api
WORKDIR $INSTALL_PATH

COPY --from=builder /barber-shop-api/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

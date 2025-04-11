# Usar uma imagem base do Gradle com JDK
FROM gradle:8.11.1-jdk-21-and-23

# Atualizar pacotes e instalar dependências
RUN apt-get update && apt-get install -qq -y --no-install-recommends

# Definir o diretório de instalação
ENV INSTALL_PATH /barber-shop-api

# Criar o diretório
RUN mkdir $INSTALL_PATH

# Definir o diretório de trabalho
WORKDIR $INSTALL_PATH

# Copiar os arquivos do projeto para o container
COPY . .

# Definir variáveis de ambiente para conectar ao banco de dados
ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Comando para rodar a aplicação
CMD ["./gradlew", "bootRun"]

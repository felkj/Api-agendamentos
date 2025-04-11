FROM gradle:8.11.1-jdk-21-and-23

# Instalação de dependências adicionais, se necessário
RUN apt-get update && apt-get install -qq -y --no-install-recommends \
    curl \
    git

# Definir o diretório de instalação
ENV INSTALL_PATH /barber-shop-api
RUN mkdir $INSTALL_PATH

# Definir o diretório de trabalho
WORKDIR $INSTALL_PATH

# Copiar os arquivos para o container
COPY . .

# Configurar memória para Gradle e evitar o uso do daemon
ENV GRADLE_OPTS="-Xmx2g -Dorg.gradle.daemon=false"

# Executar o build (caso o build seja feito ao iniciar o container)
RUN gradle build --no-daemon

# Definir o comando de execução (caso o container precise rodar algum serviço)
CMD ["java", "-jar", "build/libs/barber-shop-api.jar"]

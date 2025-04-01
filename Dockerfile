
FROM openjdk:17-jdk-slim AS build


RUN apt-get update && apt-get install -y \
    maven \
    wget \
    make \
    g++ \
    libsnappy-dev \
    build-essential \
    && rm -rf /var/lib/apt/lists/*


RUN wget https://github.com/google/leveldb/archive/refs/tags/1.23.tar.gz && \
    tar -xzf 1.23.tar.gz && \

    ls -l && \

    cd leveldb-1.23 && ls -l


WORKDIR /app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY src ./src


RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim


RUN apt-get update && apt-get install -y \
    libsnappy1v5 \
    && rm -rf /var/lib/apt/lists/*


WORKDIR /app


COPY --from=build /app/target/dopamineDB-0.0.1-SNAPSHOT.jar dopamineDB.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "dopamineDB.jar"]

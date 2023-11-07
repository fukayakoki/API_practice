# FROM maven:3-eclipse-temurin-17 AS build
# COPY . .
# RUN mvn clean package -Dmaven.test.skip=true
#
# FROM eclipse-temurin:17-alpine
# COPY --from=build /target/smash-0.0.1-SNAPSHOT.jar demo.jar
# EXPOSE 4646
# ENTRYPOINT ["java", "-jar", "demo.jar"]
# ビルド用ステージ
FROM maven:3.8.4-eclipse-temurin-17 AS build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean package -DskipTests

# 実行用ステージ
FROM eclipse-temurin:17-jdk-focal
COPY --from=build /home/app/target/smash-0.0.1-SNAPSHOT.jar /usr/app/demo.jar
WORKDIR /usr/app
EXPOSE 4646
ENTRYPOINT ["java", "-jar", "demo.jar"]


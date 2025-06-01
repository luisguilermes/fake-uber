FROM azul/zulu-openjdk:21-latest AS builder
WORKDIR /build
COPY ./ .
RUN ./gradlew --no-daemon --build-cache assemble

FROM openjdk:21-jdk-slim
ENV JAVA_OPTS=""
EXPOSE 8080
EXPOSE 8081
COPY --from=builder /build/fu-squad-merchant/merchant-profile-svc/build/libs/merchant-profile-svc*.jar app.jar
CMD [ "java ${JAVA_OPTS} -jar app.jar" ]

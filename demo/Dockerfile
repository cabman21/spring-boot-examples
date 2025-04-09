FROM gradle:8.6.0-jdk17-alpine as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM eclipse-temurin:17-jdk-alpine
RUN apk --no-cache add curl
RUN apk add --no-cache aws-cli
WORKDIR /app

# set ARGS
ARG AWS_ACCESS_KEY_ID=''
ARG AWS_SECRET_ACCESS_KEY=''
ARG AWS_REGION=''

RUN aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID \
&& aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY \
&& aws configure set default.region $AWS_REGION

COPY --from=builder /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080

USER root
ENTRYPOINT ["java", "-jar", "app.jar"]

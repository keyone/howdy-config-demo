FROM openjdk:8u191-jre-alpine3.8

ENV JAVA_APP_JAR howdy-config-demo-0.1.jar

ARG user=keyone
ARG group=keyone
ARG uid=1000
ARG gid=1000

WORKDIR /

COPY target/$JAVA_APP_JAR .

RUN addgroup -g ${gid} ${group} \
    && adduser -u ${uid} -G ${group} -s /bin/sh -D ${user} \
    && chown -R ${user}:${group} $JAVA_APP_JAR

EXPOSE 8080

USER ${user}

CMD java -jar $JAVA_APP_JAR
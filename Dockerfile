FROM openjdk:11
ENV JAVA_APP_JAR howdy-config-demo-0.1.jar
WORKDIR /
COPY target/$JAVA_APP_JAR .
EXPOSE 8080
CMD java -jar $JAVA_APP_JAR
FROM openjdk:14-alpine
RUN apk --no-cache add curl
COPY build/libs/complete-*-all.jar complete.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "complete.jar"]
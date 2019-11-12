FROM openjdk:8-jdk-slim as build

COPY . /tmp/programm
RUN cd /tmp/programm \
    && sed -i s/\\r//g gradlew \
    && ./gradlew build --console=plain -x test\
    && mkdir /opt/programm \
   && cp build/libs/polls-0.0.1-SNAPSHOT.jar /opt/programm

FROM openjdk:8-jre-slim
COPY --from=build /tmp/programm/build/libs/polls-0.0.1-SNAPSHOT.jar /opt/programm/polls-0.0.1-SNAPSHOT.jar
EXPOSE 8085
WORKDIR /opt/programm
ENTRYPOINT ["java"]
CMD ["-jar", "polls-0.0.1-SNAPSHOT.jar"]

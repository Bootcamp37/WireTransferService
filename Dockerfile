FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/WireTransfer-0.0.1-SNAPSHOT.jar ms-wire-transfer.jar
ENTRYPOINT ["java","-jar","/ms-wire-transfer.jar"]
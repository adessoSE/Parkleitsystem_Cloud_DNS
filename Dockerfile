FROM ubuntu

RUN apt-get update

RUN apt-get install -y openjdk-17-jdk openjdk-17-jre

COPY build/libs/Cloud-DNS-0.0.1-SNAPSHOT.jar cloud-dns.jar

ENTRYPOINT ["java", "-jar", "/cloud-dns.jar"]
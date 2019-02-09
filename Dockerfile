FROM ghostben/java1.8.0_201:1.0.0
MAINTAINER ghostben
VOLUME /tmp
ADD target/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dfile.encoding=utf-8","-jar", "app.jar"]

EXPOSE 8080

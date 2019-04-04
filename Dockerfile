FROM gradle:5.3.1-jdk11

VOLUME /tmp

COPY . /home/gradle/adds-app
WORKDIR /home/gradle/adds-app

ENTRYPOINT ["gradle"]
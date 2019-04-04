FROM gradle:5.3.1-jdk11

VOLUME /tmp

COPY . /home/gradle/ads-app
WORKDIR /home/gradle/ads-app

ENTRYPOINT ["gradle"]

FROM gradle:5.3.1-jdk11

VOLUME /tmp

COPY . /home/gradle/flight-planner
WORKDIR /home/gradle/flight-planner

ENTRYPOINT ["gradle"]

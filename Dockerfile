FROM openjdk:11

RUN apt-get update && apt-get install -y curl gnupg2 && \
    echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list && \
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0xE0C56E857200E83A1F327B7E75BDFD22F0F8E4D8" | gpg --dearmor | tee /etc/apt/trusted.gpg.d/sbt.gpg && \
    apt-get update && apt-get install -y sbt

COPY . /app
WORKDIR /app

CMD ["sbt", "run"]


FROM openjdk:8

WORKDIR /PlayCassandraDocker

COPY . /PlayCassandraDocker

EXPOSE 9000

CMD java -jar ./target/scala-2.12/PlayCassandraDocker-assembly-0.1.jar
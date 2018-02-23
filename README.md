### Original source

Example is not finished for the sake of assignment original sources are based
on https://doc.akka.io/docs/akka-stream-kafka/current/consumer.html

### run kafka in docker and develop locally

`docker-compose run --rm --service-ports kafka`

### do development and run kafka in docker

* clean

`docker-compose run --rm sbt clean`

* run

`docker-compose run --rm sbt run`

* test

`docker-compose run --rm sbt test`

### troubleshooting

get hanging process of kafka if mounted to host

`sudo lsof -i tcp:9092`

if you intend to run development locally, you need to start kafka container,
retrieve container id via `docker ps` and place that id as a `kafka.hostname`
into `application.conf`, then you can `sbt run` locally

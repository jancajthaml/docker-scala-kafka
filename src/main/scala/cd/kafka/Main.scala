package cd.kafka

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App with LazyLogging {

  logger.info("Starting")

  KafkaProducer.processMessages.andThen({
    case Failure(msg) => msg.printStackTrace()
    case Success(_) =>
      logger.info("consuming messages")
      KafkaConsumer.consumeMessages
  })

  while (true) {

  }

  logger.info("Terminating")

}

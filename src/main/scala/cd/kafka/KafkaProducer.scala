package cd.kafka

import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

import scala.concurrent.ExecutionContext

object KafkaProducer extends LazyLogging {

  val producerSettings = ProducerSettings(system, new ByteArraySerializer, new StringSerializer)
    .withBootstrapServers(kafkaBroker)

  val producer = producerSettings.createKafkaProducer()

  def processMessages = Source(1 to 30)
    .map(_.toString)
    .map { msg =>
      logger.info(s"Producing message ${msg}")
      new ProducerRecord[Array[Byte], String]("sample", msg)
    }
    .runWith(Producer.plainSink(producerSettings))
}

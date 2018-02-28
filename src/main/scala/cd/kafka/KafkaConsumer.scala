package cd.kafka

import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.Sink
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object KafkaConsumer extends LazyLogging {

  val consumerSettings = ConsumerSettings(system, new ByteArrayDeserializer, new StringDeserializer)
    .withBootstrapServers(kafkaBroker)
    .withGroupId("group1")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val processor = new KafkaProcessor()

  def consumeMessages: Unit = {
    processor.loadOffset.foreach { fromOffset =>

      logger.info(s"Consuming offset ${fromOffset}")

      val partition = 1
      val subscription = Subscriptions.assignmentWithOffset(
        new TopicPartition("sample", partition) -> fromOffset
      )

      Consumer.plainSource(consumerSettings, subscription)
        .mapAsync(1)(processor.process)
        .runWith(Sink.ignore)

    }
  }

}

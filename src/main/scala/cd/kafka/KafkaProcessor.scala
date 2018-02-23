package cd.kafka

import java.util.concurrent.atomic.AtomicLong

import akka.Done
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerRecord

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class KafkaProcessor extends LazyLogging {

  private val offset = new AtomicLong

  def process(msg: ConsumerRecord[Array[Byte], String]): Future[Done] = {
    logger.debug(s"Processing message ${msg.value}")
    offset.set(msg.offset)
    Future.successful(Done)
  }

  def loadOffset(): Future[Long] = Future.successful(offset.get)
}

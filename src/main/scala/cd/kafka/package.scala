package cd

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

package object kafka {

  val kafkaBroker = ConfigFactory.load.getString("kafka.hostname") + ":9092"

  implicit val system = ActorSystem("cd-kafka")
  implicit val mat = ActorMaterializer()

}

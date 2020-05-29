package com.github.spark

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/26 2:29 下午
 **/
object KafkaWordProducer {
  def main(args: Array[String]): Unit = {
    if (args.length < 4) {
      System.err.println("Usage: KafkaWordProducer <metadataBrokerList> <topic> <messagePerSec> <wordsPerMessages>")
      System.exit(1)
    }
    val Array(brokers, topic, messagesPerSec, wordPerMessage) = args

    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    while (true) {
      (1 to messagesPerSec.toInt).foreach({
        messageNum =>
          val str = (1 to wordPerMessage.toInt).map(x => scala.util.Random.nextInt(10).toString)
            .mkString(" ")
          print(str)
          println()
          val message = new ProducerRecord[String,String](topic,null,str)
          producer.send(message)
      })
      Thread.sleep(1000)
    }

  }
}

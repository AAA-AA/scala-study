package com.github.spark

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 5:06 下午
 **/
object NetworkWordCount {
  def main(args: Array[String]): Unit = {
    if (args.length<2) {
      System.err.println("Usage: NetworkWordCount <hostname> <port>")
      System.exit(1)
    }
    val sparkConf = new SparkConf().setAppName("NetworkWordCount")
      .setMaster("local[2]")

    val ssc = new StreamingContext(sparkConf,Seconds(1))
    val lines = ssc.socketTextStream(args(0),args(1).toInt,StorageLevel.MEMORY_AND_DISK_SER)

    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x,1)).reduceByKey(_+_)

    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()

  }
}

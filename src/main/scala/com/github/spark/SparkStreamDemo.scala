package com.github.spark

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 4:25 下午
 **/
object SparkStreamDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkStreamDemo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(20))

    val filePath= "/Users/renhongqiang/Downloads/study/examples/streaming/logfile"
    val lines = ssc.textFileStream(filePath)
    val words = lines.flatMap(_.split(" "))

    val wordCounts = words.map(x => (x,1)).reduceByKey(_+_)

    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()

  }
}

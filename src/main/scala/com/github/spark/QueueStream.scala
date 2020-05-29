package com.github.spark

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 6:08 下午
 **/
object QueueStream {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("QueueStream").setMaster("local[2]")
    val ssc = new StreamingContext(conf,Seconds(2))
    val rddQueue = new mutable.SynchronizedQueue[RDD[Int]]()
    val queueStream = ssc.queueStream(rddQueue)
    val mappedStream = queueStream.map(r => (r%10,1))
    val reducedStream = mappedStream.reduceByKey(_+_)
    reducedStream.print()
    ssc.start()
    for (i <- 1 to 10) {
      rddQueue += ssc.sparkContext.makeRDD(1 to 100,2)
      Thread.sleep(1000)
    }
    ssc.stop()
  }
}

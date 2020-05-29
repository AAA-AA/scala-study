package com.github.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/27 2:25 下午
 **/
object NetworkWordCountStateful {
  def main(args: Array[String]): Unit = {
    val updateFunc = (values: Seq[Int], state:Option[Int]) => {
      val currentCount = values.foldLeft(0)(_ + _)
      val previousCount = state.getOrElse(0)
      Some(currentCount+previousCount)
    }
    StreamingExamples.setStreamingLogLevels()
    val sparkConf = new SparkConf().setAppName("NetworkWordCountStateful")
      .setMaster("local[2]")

    val sc = new StreamingContext(sparkConf,Seconds(5))
    val lines = sc.socketTextStream("localhost",9999)
    val words = lines.flatMap(_.split(" "))
    val wordDstream = words.map(x => (x, 1))
    val stateDstream = wordDstream.updateStateByKey[Int](updateFunc)
    stateDstream.print()
    sc.start()
    sc.awaitTermination()

  }

}

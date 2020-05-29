package com.github.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/26 3:35 下午
 **/
object KafkaWordCount {
  def main(args: Array[String]): Unit = {
    val sc = new SparkConf().setAppName("KafkaWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(sc,Seconds(10))
    ssc.checkpoint("/Users/renhongqiang/Downloads/study/examples/checkpoint")
    val topics = "wordsender"
    val numThreads = 1
    val zkQuorum = 1
    val group = ""
    val topicMap = topics.split(",").map((_,numThreads.toInt)).toMap
    //val lineMap = KafkaUtils.createDirectStream(ssc,zkQuorum,group,topicMap)

  }
}

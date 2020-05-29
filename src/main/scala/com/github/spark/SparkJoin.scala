package com.github.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 8:34 上午
 **/
object SparkJoin {
  def main(args: Array[String]): Unit = {
    if (args.length !=3) {
      println("usage is WordCount <rating> <movie> <output>")
      return
    }
    val conf = new SparkConf().setAppName("SparkJoin").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(args(0))

    val rating = textFile.map(line => {
      val fields = line.split("::")
      (fields(1).toLong,fields(2).toDouble)
    })
    val movieScores = rating.groupByKey().map(data => {
      val avg = data._2.sum/data._2.size
      (data._1,avg)
    })

    val movies = sc.textFile(args(1))
    val moviesKey = movies.map(line => {
      val fields = line.split("::")
      (fields(0).toLong,fields(1))
    }).keyBy(tup => tup._1)
    val result = movieScores.keyBy(tup => tup._1)
      .join(moviesKey)
      .filter(f => f._2._1._2 > 4.0)
      .map(f => (f._1,f._2._1,f._2._2._2))
    result.saveAsTextFile(args(2))
  }
}

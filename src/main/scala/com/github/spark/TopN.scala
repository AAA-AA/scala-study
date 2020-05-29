package com.github.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/24 6:08 下午
 **/
object TopN {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TopN").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    //val lines = sc.textFile("hdfs://localhost:9000/user/hadoop/spark/mycode/rdd/examples", 2)
    val lines = sc.textFile("file:/Users/renhongqiang/Downloads/study/examples/files", 2)
    var num = 0
    val result = lines.filter(line => (line.trim.length > 0) &&
      (line.split(",").length == 4))
      .map(_.split(",")(2))
      .map(x => (x.toLong, ""))
      .sortByKey(false)
      .map(x => x._1).take(5)
      .foreach(x => {
        num = num + 1
        println(num + "\t" + x)
      })


  }
}

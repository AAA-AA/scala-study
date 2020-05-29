package com.github.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/24 7:18 下午
 **/
object MaxAndMin {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("MaxAndMin").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val lines = sc.textFile("")
    val result = lines.filter(_.trim.length>0).map(line => ("key", line.trim.toLong)).groupByKey().map(x => {
      var min = Long.MaxValue
      var max = Long.MinValue
      for (num <- x._2) {
        if (num > max) {
          max = num
        }
        if (num < min) {
          min = num
        }
      }
      (max,min)
    }).collect().foreach(x => {
      println("max\t"+ x._1)
      println("min\t"+x._2)
    })
  }
}

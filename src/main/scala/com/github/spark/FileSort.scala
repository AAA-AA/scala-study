package com.github.spark

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/24 8:07 下午
 **/
object FileSort {
  def main(args: Array[String]): Unit = {
    val source = "file:/Users/renhongqiang/Downloads/study/examples/filesort"
    val target = "file:/Users/renhongqiang/Downloads/study/examples/result"
    val conf = new SparkConf().setAppName("TopN").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    var index = 0
    //val lines = sc.textFile("hdfs://localhost:9000/user/hadoop/spark/mycode/rdd/examples", 2)
    val lines = sc.textFile(source, 3)
    val result = lines.filter(_.trim.length>0).map(n => (n.trim.toLong,""))
      .partitionBy(new HashPartitioner(1)).sortByKey().map(t => {
      index += 1
      (index,t._1)
    })
    result.saveAsTextFile(target)
  }
}

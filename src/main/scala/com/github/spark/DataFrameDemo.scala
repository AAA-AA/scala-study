package com.github.spark

import org.apache.spark.sql.SparkSession

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 10:03 上午
 **/
object DataFrameDemo {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().getOrCreate()
    //spark.createDataFrame()
  }
}

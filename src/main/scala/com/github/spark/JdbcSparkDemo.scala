package com.github.spark

import java.util.Properties

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 11:18 上午
 **/
object JdbcSparkDemo {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("jdbcSparkDemo")
      .getOrCreate()
    val jdbcDF = spark.read.format("jdbc")
      .option("url","jdbc:mysql://127.0.0.1:3306/spark")
      .option("dbtable","student")
      .option("user","root")
      .option("password","1qaz2wsx")
      .option("driver","com.mysql.jdbc.Driver")
      .load()
    jdbcDF.show()

    val studentRDD = spark.sparkContext.parallelize(
      Array("3 Michale M 27","4 Tina F 18")
    ).map(_.split(" "))

    val schema = StructType(List(StructField("id",IntegerType,true),
      StructField("name",StringType,true),
      StructField("gender",StringType,true),
      StructField("age",IntegerType,true)
    ))

    val rowRDD = studentRDD.map(p => Row(p(0).toInt,p(1).trim,p(2).trim,p(3).toInt))

    val studentDF = spark.createDataFrame(rowRDD,schema)

    val prop = new Properties()
    prop.put("user","root")
    prop.put("password","1qaz2wsx")
    prop.put("driver","com.mysql.jdbc.Driver")

    studentDF.write.mode("append").jdbc("jdbc:mysql://127.0.0.1:3306/spark","spark.student", prop)




  }


}

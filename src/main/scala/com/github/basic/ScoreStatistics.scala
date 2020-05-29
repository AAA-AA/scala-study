package com.github.basic

import scala.io.Source

/**
 * @Author: renhongqiang
 * @Date: 2020/5/29 9:53 上午
 **/
object ScoreStatistics {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("/Users/renhongqiang/Downloads/study/code/scala-study/doc/test.txt")

    val fullData = source.getLines().map(_.split(" ")).toList
    val courseData = fullData.head.drop(2)
    val resultData = fullData.tail

    def statistics(lines: List[Array[String]]) = {
      for (i <- 2 to courseData.length+1) yield {
        val temp = lines.map(e => e(i).toDouble)
        (temp.sum,temp.sum/temp.length, temp.min, temp.max)
      }
    }

    def printResult(theResult: Seq[(Double,Double,Double,Double)])= {
      (courseData zip theResult) foreach {
        case (course,result) => {
          println(f"${course+":"}%-10s${result._1}%5.2f${result._2}%8.2f${result._3}%11.2f${result._4}%14.2f")
        }
      }
    }

    val allResult = statistics(resultData)
    printResult(allResult)





  }
}

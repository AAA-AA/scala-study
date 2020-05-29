package com.github.spark

import scala.io.Source

/**
 * @Author: renhongqiang
 * @Date: 2020/5/28 10:47 下午
 **/
object ScoreReport {
  def main(args: Array[String]): Unit = {
    val inputFile = Source.fromFile("/Users/renhongqiang/Downloads/study/code/scala-demo/doc/test.txt")
    val originalData = inputFile.getLines().map(_.split(" ")).toList

    val courseNames = originalData.head.drop(2)
    val allStudents = originalData.tail
    val courseNum = courseNames.length

    def statistic(lines:List[Array[String]]) = {
      for (i <- 2 to courseNum+1) yield {
        val temp = lines.map(elem => elem(i).toDouble)
        (temp.sum,temp.min,temp.max)
      }
    }

    def printResult(theResult:Seq[(Double,Double,Double)]): Unit = {
      (courseNames zip theResult) foreach {
        case (course,result) =>
          println(f"${course+":"}%-10s${result._1}%5.2f${result._2}%8.2f${result._3}%8.2f")
      }
    }
    val allResult = statistic(allStudents)
    println("course average min max")
    printResult(allResult)

    val (maleLines,femaleLines) = allStudents.partition{_(1) == "male"}

    val maleResult = statistic(maleLines)
    println("course average min max")
    printResult(maleResult)

    val femaleResult = statistic(femaleLines)
    println("course average min max")
    printResult(maleResult)


  }
}

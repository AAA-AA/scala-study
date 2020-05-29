package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/26 11:39 上午
 **/
object FuncTest {

  def outputFunc(num:Int):(Int) => Unit = {
    (number) => {
      var result = number*2
    }
  }

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    list.foreach(
      FuncTest.outputFunc(2)
    )
  }
}

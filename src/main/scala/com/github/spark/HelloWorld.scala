package com.github.spark

/**
  * Created by renhongqiang on 2019-09-12 17:19
  */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello World!")
    val intList = 1::2::3::Nil
    val counter: Int => Int = {(value) => value}
    val m1 = List(1,2,3)
    val add = (_:Int) + (_:Int)//等价于 (a:Int,b:Int) => a+b
    val m2 = m1.map(_*2)
    val f = (i:Int) => println(i)
    m1.foreach(f)

  }
}

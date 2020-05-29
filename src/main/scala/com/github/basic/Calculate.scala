package com.github.basic

import scala.io.StdIn.readInt

/**
 * @Author: renhongqiang
 * @Date: 2020/5/27 6:25 下午
 **/
object Calculate {
  def main(args: Array[String]): Unit = {
    var sn:Float = 0
    var n:Float = 1
    println("please input q:")
    val q = readInt()
    while (sn < q) {
      sn += (n+1)/n
      n +=1
    }
    println(s"sn=$sn")
  }
}

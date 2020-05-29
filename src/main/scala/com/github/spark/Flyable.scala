package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/21 11:03 上午
 **/
trait Flyable {
  var maxFlyHeight:Int
  def fly()
  def breathe(): Unit = {
    println("I can breathe")
  }
}

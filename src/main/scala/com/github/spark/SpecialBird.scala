package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/21 11:22 上午
 **/
class SpecialBird(flyHeight:Int) extends Animal("com.github.scala.SpecialBird") with Flyable with HasLegs {
  var maxFlyHeight:Int = flyHeight
  val legs = 2
  def fly(): Unit = {
    printf("I can fly at the height of %d", maxFlyHeight)
  }
}

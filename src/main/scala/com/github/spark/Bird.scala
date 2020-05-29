package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/21 11:07 上午
 **/
class Bird(flyHeight:Int) extends Flyable {
  var maxFlyHeight: Int = flyHeight

  def fly(){
    printf("I can fly at height of %d.", maxFlyHeight)
  }
}

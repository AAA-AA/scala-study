package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/21 11:17 上午
 **/
trait HasLegs {
  val legs:Int
  def move(){
    printf("I can walk with %d legs", legs)
  }
}

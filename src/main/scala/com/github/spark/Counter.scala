package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/20 5:35 下午
 **/
class Counter {
  private var privateValue = 0

  /**
   * 方法无参数可以省略括号
   * @return
   */
  def value = privateValue
  def value_= (newValue:Int): Unit = {
    if (newValue >0) privateValue = newValue
  }


  def increment(step:Int):Unit={value+=step}
  def current():Int={value}
}

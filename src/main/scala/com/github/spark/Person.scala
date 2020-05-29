package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/20 6:16 下午
 **/
object Person {
  private var lastId = 0
  def newPersonId = {
    lastId +=1
    lastId
  }
}

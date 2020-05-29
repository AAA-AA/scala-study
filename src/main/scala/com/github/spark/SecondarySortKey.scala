package com.github.spark

/**
 * @Author: renhongqiang
 * @Date: 2020/5/24 8:35 下午
 **/
class SecondarySortKey(val first:Int, val second:Int) extends Ordered[SecondarySortKey] with Serializable {
  override def compare(that: SecondarySortKey): Int = {
    if (this.first - that.first != 0) {
      this.first - that.first
    } else {
      this.second-that.second
    }
  }
}

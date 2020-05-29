package com.github.spark

import java.io.PrintWriter
import java.net.ServerSocket

import scala.io.Source
import scala.util.Random

/**
 * @Author: renhongqiang
 * @Date: 2020/5/25 5:43 下午
 **/
object DataSourceSocket {
  def index(length:Int) = {
    val rdm = new Random()
    rdm.nextInt(length)
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 3) {
      System.err.println("Usage: <filename> port millisecond")
      System.exit(1)
    }
    val fileName = args(0)
    val lines = Source.fromFile(fileName).getLines().toList

    val rowCount = lines.length

    val listener = new ServerSocket(args(1).toInt)
    while (true) {
      val socket = listener.accept()
      new Thread() {
        override def run(): Unit = {
          println("Got client connected from: "+ socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream,true)
          while (true) {
            Thread.sleep(args(2).toLong)
            val content = lines(index(rowCount))
            println(content)
            out.write(content+"\n")
            out.flush()
          }
        }
      }.start()
    }



  }


}

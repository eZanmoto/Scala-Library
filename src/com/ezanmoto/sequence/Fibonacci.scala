package com.ezanmoto.sequence

class Fibonacci extends Iterator[Int] {

  private var previous = ( 0, 1 )

  def hasNext = true

  def next() = {
    previous = ( previous._2, previous._1 + previous._2 )
    previous._1
  }
}

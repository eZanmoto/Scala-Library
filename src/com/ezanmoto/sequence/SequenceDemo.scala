package com.ezanmoto.sequence

object SequenceDemo {
  def main( args: Array[String] ) = {
    val fib = new Fibonacci
    for ( i <- 0 to 10 )
      println( fib.next() )
  }
}

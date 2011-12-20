package com.ezanmoto.immutable.graph

import GraphProperty._

object GraphDemo {
  def main( args: Array[ String ] ) = {
    val graph = EdgeGraph[String]( cyclic ).+( "a" -> "b" )
                                           .+( "b" -> "d" )
                                           .+( "a" -> "c" )
    println( graph getVerticesAdjacentTo "a" )
  }
}

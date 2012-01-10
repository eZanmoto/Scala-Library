package com.ezanmoto.immutable.graph

import GraphProperty._

object GraphDemo {
  def main( args: Array[ String ] ) = {
    val graph = Graph[String]( cyclic ).+( "a" -> "b" )
                                       .+( "b" -> "d" )
                                       .+( "a" -> "c" )
    println( graph getVerticesAdjacentTo "a" )
    val pgraph = new PointGraph + ( 0, 0 ) + ( 1, 1 ) + ( 3, 4 )
    println( pgraph contains ( 1, 1 ) )
    println( pgraph contains ( 2, 2 ) )
  }
}

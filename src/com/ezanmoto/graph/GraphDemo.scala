package com.ezanmoto.graph

object GraphDemo {
  def main( args: Array[ String ] ) = {
    val graph = new MutableGraph[String]
    graph addEdge( "a", "b" )
    graph addEdge( "a", "c" )
    println( graph getVerticesAdjacentTo "a" )
  }
}

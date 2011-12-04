package com.ezanmoto.graph

import GraphProperty._

object GraphDemo {
  def main( args: Array[ String ] ) = {
    val graph = new HashGraph[String]( directed )
    graph addEdge( "a", "b" )
    graph addEdge( "c", "a" )
    graph addEdge( "a", "d" )
    println( graph getVerticesAdjacentTo "a" )
  }
}

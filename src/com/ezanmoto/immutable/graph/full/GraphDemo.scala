package com.ezanmoto.immutable.graph.full

import com.ezanmoto.immutable.graph.BFS

object GraphDemo {
  def main( args: Array[ String ] ) = {
    val graph = new HashGraph[String]().+( "a" -> "b" )
                                       .+( "b" -> "d" )
                                       .+( "a" -> "c" )
    println( graph getVerticesAdjacentTo "a" get )
    println( graph isDirectedForest )
    println( graph isDirectedTree )
    println( graph isSubgraphOf graph )
    println( graph getVerticesIncidentOn "b" )
    println( graph indegreeOf "b" )
    println( graph indegreeOf "a" )
    println( graph outdegreeOf "a" )
    println( graph degreeOf "a" )
  }
}

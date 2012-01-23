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
    for ( v <- BFS over graph from "a" )
      println( "Element: " + v )
    for ( v <- DFS over graph from "a" )
      println( "Element: " + v )

    val wgraph = new WeightedHashGraph[String,Int]( cyclic ).+( "u" -> "w", 5 )
                                                            .+( "u" -> "v", 1 )
                                                            .+( "u" -> "x", 2 )
                                                            .+( "v" -> "w", 3 )
                                                            .+( "x" -> "w", 4 )
                                                            .+( "x" -> "y", 7 )
                                                            .+( "w" -> "z", 6 )
                                                            .+( "y" -> "z", 2 )
    for ( a <- DFS over wgraph from "u" )
      for ( b <- wgraph getVerticesAdjacentTo a ) {
        val edge = ( a, b )
        println( "[dfs] Weight of " + edge + ": " + ( wgraph weightOf edge ) )
      }

    for ( edge <- BellmanFord of wgraph from "u" to "z" )
        println( "[b-f] Weight of " + edge + ": " + ( wgraph weightOf edge ) )
  }
}

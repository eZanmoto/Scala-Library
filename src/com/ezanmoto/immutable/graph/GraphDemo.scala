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
    val wgraph = new WeightedHashGraph[String,Int]( cyclic ).+( "a" -> "b", 4 )
                                                .+( "b" -> "d", 1 )
                                                .+( "a" -> "c", 2 )
    for ( a <- DFS over wgraph from "a" )
      for ( b <- wgraph getVerticesAdjacentTo a ) {
        val edge = ( a, b )
        println( "Weight of " + edge + ": " + ( wgraph weightOf edge ) )
      }
  }
}

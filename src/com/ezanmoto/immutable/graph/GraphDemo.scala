package com.ezanmoto.immutable.graph

object GraphDemo {
  def main( args: Array[ String ] ) = {
    val graph = ( UndirectedGraph[String]() + ( "a" -> "b" )
                                            + ( "b" -> "d" )
                                            + ( "a" -> "c" ) )
    println( graph getVerticesAdjacentTo "a" )
    for ( v <- BFS over graph from "a" )
      println( "Element: " + v )
    for ( v <- DFS over graph from "a" )
      println( "Element: " + v )

    val pgraph = new PointGraph + ( 0, 0 ) + ( 1, 1 ) + ( 3, 4 )
    println( pgraph contains ( 1, 1 ) )
    println( pgraph contains ( 2, 2 ) )

    val wgraph = ( new WeightedHashGraph[String, Int] + ( "u" -> "w", 5 )
                                                      + ( "u" -> "v", 1 )
                                                      + ( "u" -> "x", 2 )
                                                      + ( "v" -> "w", 3 )
                                                      + ( "x" -> "w", 4 )
                                                      + ( "x" -> "y", 7 )
                                                      + ( "w" -> "z", 6 )
                                                      + ( "y" -> "z", 2 ) )
    for ( a <- DFS over wgraph from "u" )
      for ( b <- ( wgraph getVerticesAdjacentTo a get ) ) {
        val e = ( a, b )
        println( "[dfs] Weight of " + e + ": " + ( wgraph weightOf e get ) )
      }

    for ( e <- BellmanFord of wgraph from "u" to "z" )
        println( "[b-f] Weight of " + e + ": " + ( wgraph weightOf e get ) )
  }
}

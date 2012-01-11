package com.ezanmoto.immutable.graph

import GraphProperty._

/** An adjacency list implementation of a graph.
  *
  * In the method complexities below, ',' indicates an assumption
  */
class HashGraph[T]( private val vertices: Map[T, Set[T]],
                    private val properties: GraphProperty* ) extends Graph[T] {

  require( this is cyclic, "HashGraph does not support non-cycles" )

  def this( ps: GraphProperty* ) = this( Map[T, Set[T]](), ps: _* )

  /** O(n), Seq implements contains in O(n) */
  def is( p: GraphProperty ) = properties contains p

  /** O(1), Map implements contains in O(1) */
  def contains( vertex: T ) = vertices contains vertex

  /** O(1), contains implemented in O(1)
    *     , Map implements + in O(1)
    */
  def +( vertex: T ): HashGraph[T] =
    if ( this contains vertex )
      this
    else
      new HashGraph[T]( vertices + ( vertex -> Set[T]() ), properties: _* )

  /** O(n), getVerticesAdjacentTo implemented in O(1)
    *     , Set implements + in O(n)
    */
  def +( edge: (T, T) ): HashGraph[T] =
    if ( ( this is simple ) && ( edge._1 equals edge._2 ) )
      throw new IllegalArgumentException( "Cannot add loops to simple graph" )
    else {
      val graph = this + edge._1 + edge._2 addEdge ( edge._1, edge._2 )
      if ( this is directed )
        graph
      else
        graph addEdge( edge._2, edge._1 )
    }

  private def addEdge( a: T, b: T ): HashGraph[T] = {
    val neighbours = getVerticesAdjacentTo( a )
    new HashGraph[T]( vertices + ( a -> ( neighbours + b ) ), properties: _* )
  }

  /** O(1), Map implements getOrElse in O(1) */
  def getVerticesAdjacentTo( vertex: T ) =
    if ( this contains vertex )
      vertices getOrElse( vertex, Set[T]() )
    else
      throw new IllegalArgumentException(
        "Graph doesn't contain vertex '" + vertex + "'" )
}

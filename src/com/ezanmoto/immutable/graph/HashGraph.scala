package com.ezanmoto.immutable.graph

import GraphProperty._

/**
  * An adjacency list implementation of a graph.
  *
  * Complexity:
  *   contains    O(1), map implements contains in O(1)
  *   + (vertex)  O(1), contains implemented in O(1)
  *                   , map implements + in O(1)
  *   + (edge)    O(n), getVerticesAdjacentTo implemented in O(1)
  *                   , set + implemented in O(n)
  *   getVerticesAdjacentTo
  *               O(1), map implements getOrElse in O(1)
  *
  * ',' indicates an assumption
  */
class HashGraph[T]( private val vertices: Map[T, Set[T]],
                    private val properties: GraphProperty* ) extends Graph[T] {

  require( this is cyclic, "HashGraph does not support non-cycles" )

  def this( ps: GraphProperty* ) = this( Map[T, Set[T]](), ps: _* )

  def is( p: GraphProperty ) = properties contains p

  def contains( vertex: T ) = vertices contains vertex

  def +( vertex: T ): HashGraph[T] =
    if ( this contains vertex )
      this
    else
      new HashGraph[T]( vertices + ( vertex -> Set[T]() ), properties: _* )

  def +( edge: (T, T) ): Graph[T] =
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
    val adjacents = getVerticesAdjacentTo( a )
    new HashGraph[T]( vertices + ( a -> ( adjacents + b ) ), properties: _* )
  }

  def getVerticesAdjacentTo( vertex: T ): Set[T] =
    if ( this contains vertex )
      vertices getOrElse( vertex, Set[T]() )
    else
      throw new IllegalArgumentException(
        "Graph doesn't contain vertex '" + vertex + "'" )
}

package com.ezanmoto.immutable.graph

import GraphProperty._

/** An adjacency list implementation of a weighted graph.
  *
  * In the method complexities below, ',' indicates an assumption
  */
class WeightedHashGraph[T, Int] ( private val graph: Graph[T]
                              , private val weights: Map[(T, T), Int]
                              ) extends WeightedGraph[T, Int] {

  // require( this is cyclic, "HashGraph does not support non-cycles" )

  def this( ps: GraphProperty* ) =
    this( Graph[T]( ps: _* ), Map[(T, T), Int]() )

  /** O(n), Seq implements contains in O(n) */
  def is( p: GraphProperty ) = graph is p

  /** O(1), Graph implements contains in O(1) */
  def contains( vertex: T ) = graph contains vertex

  /** O(1), Graph implements + in O(1) */
  def +( vertex: T ): WeightedHashGraph[T, Int] =
    new WeightedHashGraph( graph + vertex, weights )

  /** O(n), Graph implements + in O(n)
    *     , Map implements + in O(1)
    */
  def +( edge: (T, T), weight: Int ): WeightedHashGraph[T, Int] =
    new WeightedHashGraph( graph + edge, weights + ( edge -> weight ) )

  /** O(1), Map implements contains in O(1)
    *     , Map implements get in O(1)
    */
  def weightOf( edge: (T, T) ): Int =
    if ( weights contains edge )
      ( weights get edge ) get
    else
      throw new IllegalArgumentException(
        "Graph doesn't contain edge '" + edge + "'" )

  /** O(1), Graph implements getVerticesAdjacentTo in O(1) */
  def getVerticesAdjacentTo( vertex: T ) = graph getVerticesAdjacentTo vertex
}

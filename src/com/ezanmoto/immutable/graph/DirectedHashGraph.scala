package com.ezanmoto.immutable.graph

/** An adjacency list implementation of a graph.
  *
  * In the method complexities below, ',' indicates an assumption
  */
class DirectedHashGraph[T]( private val graph: Graph[T] ) extends Graph[T] {

  def this() = this( new UndirectedHashGraph() )

  /** O(1), Map implements contains in O(1) */
  def contains( vertex: T ) = graph contains vertex

  /** O(1), Graph implements + in O(1) */
  def +( vertex: T ): DirectedHashGraph[T] =
    new DirectedHashGraph[T]( graph + vertex )

  /** O(2n), Graph implements + in O(2n) */
  def +( edge: (T, T) ): DirectedHashGraph[T] =
    new DirectedHashGraph[T]( graph + edge + ( edge._2, edge._1 ) )

  /** O(1), Graph implements getVerticesAdjacentTo in O(1) */
  def getVerticesAdjacentTo( v: T ) = graph getVerticesAdjacentTo v
}

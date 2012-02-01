package com.ezanmoto.immutable.graph

/** A graph of points.
  *
  * Complexity:
  *   contains    O(n), map implements contains in O(1)
  *                   , set implements contains in O(n)
  *   +           O(n), graph implements + in O(n)
  *
  * ',' indicates an assumption
  */
class PointGraph( private val graph: Graph[Double] ) {

  def this() = this( DirectedGraph[Double]() )

  def +( point: (Double, Double) ) = new PointGraph( graph + point )

  def contains( point: (Double, Double) ) =
    graph getVerticesAdjacentTo point._1 match {
      case Some( set ) => set contains point._2
      case None => false
    }
}

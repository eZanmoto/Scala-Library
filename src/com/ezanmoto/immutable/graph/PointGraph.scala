package com.ezanmoto.immutable.graph

import GraphProperty._

/**
  * A graph that contains points.
  *
  * Complexity:
  *   contains    O(n), map implements contains in O(1)
  *                   , set implements contains in O(n)
  *   +           O(n), graph implements + in O(n)
  *
  * ',' indicates an assumption
  */
class PointGraph( private val graph: Graph[Double] ) {

  def this() = this( Graph[Double]( directed, cyclic ) )

  def +( point: (Double, Double) ) = new PointGraph( graph + point )

  def contains( point: (Double, Double) ) =
    if ( graph contains point._1 )
      ( graph getVerticesAdjacentTo point._1 ) contains point._2
    else
      false
}

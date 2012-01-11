package com.ezanmoto.immutable.graph.full

trait Graph[T] extends com.ezanmoto.immutable.graph.Graph[T] {
  /** Abstract */
  def isDirectedForest: Boolean
  def isDirectedTree: Boolean
  def isSubgraphOf( graph: Graph[T] ): Boolean
  def getVerticesIncidentOn( vertex: T ): Set[T]
  // def -( vertices: Set[T] ): Graph[T]

  /** Concrete */
  def indegreeOf( vertex: T ) = ( this getVerticesIncidentOn vertex ) size
  def outdegreeOf( vertex: T ) = ( this getVerticesAdjacentTo vertex ) size
  def degreeOf( vertex: T ) = indegreeOf( vertex ) + outdegreeOf( vertex )
}

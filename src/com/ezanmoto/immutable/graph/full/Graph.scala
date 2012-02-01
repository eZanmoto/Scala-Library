package com.ezanmoto.immutable.graph.full

trait Graph[T] extends com.ezanmoto.immutable.graph.Graph[T] {
  /** Abstract */
  def isDirectedForest: Boolean
  def isDirectedTree: Boolean
  def isSubgraphOf( graph: Graph[T] ): Boolean
  def getVerticesIncidentOn( vertex: T ): Option[Set[T]]
  // def -( vertices: Set[T] ): Graph[T]

  /** Concrete */
  def indegreeOf( vertex: T ) = this getVerticesIncidentOn vertex match {
    case Some( vs ) => Some( vs size )
    case None => None
  }

  def outdegreeOf( vertex: T ) = this getVerticesAdjacentTo vertex match {
    case Some( vs ) => Some( vs size )
    case None => None
  }

  def degreeOf( vertex: T ) = indegreeOf( vertex ) match {
    case Some( indegree ) => Some( indegree + outdegreeOf( vertex ).get )
    case None => None
  }

  def vertexIsIsolated( vertex: T ) = this degreeOf vertex match {
    case Some( degree ) => Some( degree == 0 )
    case None => None
  }
}

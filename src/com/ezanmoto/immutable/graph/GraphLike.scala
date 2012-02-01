package com.ezanmoto.immutable.graph

trait GraphLike[T] extends Immutable {
  def contains( vertex: T ): Boolean
  /** @return None if v does not exist */
  def getVerticesAdjacentTo( v: T ): Option[Set[T]]
  // def isDirected: Boolean
  // def isSimple: Boolean // No self-loops
  // def isCyclic: Boolean
}

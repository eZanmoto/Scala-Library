package com.ezanmoto.immutable.graph

trait GraphLike[T] extends Immutable {
  def contains( vertex: T ): Boolean
  def getVerticesAdjacentTo( v: T ): Set[T]
}

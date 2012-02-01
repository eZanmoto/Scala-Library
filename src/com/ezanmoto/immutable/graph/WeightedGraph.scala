package com.ezanmoto.immutable.graph

trait WeightedGraph[T, N] extends GraphLike[T] {
  def +( vertex: T ): WeightedGraph[T, N]
  def +( edge: (T, T), weight: N ): WeightedGraph[T, N]
  def weightOf( edge: (T, T) ): Option[N]
}

object WeightedGraph {
  def apply[T, N]() = new WeightedHashGraph[T, N]()
}

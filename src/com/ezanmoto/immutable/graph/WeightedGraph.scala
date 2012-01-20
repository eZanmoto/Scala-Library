package com.ezanmoto.immutable.graph

import GraphProperty._

trait WeightedGraph[T, N] extends GraphLike[T] {
  def is( p: GraphProperty ): Boolean
  def isNot( p: GraphProperty ) = ! is( p )
  def +( vertex: T ): WeightedGraph[T, N]
  def +( edge: (T, T), weight: N ): WeightedGraph[T, N]
  def weightOf( edge: (T, T) ): N
}

object WeightedGraph {
  def apply[T, N]( ps: GraphProperty* ) = new WeightedHashGraph[T, N]( ps: _* )
}

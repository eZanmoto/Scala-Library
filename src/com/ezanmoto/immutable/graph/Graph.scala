package com.ezanmoto.immutable.graph

trait Graph[T] extends GraphLike[T] {
  // def is( p: GraphProperty ): Boolean
  // def isNot( p: GraphProperty ) = ! is( p )
  def +( vertex: T ): Graph[T]
  def +( edge: (T, T) ): Graph[T]
}

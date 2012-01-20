package com.ezanmoto.immutable.graph

import GraphProperty._

trait Graph[T] extends GraphLike[T] {
  def is( p: GraphProperty ): Boolean
  def isNot( p: GraphProperty ) = ! is( p )
  def +( vertex: T ): Graph[T]
  def +( edge: (T, T) ): Graph[T]
}

object Graph {
  def apply[T]( ps: GraphProperty* ) = new HashGraph[T]( ps: _* )
}

package com.ezanmoto.immutable.graph

import GraphProperty._

trait EdgeGraph[T] extends VertexGraph[T] {
  def is( p: GraphProperty ): Boolean
  def isNot( p: GraphProperty ) = ! is( p )
  override def +( vertex: T ): EdgeGraph[T]
  def +( edge: (T, T) ): EdgeGraph[T]
  def getVerticesAdjacentTo( v: T ): Set[T]
}

object EdgeGraph {
  def apply[T]( ps: GraphProperty* ) =
    new HashGraph( Map[T, Set[T]](), ps: _* )
}

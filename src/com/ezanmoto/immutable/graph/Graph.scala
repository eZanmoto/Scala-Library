package com.ezanmoto.immutable.graph

import GraphProperty._

trait Graph[T] {
  def is( p: GraphProperty ): Boolean
  def isNot( p: GraphProperty ) = ! is( p )
  def contains( vertex: T ): Boolean
  def +( vertex: T ): Graph[T]
  def +( edge: (T, T) ): Graph[T]
  def getVerticesAdjacentTo( v: T ): Set[T]
}

object Graph {
  def apply[T]( ps: GraphProperty* ) = new HashGraph( Map[T, Set[T]](), ps: _* )
}

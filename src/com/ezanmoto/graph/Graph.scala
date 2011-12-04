package com.ezanmoto.graph

import java.util.Set

trait Graph[T] {
  def addVertex( v: T )
  def addEdge( a: T, b: T )
  def getVerticesAdjacentTo( v: T ): Set[T]
}

package com.ezanmoto.immutable.graph

trait VertexGraph[T] {
  def contains( vertex: T ): Boolean
  def +( vertex: T ): VertexGraph[T]
}

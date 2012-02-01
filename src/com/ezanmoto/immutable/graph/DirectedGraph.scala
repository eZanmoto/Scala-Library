package com.ezanmoto.immutable.graph

object DirectedGraph {
  def apply[T]() = new DirectedHashGraph[T]()
}

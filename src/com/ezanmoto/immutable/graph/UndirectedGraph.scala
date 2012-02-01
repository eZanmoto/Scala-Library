package com.ezanmoto.immutable.graph

object UndirectedGraph {
  def apply[T]() = new UndirectedHashGraph[T]()
}

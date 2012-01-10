package com.ezanmoto.immutable.graph

trait GraphIterator[T] {
  def from( vertex: T ): Iterator[T]
}

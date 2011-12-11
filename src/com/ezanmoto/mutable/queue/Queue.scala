package com.ezanmoto.mutable.queue

trait Queue[T] {
  def isEmpty: Boolean
  def size: Int
  def enqueue( element: T ): Unit
}

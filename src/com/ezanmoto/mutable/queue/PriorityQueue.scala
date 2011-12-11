package com.ezanmoto.mutable.queue

trait PriorityQueue[T <: Ordered[T]] extends Queue[T] {
  def dequeueMin(): T
}

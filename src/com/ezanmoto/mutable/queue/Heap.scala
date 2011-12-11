package com.ezanmoto.mutable.queue

import java.util.ArrayList

class Heap[T <: Ordered[T]] extends PriorityQueue[T] {

  private var heap = new ArrayList[T]

  def isEmpty = ( this size ) == 0

  def size = this.heap.size

  def enqueue( e: T ): Unit = {
    this.heap.add( e )
    bubbleUpElementAt( this.size - 1 )
  }

  def dequeueMin(): T = {
    this.heap.remove( 0 )
  }

  private def bubbleUpElementAt( childIndex: Int ): Unit = {
    if ( childIndex != 0 ) {
      val parentIndex = parentIndexOf( childIndex )
      if ( this.heap.get( parentIndex ) > this.heap.get( childIndex ) ) {
        swapElements( parentIndex, childIndex )
        bubbleUpElementAt( parentIndex )
      }
    }
  }

  private def parentIndexOf( childIndex: Int ): Int = {
    if ( childIndex > 0 ) {
      childIndex / 2
    } else {
      throw new IllegalArgumentException()
    }
  }

  private def swapElements( i: Int, j: Int ): Unit = {
    val tmp = this.heap.get( i )
    this.heap.set( i, this.heap.get( j ) )
    this.heap.set( j, tmp )
  }
}

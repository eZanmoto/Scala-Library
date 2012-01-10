package com.ezanmoto.immutable.graph

import java.util.NoSuchElementException

object DepthFirstIterator {
  def over[T]( graph: Graph[T] ) = new DepthFirstIterator( graph )
}

/** Alias for 'DepthFirstIterator' */
object DFI {
  def over[T]( graph: Graph[T] ) = new DepthFirstIterator[T]( graph )
}

/**
  * Algorithm properties:
  *   optimal     no
  *   complete    no
  *   memory      O(n)
  */
sealed class DepthFirstIterator[T]( private val graph: Graph[T] )
    extends GraphIterator[T] {

  def from( vertex: T ) =
    if ( graph contains vertex )
      this iteratorFrom vertex
    else
      throw new IllegalArgumentException(
        "No vertex '" + vertex + "' in graph" )

  private def iteratorFrom( vertex: T ) =
    new Iterator[T] {

      private val queue      = scala.collection.mutable.Stack[T]()
      private var discovered = scala.collection.mutable.Set[T]()

      this enqueue vertex

      private def enqueue( vertex: T ) = {
        queue push vertex
        discovered += vertex
      }

      def hasNext = ! ( queue isEmpty )

      def next =
        if ( hasNext ) {
          val next = queue pop
          val neighbours = reverse( graph getVerticesAdjacentTo next )
          for ( neighbour <- neighbours )
            if ( ! ( discovered contains neighbour ) )
              this enqueue neighbour
          next
        } else
          throw new NoSuchElementException( "Graph has no more vertices" )

      /**
        * Complexity:
        *   O(n), set implements head in O(1)
        *       , set implements drop(n) in O(n)
        */
      private def reverse[T]( s: Set[T] ): Set[T] =
        if ( s isEmpty )
          Set[T]()
        else
          reverse( s drop 1 ) + ( s head )
    }
}

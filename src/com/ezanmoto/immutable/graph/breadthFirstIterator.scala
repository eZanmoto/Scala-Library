package com.ezanmoto.immutable.graph

import java.util.NoSuchElementException

object BreadthFirstIterator {
  def over[T]( graph: Graph[T] ) = new BreadthFirstIterator( graph )
}

/** Alias for 'BreadthFirstIterator' */
object BFI {
  def over[T]( graph: Graph[T] ) = new BreadthFirstIterator[T]( graph )
}

/**
  * Algorithm properties:
  *   optimal     yes
  *   complete    yes
  *   memory      O(n^2)
  */
sealed class BreadthFirstIterator[T]( private val graph: Graph[T] )
    extends GraphIterator[T] {

  def from( vertex: T ) =
    if ( graph contains vertex )
      this iteratorFrom vertex
    else
      throw new IllegalArgumentException(
        "No vertex '" + vertex + "' in graph" )

  private def iteratorFrom( vertex: T ) =
    new Iterator[T] {

      private val queue      = scala.collection.mutable.Queue[T]()
      private var discovered = scala.collection.mutable.Set[T]()

      this enqueue vertex

      private def enqueue( vertex: T ) = {
        queue enqueue vertex
        discovered += vertex
      }

      def hasNext = ! ( queue isEmpty )

      def next =
        if ( hasNext ) {
          val next = queue dequeue
          val neighbours = graph getVerticesAdjacentTo next 
          for ( neighbour <- neighbours )
            if ( ! ( discovered contains neighbour ) )
              this enqueue neighbour
          next
        } else
          throw new NoSuchElementException( "Graph has no more vertices" )
    }
}

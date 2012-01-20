package com.ezanmoto.immutable.graph

/** Breadth First Search
  *
  * Complexity:
  *   optimal   yes
  *   complete  yes
  *   memory    O(n^2)
  */
object BFS {
  private class Queue_[T] extends scala.collection.mutable.Queue[T]
                          with Container[T] {
    def add( e: T ) = +=( e )
    def decapitate() = dequeue()
  }

  def over[T]( g: GraphLike[T] ) = new GraphIterator( g, new Queue_[T]() )
}

/** Depth First Search
  *
  * Complexity:
  *   optimal   no
  *   complete  no
  *   memory    O(n)
  */
object DFS {
  private class Stack_[T] extends scala.collection.mutable.Stack[T]
                          with Container[T] {
    def add( e: T ) = push( e )
    def decapitate() = pop()
  }

  def over[T]( g: GraphLike[T] ) = new GraphIterator( g, new Stack_[T]() )
}

trait Container[T] {
  def add( e: T )
  def decapitate(): T
  def isEmpty: Boolean
}

class GraphIterator[T]( private val graph: GraphLike[T],
                        private val queue: Container[T] ) {

  def from( vertex: T ) =
    if ( graph contains vertex )
      this iteratorFrom vertex
    else
      throw new IllegalArgumentException(
        "No vertex '" + vertex + "' in graph" )

  private def iteratorFrom( vertex: T ) =
    new Iterator[T] {

      private var discovered = scala.collection.mutable.Set[T]()

      this enqueue vertex

      private def enqueue( vertex: T ) = {
        queue add vertex
        discovered += vertex
      }

      def hasNext = ! ( queue isEmpty )

      def next =
        if ( hasNext ) {
          val next = queue decapitate
          val neighbours = graph getVerticesAdjacentTo next 
          for ( neighbour <- neighbours )
            if ( ! ( discovered contains neighbour ) )
              this enqueue neighbour
          next
        } else
          throw new NoSuchElementException( "Graph has no more vertices" )
    }
}

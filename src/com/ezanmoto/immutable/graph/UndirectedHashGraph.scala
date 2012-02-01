package com.ezanmoto.immutable.graph

/** An adjacency list implementation of a graph.
  *
  * In the method complexities below, ',' indicates an assumption
  */
class UndirectedHashGraph[T]( private val vertices: Map[T, Set[T]] )
    extends Graph[T] {

  def this() = this( Map[T, Set[T]]() )

  /** O(1), Map implements contains in O(1) */
  def contains( vertex: T ) = vertices contains vertex

  /** O(1), contains implemented in O(1)
    *     , Map implements + in O(1)
    */
  def +( vertex: T ): UndirectedHashGraph[T] =
    if ( this contains vertex )
      this
    else
      new UndirectedHashGraph( vertices + ( vertex -> Set[T]() ) )

  /** O(n), getVerticesAdjacentTo implemented in O(1)
    *     , ++ implemented in O(n)
    *     , Set implements + in O(n)
    */
  def +( edge: (T, T) ): UndirectedHashGraph[T] =
    this + edge._1 + edge._2 ++ ( edge._1, edge._2 )

  /** O(n), getVerticesAdjacentTo implemented in O(1)
    *     , Set implements + in O(n)
    *     , Map implements + in O(1)
    */
  private def ++( a: T, b: T ): UndirectedHashGraph[T] = {
    val neighbours = getVerticesAdjacentTo( a ) match {
      case Some( set ) => set
      case None => throw new IllegalStateException(
        "Graph should have a vertex '" + a + "'" )
    }
    new UndirectedHashGraph( vertices + ( a -> ( neighbours + b ) ) )
  }

  /** O(1), Map implements get in O(1) */
  def getVerticesAdjacentTo( v: T ) = vertices get v
}

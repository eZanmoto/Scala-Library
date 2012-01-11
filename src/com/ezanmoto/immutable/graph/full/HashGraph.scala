package com.ezanmoto.immutable.graph.full

import com.ezanmoto.immutable.graph.BFS
import com.ezanmoto.immutable.graph.GraphProperty._

class HashGraph[T]( private val adjacent: com.ezanmoto.immutable.graph.Graph[T]
                  , private val incident: com.ezanmoto.immutable.graph.Graph[T]
                  , private val lastVertex: Option[T]
                  , private val properties: GraphProperty* ) extends Graph[T] {

  def this() = this( com.ezanmoto.immutable.graph.Graph[T]( cyclic )
                   , com.ezanmoto.immutable.graph.Graph[T]( cyclic )
                   , None
                   , cyclic )

  private var nv = 0
  private var ne = 0

  if ( lastVertex != None )
    for ( v <- BFS over this from ( lastVertex get ) ) {
      nv += 1
      ne += ( this getVerticesAdjacentTo v ).size
    }

  private val numVertices = nv
  private val numEdges = ne

  /** O(n), Graph implements is in O(n) */
  def is( p: GraphProperty ) = adjacent is p

  /** O(1), Graph implements contains in O(1) */
  def contains( v: T ) = adjacent contains v

  /** O(1), Graph implements + in O(1) */
  def +( v: T ): HashGraph[T] =
    new HashGraph[T]( adjacent + v, incident, Some( v ), properties: _* )

  /** O(2n), Graph implements + in O(n) */
  def +( e: (T, T) ): HashGraph[T] =
    new HashGraph[T]( adjacent + e, incident + ( e._2, e._1 )
                    , Some( e._1 ), properties: _* )

  /** O(1), Graph implements getVerticesAdjacentTo in O(1) */
  def getVerticesAdjacentTo( v: T ) = adjacent getVerticesAdjacentTo v

  /** O(|V||E|n), Graph implements contains in O(1)
    *           , Graph implements getVerticesAdjacentTo in O(1)
    *           , Set implements contains in O(n)
    */
  def isSubgraphOf( graph: Graph[T] ): Boolean = {
    for ( v <- BFS over this from ( lastVertex get ) ) // O(|V||E|n)
      if ( graph contains v ) {
        val neighbours = graph getVerticesAdjacentTo v
        for ( e <- ( this getVerticesAdjacentTo v ) ) // O(|E|n)
          if ( ! ( neighbours contains e ) ) // O(n)
            return false
      } else
        return false
    return true
  }

  /** O(1), Graph implements getVerticesAdjacentTo in O(1) */
  def getVerticesIncidentOn( v: T ) = incident getVerticesAdjacentTo v

  /** O(n^2), getVerticesAdjacentTo implemented in O(1)
    *       , Set implements intersect in O(n)
  def -( vertices: Set[T] ): HashGraph[T] = {
    var a = com.ezanmoto.immutable.graph.Graph[T]( properties: _* )
    var i = com.ezanmoto.immutable.graph.Graph[T]( properties: _* )
    var lastVertex: Option[T] = None
    for ( v <- vertices ) {
      for ( e <- ( ( this getVerticesAdjacentTo v ) intersect vertices ) )
        a = a + ( v, e )
      for ( e <- ( ( this getVerticesIncidentOn v ) intersect vertices ) )
        i = i + ( v, e )
      lastVertex = Some( v )
    }
    new HashGraph[T]( a, i, lastVertex, properties: _* )
  }
    */

  val isDirectedForest = idf

  /** O(|V|), indegree implemented in O(1) */
  def idf: Boolean = {
    if ( this is cyclic )
      return false
    else {
      for ( v <- BFS over this from ( lastVertex get ) )
        if ( ( this indegreeOf v ) > 1 )
          return false
    }
    return true
  }

  val isDirectedTree = numEdges == ( numVertices - 1 )
}

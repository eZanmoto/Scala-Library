package com.ezanmoto.immutable.graph.full

import com.ezanmoto.immutable.graph.BFS

class HashGraph[T]( private val adjacent: com.ezanmoto.immutable.graph.Graph[T]
                  , private val incident: com.ezanmoto.immutable.graph.Graph[T]
                  , private val lastVertex: Option[T] ) extends Graph[T] {

  def this() = this( com.ezanmoto.immutable.graph.UndirectedGraph[T]()
                   , com.ezanmoto.immutable.graph.UndirectedGraph[T]()
                   , None )

  private var nv = 0
  private var ne = 0

  if ( lastVertex != None )
    for ( v <- BFS over this from ( lastVertex get ) ) {
      nv += 1
      ne += ( this getVerticesAdjacentTo v ).size
    }

  private val numVertices = nv
  private val numEdges = ne

  /** O(1), Graph implements contains in O(1) */
  def contains( v: T ) = adjacent contains v

  /** O(1), Graph implements + in O(1) */
  def +( v: T ): HashGraph[T] =
    new HashGraph[T]( adjacent + v, incident, Some( v ) )

  /** O(2n), Graph implements + in O(n) */
  def +( e: (T, T) ): HashGraph[T] =
    new HashGraph[T]( adjacent + e, incident + ( e._2, e._1 ), Some( e._1 ) )

  /** O(1), Graph implements getVerticesAdjacentTo in O(1) */
  def getVerticesAdjacentTo( v: T ) = adjacent getVerticesAdjacentTo v

  /** O(|V||E|n), Graph implements contains in O(1)
    *           , Graph implements getVerticesAdjacentTo in O(1)
    *           , Set implements contains in O(n)
    */
  def isSubgraphOf( graph: Graph[T] ): Boolean = {
    for ( v <- BFS over this from ( lastVertex get ) ) // O(|V||E|n)
      if ( graph contains v ) {
        val neighbours = graph getVerticesAdjacentTo v get
        val edges = ( this getVerticesAdjacentTo v get )
        for ( e <- edges ) // O(|E|n)
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

  private var isC: Option[Boolean] = None

  private def isCyclic = isC match {
    case Some( v ) => v
    case None => lastVertex match {
      case Some( vertex ) => {
        var discovered = Set[T]()
        var isC = false
        for ( v <- BFS over this from vertex )
          if ( discovered contains v )
            isC = true
          else
            discovered = discovered + v
        isC
      }
      case None => false
    }
  }

  private var isDF: Option[Boolean] = None

  /** O(|V|), indegree implemented in O(1) */
  val isDirectedForest = isDF match {
    case Some( b ) => b
    case None => lastVertex match {
      case Some( vertex ) =>
        if ( this isCyclic )
          false
        else {
          var isDF = true
          for ( v <- BFS over this from vertex )
            if ( ( this indegreeOf v get ) > 1 )
              isDF = false
          isDF
        }
      case None => true
    }
  }

  val isDirectedTree = numEdges == ( numVertices - 1 )

  private var isR: Option[Boolean] = None

  val isRegular = isR match {
    case Some( b ) => b
    case None => lastVertex match {
      case Some( vertex ) => {
        val degree = this degreeOf vertex
        var isR = true
        for ( v <- BFS over this from vertex )
          if ( ( this degreeOf v ) != degree )
            isR = false
        isR
      }
      case None => true
    }
  }

  private var isN: Option[Boolean] = None

  val isNull = isN match {
    case Some( b ) => b
    case None => lastVertex match {
      case Some( vertex ) => ( this vertexIsIsolated vertex get ) && isRegular
      case None => true
    }
  }
}

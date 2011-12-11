package com.ezanmoto.mutable.graph

import GraphProperty._

import java.util.HashMap
import java.util.HashSet
import java.util.Set

class HashGraph[T]( private val properties: GraphProperty* ) extends Graph[T] {

  require( ! hasProperty( nonCyclic ), "HashGraph does not support non-cycles" )

  protected val vertices = new HashMap[T, Set[T]]
  val isDirected = hasProperty( directed )
  val isSimple   = hasProperty( simple )

  def hasProperty( p: GraphProperty ) = properties contains p

  def containsVertex( v: T ): Boolean = vertices containsKey v

  def addVertex( v: T ) = {
    if ( ! ( this containsVertex v ) ) {
      vertices put( v, new HashSet[T] )
    }
  }

  def addEdge( e: (T, T) ) = {
    if ( ( this isSimple ) && ( e._1 equals e._2 ) ) {
      throw new IllegalArgumentException( "Cannot add loops to simple graph" )
    } else {
      addVertex( e._1 )
      addVertex( e._2 )
      vertices.get( e._1 ).add( e._2 )
      if ( ! ( this isDirected ) ) {
        vertices.get( e._2 ).add( e._1 )
      }
    }
  }

  def getVerticesAdjacentTo( v: T ): Set[T] = {
    if ( this containsVertex v ) {
      new HashSet( vertices get v )
    } else {
      throw new IllegalArgumentException(
        "Graph doesn't contain vertex '" + v + "'" )
    }
  }

  /*
  private type SubSquare = (Int, Int)
  private type Point = (Num, Num)

  def getClosestPoints(): Option[(Point, Point)] = {
    if ( weights.size() > 1 ) {
      val discovered = Map[SubSquare, T]
      val points = vertices getKeySet
      var closest = ( points.get( 0 ), points.get( 1 ) )
      var minDistance = getDistance( closest )
      for ( i <- 0 to points.size ) {
        val point = points get i
        val neighbour = getClosestNeighbourTo( point, discovered )
        val distance = getDistanceBetween( point, neighbour )
        if ( distance < minDistance ) {
          minDistance = distance
          closest = ( point, neighbour )
          discovered clear
          for ( j <- 0 to i ) {
            store( points get j, discovered )
          }
        }
        store( point, discovered )
      }
      closest
    } else {
      None( "Not enough points" )
    }
  }

  private def getClosestNeighbourTo( p: T, ps: Map[(Int, Int), T] ) = {
    val neighbours = getNeighboursOf( p, ps )
    var closestNeighbour = ps.remove( 0 )
    var minDistance = getDistanceBetween( p, neighbour )
    for ( neighbour <- neighbours ) {
      val distance = getDistanceBetween( p, neighbour )
      if ( distance < minDistance ) {
        minDistance = distance
        closestNeighbour = neigbour
      }
    }
    closestNeighbour
  }

  private def getNeighboursOf( p: T, ps: Map[(Int, Int), T] ) = {
  }
  */
}

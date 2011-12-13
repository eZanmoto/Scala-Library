package com.ezanmoto.immutable.graph

import GraphProperty._

class HashGraph[T]( private val vertices: Map[T, Set[T]],
                    private val properties: GraphProperty* ) extends Graph[T] {

  require( this.is( cyclic ), "HashGraph does not support non-cycles" )

  // val is( ps: GraphProperty* ) = ps forall { properties contains _ }
  def is( p: GraphProperty ): Boolean = properties contains p
  def contains( vertex: T ): Boolean = vertices contains vertex

  private def add( vertex: T ): HashGraph[T] = {
    if ( ! this.contains( vertex ) )
      new HashGraph[T]( vertices + ( vertex -> Set[T]() ), properties: _* )
    else
      this
  }

  def +( vertex: T ): Graph[T] = add( vertex )

  def +( edge: (T, T) ): Graph[T] = {
    if ( ( this.is( simple ) ) && ( edge._1 equals edge._2 ) ) {
      throw new IllegalArgumentException( "Cannot add loops to simple graph" )
    } else {
      val v = this.add( edge._1 ).add( edge._2 ).plus( edge._1, edge._2 )
      if ( this isNot directed )
        v.plus( edge._2, edge._1 )
      else
        v
    }
  }

  private def plus( a: T, b: T ): HashGraph[T] = {
    val vs = getVerticesAdjacentTo( a )
    new HashGraph[T]( vertices + ( a -> ( vs + b ) ), properties: _* )
  }

  def getVerticesAdjacentTo( vertex: T ): Set[T] = {
    if ( this contains vertex )
      vertices getOrElse( vertex, Set[T]() )
    else
      throw new IllegalArgumentException(
        "Graph doesn't contain vertex '" + vertex + "'" )
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

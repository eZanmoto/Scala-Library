package com.ezanmoto.graph

import GraphProperty._

import java.util.HashMap
import java.util.HashSet
import java.util.Set

class HashGraph[T]( private val properties: GraphProperty* ) extends Graph[T] {

  require( ! hasProperty( weighted ), "HashGraph does not support weights" )
  require( ! hasProperty( labelled ), "HashGraph does not support labels" )
  require( ! hasProperty( nonCyclic ), "HashGraph does not support non-cycles" )

  protected val vertices = new HashMap[T, Set[T]]
  private val isDirected = hasProperty( directed )
  private val isSimple   = hasProperty( simple )

  def hasProperty( p: GraphProperty ) = properties contains p

  def containsVertex( v: T ): Boolean = vertices containsKey v

  def addVertex( v: T ) = {
    if ( ! ( this containsVertex v ) ) {
      vertices put( v, new HashSet[T] )
    }
  }

  def addEdge( a: T, b: T ) = {
    if ( ( this isSimple ) && ( a equals b ) ) {
      throw new IllegalArgumentException( "Cannot add loops to simple graph" )
    } else {
      addVertex( a )
      addVertex( b )
      vertices.get( a ).add( b )
      if ( ! ( this isDirected ) ) {
        vertices.get( b ).add( a )
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
}

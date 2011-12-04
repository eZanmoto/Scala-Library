package com.ezanmoto.graph

import java.util.HashMap
import java.util.HashSet
import java.util.Set

class MutableGraph[T] extends Graph[T] {

  val vertices = new HashMap[T, Set[T]]

  def addVertex( v: T ) = {
    if ( vertices containsKey v ) {
    } else {
      vertices put( v, new HashSet[T] )
    }
  }

  def addEdge( a: T, b: T ) = {
    addVertex( a )
    addVertex( b )
    getVerticesAdjacentTo( a ).add( b )
  }

  def getVerticesAdjacentTo( v: T ): Set[T] = vertices get v
}

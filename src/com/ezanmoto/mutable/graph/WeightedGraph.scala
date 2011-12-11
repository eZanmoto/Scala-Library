package com.ezanmoto.mutable.graph

import GraphProperty._

import java.util.HashMap
import java.util.HashSet
import java.util.Set

class WeightedGraph[T]( private val property: GraphProperty )
    extends HashGraph[T]( property ) {

  protected val weights  = new HashMap[(T, T), Double]

  override def addEdge( e: (T, T) ) = addWeightedEdge( e, 0 )

  def addWeightedEdge( e: (T, T), weight: Int ): Unit = {
    super.addEdge( e )
    weights.put( e, weight )
    weights.put( ( e._2, e._1 ), weight )
  }

  def getEdgeWeight( edge: (T, T) ): Double = {
    if ( weights containsKey edge )
      weights.get( edge )
    else
      throw new IllegalArgumentException( "No edge '" + edge + "'" )
  }
}

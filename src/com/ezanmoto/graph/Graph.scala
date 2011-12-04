package com.ezanmoto.graph

import GraphProperty.GraphProperty

import java.util.Set

trait Graph[T] {
  def hasProperty( p: GraphProperty ): Boolean
  def containsVertex( v: T ): Boolean
  def addVertex( v: T )
  def addEdge( a: T, b: T )
  def getVerticesAdjacentTo( v: T ): Set[T]
}

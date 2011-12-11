package com.ezanmoto.mutable.graph

import GraphProperty.GraphProperty

import java.util.Set

trait Graph[T] {
  def hasProperty( p: GraphProperty ): Boolean
  def containsVertex( v: T ): Boolean
  def addVertex( v: T ): Unit
  def addEdge( e: (T, T) ): Unit
  def getVerticesAdjacentTo( v: T ): Set[T]
}

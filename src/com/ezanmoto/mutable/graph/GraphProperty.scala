package com.ezanmoto.mutable.graph

object GraphProperty extends Enumeration {
  type GraphProperty = Value
  val directed, simple, nonCyclic = Value
}

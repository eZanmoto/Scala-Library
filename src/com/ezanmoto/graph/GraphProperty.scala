package com.ezanmoto.graph

object GraphProperty extends Enumeration {
  type GraphProperty = Value
  val directed, weighted, simple, nonCyclic, labelled = Value
}

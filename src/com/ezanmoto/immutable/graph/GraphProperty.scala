package com.ezanmoto.immutable.graph

object GraphProperty extends Enumeration {
  type GraphProperty = Value
  val directed, simple, cyclic = Value
}

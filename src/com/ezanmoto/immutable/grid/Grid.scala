package com.ezanmoto.immutable.grid

trait Grid[T] {

  val width: Int

  val height: Int

  def at( point: (Int, Int) ): T

  def updated( point: (Int, Int), value: T ): Grid[T]
}

object Grid {

  def apply[T]( width: Int, value: T ): Grid[T] =
    new ListGrid[T]( width, value )
}

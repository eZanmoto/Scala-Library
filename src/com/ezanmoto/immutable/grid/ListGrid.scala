package com.ezanmoto.immutable.grid

class ListGrid[T]( grid: List[List[T]] ) extends Grid[T] {

  val width = grid.length

  val height = if ( width > 0 ) grid( 0 ).length else 0

  def this( w: Int, h: Int, v: T ) = this( ListGrid.ofSize( w, h, v ) )

  def this( width: Int, value: T ) = this( width, width, value )

  def at( point: (Int, Int) ) = grid( point._2 )( point._1 )

  def updated( point: (Int, Int), value: T ): ListGrid[T] = point match {
    case ( x, y ) =>
      new ListGrid( grid updated ( y, grid( y ) updated ( x, value ) ) )
  }

  override def toString = {
    var string = ""
    for ( row <- 1 to grid.length )
      string = string + " _"
    string = string + "\n"
    for ( row <- grid ) {
      string = string + "|"
      for ( cell <- row )
        string = string + cell + "|"
      string = string + "\n"
    }
    string
  }
}

private object ListGrid {

  def ofSize[T]( width: Int, height: Int, value: T ): List[List[T]] =
    if ( width > 0 )
      listOfSize( height, value ) :: ListGrid.ofSize( width - 1, height, value )
    else
      Nil

  def listOfSize[T]( size: Int, value: T ): List[T] =
    if ( size > 0 )
      value :: listOfSize( size - 1, value )
    else
      Nil
}

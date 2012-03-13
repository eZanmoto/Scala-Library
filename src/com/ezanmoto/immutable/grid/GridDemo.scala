package com.ezanmoto.immutable.grid

object GridDemo {

  def main( args: Array[String] ) = {
    var grid = Grid[Char]( 10, '_' )
    grid = grid updated ( ( 0, 0 ), '#' )
    grid = grid updated ( ( 1, 1 ), '#' )
    grid = grid updated ( ( 2, 2 ), '#' )
    grid = grid updated ( ( 4, 3 ), '#' )
    grid = grid updated ( ( grid.width - 1, grid.height - 1 ), '#' )
    println( grid )
  }
}

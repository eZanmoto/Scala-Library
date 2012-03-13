package com.ezanmoto.immutable.graph

object BellmanFord {
  def of[T]( graph: WeightedGraph[T, Int] ) = new ShortestPaths[T]( graph )
}

class ShortestPaths[T]( graph: WeightedGraph[T, Int] ) {
  def from( vertex: T ): ShortestPath[T] = {
    var distances = Map[T, (T, Int)]() + ( vertex -> ( vertex, 0 ) )
    for ( v <- BFS over graph from vertex ) {
      val sourceCost = ( distances get v ).get._2
      val neighbours = ( graph getVerticesAdjacentTo v get )
      for ( neighbour <- neighbours ) {
        val dest   = distances get neighbour
        val weight = sourceCost + ( graph weightOf ( v, neighbour ) get )
        if ( dest == None || weight < dest.get._2 )
          distances = distances + ( neighbour -> ( v, weight ) )
      }
    }
    new ShortestPath[T]( distances )
  }
}

class ShortestPath[T]( paths: Map[T, (T, Int)] ) {
  def to( vertex: T ): List[(T, T)] = {
    var path = ( predecessorOf( vertex ), vertex ) :: Nil
    while ( costOf( path.head._1 ) != 0 )
      path = ( predecessorOf( path.head._1 ), path.head._1 ) :: path
    path
  }

  private def predecessorOf( vertex: T ) = getVertex( vertex )._1

  private def costOf( vertex: T ) = getVertex( vertex )._2

  private def getVertex( vertex: T ) = paths get vertex get
}

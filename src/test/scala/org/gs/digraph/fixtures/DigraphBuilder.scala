package org.gs.digraph.fixtures

import org.gs.digraph.EdgeWeightedDigraph
import org.gs.digraph.Digraph
import scala.io.BufferedSource
import scala.collection.mutable.ArrayBuffer
import org.gs.fixtures.BufferedSourceBuilder

trait DigraphBuilder extends BufferedSourceBuilder {
  val tinyDAGdata = Array[(Int, Int)]((2, 3), (0, 6), (0, 1), (2, 0), (11, 12), (9, 12),
    (9, 10), (9, 11), (3, 5), (8, 7), (5, 4), (0, 5), (6, 4), (6, 9), (7, 6))
  val tinyDAG = new Digraph(13)
  for (t <- tinyDAGdata) tinyDAG.addEdge(t._1, t._2)

  val tinyDGdata = Array[(Int, Int)]((4, 2), (2, 3), (3, 2), (6, 0), (0, 1), (2, 0),
    (11, 12), (12, 9), (9, 10), (9, 11), (7, 9), (10, 12), (11, 4), (4, 3), (3, 5),
    (6, 8), (8, 6), (5, 4), (0, 5), (6, 4), (6, 9), (7, 6))

  val tinyDG = new Digraph(13)
  for (t <- tinyDGdata) tinyDG.addEdge(t._1, t._2)

  val equals = (_: Int) == (_: Int)

  val expectedOrderTinyDG = List(7, 6, 8, 9, 10, 11, 12, 0, 1, 5, 4, 3, 2)

  val intPattern = """^\d+$""".r
  val pairPattern = """^(\s*\d+)\s{1,2}(\d+\s*)$""".r
  def readFileToTuple(buffSource: BufferedSource): (Int, Int, ArrayBuffer[(Int, Int)]) = {
    val savedLines = new ArrayBuffer[(Int, Int)]()
    val it = buffSource.getLines
    var v = 0
    var e = 0
    for {
      s <- it
    } {
      s match {
        case pairPattern(a, b) => savedLines.append((a.trim.toInt, b.trim.toInt))
        case intPattern() => if (v == 0) v = s.trim.toInt else e = s.trim.toInt
        case _ => println(s)
      }
    }
    (v, e, savedLines)
  }
}
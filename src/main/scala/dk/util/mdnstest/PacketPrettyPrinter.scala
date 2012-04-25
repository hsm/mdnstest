package dk.util.mdnstest

import java.util.Formatter

object PacketPrettyPrinter {

  def print(packet: Array[Int]) {

    val hexes = packet map {i => (new Formatter()).format("%02x", int2Integer(i)).toString}
    val hexGroupsOf8 = (hexes.grouped(8) map {_.mkString(" ")}).toList
    val hexGroupsOf16 = (hexGroupsOf8.grouped(2) map {_.mkString("  ")}).toList

    val chars = packet map {i => if (i < 0x7f && i.toChar.isLetter) i.toChar else '.'}
    val charGroupsOf16 = (chars.grouped(16) map {_.mkString}).toList

    val counts = (for {
      i <- 0.until(hexGroupsOf16.size * 16, 16)
    } yield (new Formatter()).format("%05x", int2Integer(i)).toString).toList
    
    for (((cnt, hex), chr) <- (counts zip hexGroupsOf16) zip charGroupsOf16) {
      println(((cnt + "   " + hex).padTo(60, ' ')) + chr)
    }
  }

}
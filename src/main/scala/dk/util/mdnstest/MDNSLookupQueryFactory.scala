package dk.util.mdnstest

object MDNSLookupQueryFactory {

  def create(name: String): Array[Int] = {
    println("Creating packet for name lookup on '" + name + "'")
    
    val before = Array(
      0x00, 0x00,
      0x00, 0x00,
      0x00, 0x01,
      0x00, 0x00,
      0x00, 0x00,
      0x00, 0x00
    )
    val after = Array(
      0x05, 'l', 'o', 'c', 'a', 'l', 0x00,
      0x00, 0x01,
      0x00, 0x01
    )
    
    val intName = name.getBytes map {_.toInt}
    
    (before :+ name.size) ++ intName ++ after
  }

}